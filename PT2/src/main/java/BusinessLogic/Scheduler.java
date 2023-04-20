package BusinessLogic;

import Models.Server;

import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    public Scheduler(List<Server> servers, int maxNoServers, int maxTasksPerServer, Strategy strategy) {
        this.servers = servers;
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = strategy;
        for(int i=0;i<maxNoServers;i++){
            //create server obj
            //create thread with the obj
        }
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
    public Strategy getStrategy() {
        return strategy;
    }
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void changeStrategy(SelectionPolicy policy){
        //apply strategy patter
        //strategy corresponding to policy
        if(policy == SelectionPolicy.SHORTEST_QUEQUE){
            strategy=new ConcreteStrategyQueque();
        }
        if(policy == SelectionPolicy.sHORTEST_TIME){
            strategy=new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task t){
        //call the strategy addTask method
    }

}
