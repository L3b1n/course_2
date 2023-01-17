package com.patterns.Composite;

import java.util.List;
import java.util.ArrayList;

public class HeadDepartment implements Department {
    private Integer id;
    private String name;
    private List<Department> childDepartments;
    public HeadDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.childDepartments = new ArrayList<>();
    }

    public Integer getDepartmentID() {
        return this.id;
    }

    public String getDepartmentName() {
        return this.name;
    }

    public void printDepartmentName() {
        System.out.println("HeadDepartment:");
        childDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}