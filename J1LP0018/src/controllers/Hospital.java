/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import models.Nurse;
import models.Patient;

/**
 *
 * @author Nguyen Thi Thuy Dung
 */
public class Hospital {

    private HashMap<String, Nurse> nurses = new HashMap<>();
    private HashMap<String, Patient> patients = new HashMap<>();
    private String input;
    private Scanner scanner = new Scanner(System.in);
    private Utilities utils = new Utilities();

    public void createNurse() {
        while (true) {
            do {
                System.out.print("Enter nurse id: ");
                input = scanner.nextLine();
            } while (input.isEmpty() || !utils.checkText(input) || nurses.containsKey(input));
            String staffID = input;

            do {
                System.out.print("Enter nurse name: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkName(input));
            String name = input;

            do {
                System.out.print("Enter nurse age: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkAge(input) || Integer.parseInt(input) < 24); // y hoc 5 nam
            int age = Integer.parseInt(input);

            do {
                System.out.print("Enter nurse gender(male/female): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("male") || input.equals("female")));
            String gender = input;

            do {
                System.out.print("Enter nurse address: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty());
            String address = input;

            do {
                System.out.print("Enter nurse phone: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkNum(input) || input.length() < 9); // phone number >= 9 number
            String phone = input;

            do {
                System.out.print("Enter nurse department: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkDepartment(input) || !(input.length() >= 3 || input.length() <= 50));
            String department = input;

            do {
                System.out.print("Enter nurse shift(morning, afternoon, evening): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("morning") || input.equals("afternoon") || input.equals("evening")));
            String shift = input;

            do {
                System.out.print("Enter nurse salary: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkDouble(input) || Double.parseDouble(input) < 0);
            Double salary = Double.parseDouble(input);

            Nurse newNurse = new Nurse(staffID, department, shift, salary, shift, name, age, gender, address, phone);
            this.nurses.put(staffID, newNurse);

            do {
                System.out.print("Added new nurse, do you want to continue(Y/N):");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("y") || input.equals("n")));
            if (input.equals("n")) {
                break;
            }
        }
    }

    public void findNurse() {
        do {
            System.out.println("Enter nurse name: ");
            input = scanner.nextLine();
        } while (input.isEmpty());
        String nameFind = input;

        Nurse foundNurse = findNurse(nameFind);
        if (foundNurse != null) {
            System.out.println(foundNurse.toString());
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    public Nurse findNurse(String partialKey) {
        for (Map.Entry<String, Nurse> entry : nurses.entrySet()) {
            if (entry.getKey().toString().contains(partialKey.toString())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void updateNurse() {
        do {
            System.out.println("Enter nurse ID: ");
            input = scanner.nextLine();
        } while (input.isEmpty());
        String idFind = input;

        if (!this.nurses.containsKey(idFind)) {
            System.out.println("The nurse does not exist");
            return;
        } else {
            System.out.println("Continue edit information");
        }

        do {
            System.out.print("Enter nurse name: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !utils.checkName(input));
        String name = input;

        do {
            System.out.print("Enter nurse age: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !utils.checkAge(input) || Integer.parseInt(input) < 24); // y hoc 5 nam
        int age = Integer.parseInt(input);

        do {
            System.out.print("Enter nurse gender(male/female): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("male") || input.equals("female")));
        String gender = input;

        do {
            System.out.print("Enter nurse address: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty());
        String address = input;

        do {
            System.out.print("Enter nurse phone: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !utils.checkNum(input) || input.length() < 9); // phone number >= 9 number
        String phone = input;

        do {
            System.out.print("Enter nurse department: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !utils.checkDepartment(input) || !(input.length() >= 3 || input.length() <= 50));
        String department = input;

        do {
            System.out.print("Enter nurse shift(morning, afternoon, evening): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("morning") || input.equals("afternoon") || input.equals("evening")));
        String shift = input;

        do {
            System.out.print("Enter nurse salary: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !utils.checkDouble(input) || Double.parseDouble(input) < 0);
        Double salary = Double.parseDouble(input);

        Nurse newNurse = new Nurse(idFind, department, shift, salary, shift, name, age, gender, address, phone);
        this.nurses.put(idFind, newNurse);

        System.out.println("Add successfully");
    }

    public void deleteNurse() {
        do {
            System.out.println("Enter nurse ID: ");
            input = scanner.nextLine();
        } while (input.isEmpty());
        String idDelete = input;

        if (!this.nurses.containsKey(idDelete)) {
            System.out.println("The nurse does not exist");
            return;
        } else {
            do {
                System.out.println("Do you want to continue delete(Y/N)");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !(input.equals("y") || input.equals("n")));

            if (input.equals("y")) {
                this.nurses.remove(idDelete);
                System.out.println("Success to delete");
            } else {
                System.out.println("Fail to delete");
                return;
            }
        }
    }
    
    public void addPatient() {
        while (true) {
            do {
                System.out.print("Enter patient id: ");
                input = scanner.nextLine();
            } while (input.isEmpty() || !utils.checkText(input) || patients.containsKey(input));
            String id = input;

            do {
                System.out.print("Enter patient name: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkName(input));
            String name = input;

            do {
                System.out.print("Enter patient age: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkAge(input) || Integer.parseInt(input) < 24); // y hoc 5 nam
            int age = Integer.parseInt(input);

            do {
                System.out.print("Enter patient gender(male/female): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("male") || input.equals("female")));
            String gender = input;

            do {
                System.out.print("Enter patient address: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty());
            String address = input;

            do {
                System.out.print("Enter patient phone: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !utils.checkNum(input) || input.length() < 9); // phone number >= 9 number
            String phone = input;

            do {
                System.out.print("Enter dianosis: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty());
            String dianosis = input;

            do {
                System.out.print("Enter admission date(yyyy/MM/dd): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || utils.checkDate(input));
            String admissionDate = input;

            do {
                System.out.print("Enter disCharge date(yyyy/MM/dd): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || utils.checkDate(input) || utils.checkDateBefore(admissionDate, input));
            String disChargeDate = input;
            
            do {
                System.out.print("Enter nurse assign id: ");
                input = scanner.nextLine();
            } while (input.isEmpty() || !utils.checkText(input) || !utils.checkNurseValid(input, patients));
            String idNurseAssign = input;

            Patient newPatient = new Patient(dianosis, admissionDate, disChargeDate, idNurseAssign, id, name, age, gender, address, phone);
            this.patients.put(id, newPatient);

            do {
                System.out.print("Added new patient, do you want to continue(Y/N):");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !utils.checkText(input) || !(input.equals("y") || input.equals("n")));
            if (input.equals("n")) {
                break;
            }
        }
    }
}
