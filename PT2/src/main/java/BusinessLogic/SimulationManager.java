package BusinessLogic;

import GUI.SimulationFrame;
import Models.Task;
import Models.WriteFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable {

    public int timeLimit = 100;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    private List<Thread> threads;
    public static boolean ok = false;
    public SimulationFrame view;
    public static int nrCl;
    public static int nrQue;
    public static int arrMax;
    public static int arrMin;
    public static int servMin;
    public static int servMax;
    public static int simInterval;
    public static int currentTime = 0;
    public int nrProcCl = 0;
    public int avgTime = 0;
    public int waitingTime = 0;
    private WriteFile writeFile;

    public SimulationManager(SimulationFrame view) throws IOException {
        this.view = view;

        this.view.addValidateListener(new validateButtonListener());
        //this.view.addAddCandyListener(new startButtonListener());
        generatedTasks = new ArrayList<Task>(nrCl);
        this.writeFile = new WriteFile();
        this.writeFile.create();
    }

    class validateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                nrCl = view.getNrClientstextField();
                nrQue = view.getNrQuetextField();
                arrMax = view.getMaxArrTimetextField();
                arrMin = view.getMinArrTimetextField();
                servMin = view.getMinServiceTimetextField();
                servMax = view.getMaxServiceTimetextField();
                simInterval = view.getSimIntervaltextField();
                String mesaj = "from now you can start the simulation";
                generateNRandomTasks();
                scheduler = new Scheduler(nrQue, nrCl);
                view.showMessage1(mesaj);


            } catch (Exception exception) {
                view.showMessage1("Can't validate input data :(");
            }
        }
    }

    public int RandomNumberExample(int maximum, int minimum) {
        Random rand = new Random();
        int randomNum = minimum + rand.nextInt(maximum - minimum + 1);
        return randomNum;
    }

    private void generateNRandomTasks() {

        for (int i = 0; i < nrCl; i++) {
            int ID = RandomNumberExample(100, 0);
            int arrivalTime = RandomNumberExample(arrMax, arrMin);
            int serviceTime = RandomNumberExample(servMax, servMin);
            Task t = new Task(ID, arrivalTime, serviceTime);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }

    public synchronized void run() {
        currentTime = 0;
        int peek=0;
        int second=0;
        int waitingTime=0;
        String result = "";
        while (currentTime < simInterval && (!generatedTasks.isEmpty() || scheduler.notEmpty())) {
            currentTime++;
            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                if (currentTime + generatedTasks.get(0).getServiceTime() < simInterval) {
                    nrProcCl++;
                    avgTime += generatedTasks.get(0).getServiceTime();
                }
                generatedTasks.remove(0);
            }
            if(peek<scheduler.peakHour())
            {
                peek=scheduler.peakHour();
                second=currentTime;
            }
            result += getResult(currentTime);
            result += scheduler.toString();
            view.setTextArea(result);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        float wait=Math.abs(scheduler.totalWaitingTime());
        float avgWait=(float) wait/nrCl;
        float avg =(float) avgTime / nrProcCl;
        result += "Average service time: " + avg+"\n"; //average service time
        result += "Average waiting time: " +avgWait+"\n"; //average waiting time-> cand adaug om in que parcurg sa vad cati is in fata
        result += "Peak hour time: " + second+"\n";  //peak our cand is cei mai multi in magazin
        view.setTextArea(result);
        try {
            writeFile.writeInFile(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scheduler.killThreads();
        for (Thread thread : scheduler.getThreads()) {
            thread.interrupt();
        }
    }
    private String getResult(int currentTime) {

        String result = "\nTime: " + currentTime + "\n";
        result = result + "Waiting clients: \n";
        for (Task i : generatedTasks) {
            result = result + i.toString();
        }
        result = result + "\n";
        return result;
    }

    public static void main(String[] args) {
        try {
            SimulationFrame view = new SimulationFrame();
            view.setVisible(true);
            SimulationManager gen = new SimulationManager(view);
            Thread t = new Thread(gen);
            sleep(20000);
            t.start();
            try {
                t.join();
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}