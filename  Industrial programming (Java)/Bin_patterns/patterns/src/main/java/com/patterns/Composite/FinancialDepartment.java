package com.patterns.Composite;

public class FinancialDepartment implements Department {
    private Integer id;
    private String name;
    public FinancialDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getDepartmentID() {
        return this.id;
    }

    public String getDepartmentName() {
        return this.name;
    }

    @Override
    public void printDepartmentName() {
        System.out.println("\t" + getClass().getSimpleName());
    }
}
