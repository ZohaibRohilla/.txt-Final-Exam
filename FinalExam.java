package com.mycompany.textmanipulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinalExam {
    static class Student {
        String firstName;
        String middleInitial;
        String lastName;
        double prelimGrade;
        double midtermGrade;
        double finalGrade;

        public Student(String firstName, String middleInitial, String lastName,
                       double prelimGrade, double midtermGrade, double finalGrade) {
            this.firstName = firstName;
            this.middleInitial = middleInitial;
            this.lastName = lastName;
            this.prelimGrade = prelimGrade;
            this.midtermGrade = midtermGrade;
            this.finalGrade = finalGrade;
        }

        public double computeSemesterGrade() {
            return (prelimGrade + midtermGrade + finalGrade) / 3;
        }

        public String getStatus() {
            return computeSemesterGrade() >= 75 ? "PASSED" : "FAILED";
        }

        @Override
        public String toString() {
            return firstName + " " + middleInitial + ". " + lastName +
                   " (PG: " + prelimGrade + ", MG: " + midtermGrade +
                   ", FG: " + finalGrade + ", SG: " + computeSemesterGrade() +
                   " - " + getStatus() + ")";
        }
    }

    private static List<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("-----Select from the choices-----");
            System.out.println("a - Add record");
            System.out.println("b - Show all records");
            System.out.println("c - Delete all records");
            System.out.println("d - Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "a":
                    addRecord();
                    break;
                case "b":
                    showAllRecords();
                    break;
                case "c":
                    deleteAllRecords();
                    break;
                case "d":
                    saveRecordsToFile();
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void addRecord() {
        System.out.println("----------Add record-----------");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Middle Initial: ");
        String middleInitial = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Prelim Grade: ");
        double prelimGrade = Double.parseDouble(scanner.nextLine());
        System.out.print("Midterm Grade: ");
        double midtermGrade = Double.parseDouble(scanner.nextLine());
        System.out.print("Final Grade: ");
        double finalGrade = Double.parseDouble(scanner.nextLine());

        Student student = new Student(firstName, middleInitial, lastName, prelimGrade, midtermGrade, finalGrade);
        studentList.add(student);
        System.out.println("Record successfully saved!");
    }

    private static void showAllRecords() {
        System.out.println("--------Show all records-------");
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("All records:");
            for (Student student : studentList) {
                System.out.println(" => " + student);
            }
        }
    }

    private static void deleteAllRecords() {
        studentList.clear();
        System.out.println("All records deleted.");
    }

    private static void saveRecordsToFile() {
        try (FileWriter writer = new FileWriter("student_records.txt")) {
            for (Student student : studentList) {
                writer.write(student.firstName + "<>" + student.middleInitial + "<>" + student.lastName + "<>" +
                             student.prelimGrade + "<>" + student.midtermGrade + "<>" + student.finalGrade +
                             System.lineSeparator());
            }
            System.out.println("Records saved to student_records.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the records.");
            e.printStackTrace();
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program.");
    }
}