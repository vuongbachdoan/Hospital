/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Nguyen Thi Thuy Dung
 */
public class Nurse extends Person{
    private String staffId;
    private String department;
    private String shift;
    private Double salary;

    public Nurse() {
    }

    public Nurse(String staffId, String department, String shift, Double salary, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.staffId = staffId;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    
}
