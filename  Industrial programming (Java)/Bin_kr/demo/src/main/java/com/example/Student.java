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

    public int compareTo(Student obj) {
        return studentAmount.compareTo(obj.studentAmount);
    }
}