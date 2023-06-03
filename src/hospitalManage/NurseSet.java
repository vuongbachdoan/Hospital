/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalManage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import models.Nurse;
import utils.File;
import utils.Validate;

/**
 *
 * @author user
 */
public class NurseSet {
    private HashMap<String, Nurse> nurses = new HashMap<>();
    private String input;
    private Scanner scanner = new Scanner(System.in);
    private Validate validate = new Validate();
    private File fileHandle = new File();
    
    public void createNurse() {
        while (true) {
            do {
                System.out.print("Enter nurse id: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkText(input) || nurses.containsKey(input));
            String staffID = input;

            do {
                System.out.print("Enter nurse name: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkName(input));
            String name = input;

            do {
                System.out.print("Enter nurse age: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkAge(input) || Integer.parseInt(input) < 24); // y hoc 5 nam
            int age = Integer.parseInt(input);

            do {
                System.out.print("Enter nurse gender(male/female): ");  // Accept MALE/FEMALE
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("male") || input.equals("female")));
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
            } while (input.isEmpty() || !validate.checkNum(input) || input.length() < 9); // phone number >= 9 number
            String phone = input;

            do {
                System.out.print("Enter nurse department: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkDepartment(input) || !(input.length() >= 3 || input.length() <= 50));
            String department = input;

            do {
                System.out.print("Enter nurse shift(morning, afternoon, evening): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("morning") || input.equals("afternoon") || input.equals("evening")));
            String shift = input;

            do {
                System.out.print("Enter nurse salary: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkDouble(input) || Double.parseDouble(input) < 0);
            Double salary = Double.parseDouble(input);

            Nurse newNurse = new Nurse(staffID, department, shift, salary, shift, name, age, gender, address, phone);
            this.nurses.put(staffID, newNurse);

            do {
                System.out.print("Added new nurse, do you want to continue(Y/N):");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("y") || input.equals("n")));
            
            if (input.equals("n")) {
                break;
            }
        }
    }

    public void findNurse() {
        do {
            System.out.print("Enter nurse name: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty());       
        String nameFind = input;

        HashMap<String, Nurse> foundNurses = findNurse(nameFind);
        
        if (foundNurses != null) {
            this.viewNurse(foundNurses);
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    public HashMap<String, Nurse> findNurse(String partialName) {
        HashMap<String, Nurse> foundNurses = new HashMap<>();
        
        for (Map.Entry<String, Nurse> entry : nurses.entrySet()) {
            if (entry.getValue().getName().contains(partialName)) {
                foundNurses.put(entry.getKey(), entry.getValue());
            }
        }
        return foundNurses;
    }

    public void updateNurse() {
        do {
            System.out.println("Enter nurse ID: ");
            input = scanner.nextLine();
        } while (input.isEmpty());
        String idFind = input;

        if (!this.nurses.containsKey(idFind)) {
            System.out.println("The nurse does not exist");
            System.out.println("Update failed!");
            return;
        } else {
            System.out.println("Continue edit information");
        }

        do {
            System.out.print("Enter nurse name: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !validate.checkName(input));
        String name = input;

        do {
            System.out.print("Enter nurse age: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !validate.checkAge(input) || Integer.parseInt(input) < 24); // y hoc 5 nam
        int age = Integer.parseInt(input);

        do {
            System.out.print("Enter nurse gender(male/female): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("male") || input.equals("female")));
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
        } while (input.isEmpty() || !validate.checkNum(input) || input.length() < 9); // phone number >= 9 number
        String phone = input;

        do {
            System.out.print("Enter nurse department: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !validate.checkDepartment(input) || !(input.length() >= 3 || input.length() <= 50));
        String department = input;

        do {
            System.out.print("Enter nurse shift(morning, afternoon, evening): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("morning") || input.equals("afternoon") || input.equals("evening")));
        String shift = input;

        do {
            System.out.print("Enter nurse salary: ");
            input = scanner.nextLine();
            input = input.trim();
        } while (input.isEmpty() || !validate.checkDouble(input) || Double.parseDouble(input) < 0);
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
            System.out.println("Delete failed");
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
    
    public void loadNurse () {
        fileHandle.loadData(nurses, null);
    }
    
    public void saveNurse() {
        fileHandle.saveData(nurses, null);
    }

    public void viewNurse() {
        System.out.println("LIST OF NURSES");
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "NurseID", "Full name", "Department", "Shift", "Salary");
        int counter = 0;
        for (Map.Entry<String, Nurse> entry : nurses.entrySet()) {
            counter++;
            System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                    counter, entry.getValue().getId(), entry.getValue().getName(), entry.getValue().getDepartment(), entry.getValue().getShift(), entry.getValue().getSalary());
        }
    }
    
    public void viewNurse(HashMap<String, Nurse> partialNurses) {
        System.out.println("LIST OF NURSES");
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "NurseID", "Full name", "Department", "Shift", "Salary");
        int counter = 0;
        for (Map.Entry<String, Nurse> entry : partialNurses.entrySet()) {
            counter++;
            System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                    counter, entry.getValue().getId(), entry.getValue().getName(), entry.getValue().getDepartment(), entry.getValue().getShift(), entry.getValue().getSalary());
        }
    }
}
