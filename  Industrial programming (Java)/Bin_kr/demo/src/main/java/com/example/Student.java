package com.example;

public class Student {
    String studentFirstName;
    String studentLastName;
    Double studentAmount;
    String studentID;
    String fileName;

    public Student(String studentFirstName, String studentLastName, Double studentAmount, String studentID, String fileName) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentAmount = studentAmount;
        this.studentID = studentID;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return studentFirstName + " " + studentLastName + " " + Double.toString(studentAmount) + " " + studentID + " " + fileName;
    }

    public Integer compareTo(Student temp) {
        Double item_0 = Double.valueOf(temp.studentAmount);
        Double item_1 = Double.valueOf(this.studentAmount);
        if(this.studentFirstName == temp.studentFirstName) {
            return item_1.compareTo(item_0);
        }
        return this.studentFirstName.compareTo(temp.studentFirstName);
    }
}