package Models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static BusinessLogic.SimulationManager.nrCl;
import static BusinessLogic.SimulationManager.ok;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    public AtomicInteger waitingPeriod;
    public AtomicInteger waitingPerson;
    private boolean open = true;

    public Server() {
        this.tasks = new LinkedBlockingQueue<Task>();
        this.waitingPerson= new AtomicInteger(0);
        this.waitingPeriod = new AtomicInteger(0);
        this.setOpen(true);
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    public void addTask(Task newTask) {
        try {
                waitingPerson.getAndAdd(waitingPeriod.get());
            waitingPeriod.incrementAndGet();
                //waitingPeriod.getAndAdd(newTask.getServiceTime());
                tasks.add(newTask);

        } catch (IllegalStateException e) {
            // Handle the exception
            System.err.println("Failed to add task: queue is full");
        }
    }
    public String toString() {
        String result;
        if (waitingPeriod.get() == 0 || tasks.peek() == null || tasks.peek().getServiceTime() == 0) {
            result = "closed";
        } else {
            result = tasks.toString();
        }
        return result;
    }

    public synchronized void run() {
        while (getOpen()) {
            while (tasks.peek() != null) {
                try {
                    Thread.sleep(500);
                    waitingPeriod.addAndGet(-tasks.peek().getServiceTime());
                    tasks.peek().setServiceTime(tasks.peek().getServiceTime()-1);
                    if (tasks.peek().getServiceTime()==0) {
                        tasks.remove();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            setOpen(false);
        }
    }
}

