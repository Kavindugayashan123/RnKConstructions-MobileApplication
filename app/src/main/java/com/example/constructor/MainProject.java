package com.example.constructor;

public class MainProject {

    private int id;
    private long started;
    private String projectName;
    private  String customerName;
    private String location;
    private  String others;
    private  String employees;
    private  String stokes;
    private String machines;
    private String eTime;
    private String eCost;
    private long finished;

    public MainProject(){

    }

    public MainProject(int id, long started, String projectName, String customerName, String location, String others, String employees, String stokes, String machines, String eTime, String eCost, long finished) {
        this.id = id;
        this.started = started;
        this.projectName = projectName;
        this.customerName = customerName;
        this.location = location;
        this.others = others;
        this.employees = employees;
        this.stokes = stokes;
        this.machines = machines;
        this.eTime = eTime;
        this.eCost = eCost;
        this.finished = finished;
    }

    public MainProject(long started, String projectName, String customerName, String location, String others, String employees, String stokes, String machines, String eTime, String eCost, long finished) {
        this.started = started;
        this.projectName = projectName;
        this.customerName = customerName;
        this.location = location;
        this.others = others;
        this.employees = employees;
        this.stokes = stokes;
        this.machines = machines;
        this.eTime = eTime;
        this.eCost = eCost;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getStokes() {
        return stokes;
    }

    public void setStokes(String stokes) {
        this.stokes = stokes;
    }

    public String getMachines() {
        return machines;
    }

    public void setMachines(String machines) {
        this.machines = machines;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String geteCost() {
        return eCost;
    }

    public void seteCost(String eCost) {
        this.eCost = eCost;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
