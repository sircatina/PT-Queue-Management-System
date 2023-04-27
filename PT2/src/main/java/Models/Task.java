package Models;

public class Task implements Comparable<Task> {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.arrivalTime, task.getArrivalTime());
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
    public String toString() {
        String rezultat="";
        rezultat= "Task id: " + this.getID() +", arrivalTime: " + this.getArrivalTime()+",serviceTime: " + this.getServiceTime() +"\n";
        return rezultat;
    }
}
