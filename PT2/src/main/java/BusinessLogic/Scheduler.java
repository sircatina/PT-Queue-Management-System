package BusinessLogic;

import Models.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Models.Task;

import static java.lang.Thread.sleep;

public class Scheduler {
    private List<Server> servers;
    private List<Thread> threads;
    private int maxNoServers;
    private int maxTasksPerServer;
    private int waitingT;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.threads = new ArrayList<Thread>(maxNoServers);
        this.servers = new ArrayList<Server>(maxNoServers);
        this.setMaxNoServers(maxNoServers);
        this.setMaxTasksPerServer(maxTasksPerServer);
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
            server.setOpen(false);
            Thread th = new Thread(server, "Q" + (i + 1));
            threads.add(th);
            threads.get(i).start();
        }
    }

    public int getWaitingT() {
        return waitingT;
    }

    public void setWaitingT(int waitingT) {
        this.waitingT = waitingT;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public void dispatchTask(Task t) {
        int min = 99;
        int id = 0;
        for (int i = 0; i < servers.size(); i++)
            if (servers.get(i).getTasks().size() < min) {
                min = servers.get(i).getTasks().size();
                id = i;
            }
        for (int i = 0; i < servers.size(); i++) {
            if (i == id) {
                servers.get(i).setOpen(true);
                servers.get(i).addTask(t);
                Thread th = new Thread(servers.get(i), "Q" + (i + 1));
                threads.add(th);
                th.start();
            }
        }
    }
    public boolean notEmpty(){
        for(Server server:this.servers){
            if(!server.getTasks().isEmpty()){
                return true;
            }
        }
        return false;
    }
    public int peakHour(){
        int peak=0;
        for(Server server:this.servers){
                peak+=server.getTasks().size();
        }
        return peak;
    }
    public int totalWaitingTime() {
        waitingT=0;
        for (Server q : servers) {
           waitingT+=  q.waitingPerson.get();
        }
            return waitingT;

    }

    public void killThreads() {
        for (Server q : servers) {
            q.setOpen(false);
        }
    }

    public String toString() {
        String rezultat = "";
        for (int i = 0; i < servers.size(); i++) {
            rezultat += "Queque[" + (i + 1) + "]: " + servers.get(i).toString() + "\n";
        }
        return rezultat;
    }
}


