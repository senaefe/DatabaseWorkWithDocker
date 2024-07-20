package com.rd.Database;

public class Personel {
    private int id;
    private String name;
    private String surname;
    private String job;
    private String department;
    private double salary;

    public Personel(int id, String name, String surname, String job, String department, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Personel{id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", job='" + job + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}

