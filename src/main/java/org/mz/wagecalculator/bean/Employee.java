package org.mz.wagecalculator.bean;

public class Employee {

    private String employeeId;
    private String employeeName;
    private double wagesPerHour;
 
    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the wagesPerHour
     */
    public double getWagesPerHour() {
        return wagesPerHour;
    }

    /**
     * @param wagesPerHour the wagesPerHour to set
     */
    public void setWagesPerHour(double wagesPerHour) {
        this.wagesPerHour = wagesPerHour;
    }

    @Override
    public String toString() {
        return employeeId+": "+employeeName;
    }
    
    
}