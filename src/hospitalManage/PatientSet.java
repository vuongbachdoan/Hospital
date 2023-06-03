/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalManage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import models.Patient;
import utils.File;
import utils.Validate;

/**
 *
 * @author user
 */
public class PatientSet {

    private HashMap<String, Patient> patients = new HashMap<>();
    private String input;
    private Scanner scanner = new Scanner(System.in);
    private Validate validate = new Validate();
    private File fileHandle = new File();

    public void addPatient() {
        while (true) {
            do {
                System.out.print("Enter patient id: ");
                input = scanner.nextLine();
            } while (input.isEmpty() || !validate.checkText(input) || patients.containsKey(input));
            String id = input;

            do {
                System.out.print("Enter patient name: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkName(input));
            String name = input;

            do {
                System.out.print("Enter patient age: ");
                input = scanner.nextLine();
                input = input.trim();
            } while (input.isEmpty() || !validate.checkAge(input));
            int age = Integer.parseInt(input);

            do {
                System.out.print("Enter patient gender(male/female): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("male") || input.equals("female")));
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
            } while (input.isEmpty() || !validate.checkNum(input) || input.length() < 9); // phone number >= 9 number
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
            } while (input.isEmpty() || !validate.checkDate(input));
            String admissionDate = input;

            do {
                System.out.print("Enter disCharge date(yyyy/MM/dd): ");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkDate(input) || !validate.checkDateBefore(admissionDate, input));
            String disChargeDate = input;

            do {
                System.out.print("Enter nurse assign id: ");
                input = scanner.nextLine();
            } while (input.isEmpty() || !validate.checkText(input) || !validate.checkNurseValid(input, patients));
            String idNurseAssign = input;

            Patient newPatient = new Patient(dianosis, admissionDate, disChargeDate, idNurseAssign, id, name, age, gender, address, phone);
            this.patients.put(id, newPatient);

            do {
                System.out.print("Added new patient, do you want to continue(Y/N):");
                input = scanner.nextLine();
                input = input.trim().toLowerCase();
            } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("y") || input.equals("n")));
            if (input.equals("n")) {
                break;
            }
        }
    }

    public void displayPatients() {
        do {
            System.out.println("Enter start date (yyyy/MM/dd): ");
            input = scanner.nextLine();
        } while (input.isEmpty() || !validate.checkDate(input));
        String startDate = input;
        do {
            System.out.println("Enter end date (yyyy/MM/dd): ");
            input = scanner.nextLine();
        } while (input.isEmpty() || !validate.checkDate(input) || !validate.checkDateBefore(startDate, input));
        String endDate = input;

        ArrayList<Patient> foundPatients = displayPatients(startDate, endDate);
        System.out.println("LIST OF PATIENTS");
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "PatientID", "Admission Date", "Full name", "Phone", "Diagnosis");
        int counter = 0;
        for (Patient patient : foundPatients) {
            counter++;
            System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                    counter, patient.getId(), patient.getAdmissionDate(), patient.getName(), patient.getPhone(), patient.getDiagnosis());
        }
    }

    public ArrayList<Patient> displayPatients(String startDate, String endDate) {
        ArrayList<Patient> matchedPatients = new ArrayList<>();
        for (Map.Entry<String, Patient> entry : patients.entrySet()) {
            String admissionDate = entry.getValue().getAdmissionDate();
            if (validate.checkDateBefore(admissionDate, endDate) && validate.checkDateBefore(startDate, admissionDate)) {
                matchedPatients.add(entry.getValue());
            }
        }
        return matchedPatients;
    }

    public void viewPatient() {
        System.out.println("LIST OF PATIENTS");
        System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "PatientID", "Admission Date", "Full name", "Phone", "Diagnosis");
        int counter = 0;
        for (Map.Entry<String, Patient> entry : patients.entrySet()) {
            counter++;
            System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                    counter, entry.getValue().getId(), entry.getValue().getAdmissionDate(), entry.getValue().getName(), entry.getValue().getPhone(), entry.getValue().getDiagnosis());
        }
    }

    public void sortPatients() {
        do {
            System.out.println("Enter sorted field (id/name): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("id") || input.equals("name")));
        String sortedField = input;

        do {
            System.out.println("Enter sort order (ASC / DESC): ");
            input = scanner.nextLine();
            input = input.trim().toLowerCase();
        } while (input.isEmpty() || !validate.checkText(input) || !(input.equals("asc") || input.equals("desc")));
        String sortOrder = input;

        System.out.println("LIST OF PATIENTS");
        System.out.println("Sorted by: " + sortedField);
        System.out.println("Sort order: " + sortOrder);

        int counter;
        switch (sortedField) {
            case "id":
                counter = 0;
                TreeMap<String, Patient> sortedByID = sortByID(sortOrder);
                System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "PatientID", "Full name", "Dianosis", "Addmission date", "Nurse asign");
                for (Map.Entry<String, Patient> entry : sortedByID.entrySet()) {
                    counter++;
                    System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                            counter, entry.getValue().getId(), entry.getValue().getName(), entry.getValue().getDiagnosis(), entry.getValue().getAdmissionDate(), entry.getValue().getNurseAssigned());
                }
                break;
            case "name":
                counter = 0;
                ArrayList<Patient> sortedByName = sortByName(sortOrder);
                for (int i = 0; i < sortedByName.size(); i++) {
                    System.out.println(sortedByName.get(i).toString());
                }
                System.out.printf("%15s %15s %15s %15s %15s %15s\n", "No.", "PatientID", "Full name", "Dianosis", "Addmission date", "Nurse asign");
                for (Patient entry : sortedByName) {
                    counter++;
                    System.out.printf("%15s %15s %15s %15s %15s %15s\n",
                            counter, entry.getId(), entry.getName(), entry.getDiagnosis(), entry.getAdmissionDate(), entry.getNurseAssigned());
                }
                break;
        }
    }

    public TreeMap<String, Patient> sortByID(String sortOrder) {
        TreeMap<String, Patient> sortedASC = new TreeMap<>();
        if (sortOrder.equals("asc")) {
            sortedASC.putAll(patients);
            return sortedASC;
        } else {
            TreeMap<String, Patient> sortedDESC = new TreeMap<>(Collections.reverseOrder());
            sortedDESC.putAll(patients);
            return sortedDESC;
        }
    }

    public ArrayList<Patient> sortByName(String sortOrder) {
        ArrayList<Patient> sortedList = new ArrayList<>();
        for (Map.Entry<String, Patient> entry : patients.entrySet()) {
            sortedList.add(entry.getValue());
        }

        if (sortOrder.equals("asc")) {
            Collections.sort(sortedList, new Comparator<Patient>() {
                @Override
                public int compare(Patient o1, Patient o2) {
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    return name1.compareTo(name2);
                }
            });
            return sortedList;
        } else {
            Collections.sort(sortedList, new Comparator<Patient>() {
                @Override
                public int compare(Patient o1, Patient o2) {
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    return name2.compareTo(name1);
                }
            });
            return sortedList;
        }
    }

    public void loadPatient() {
        fileHandle.loadData(null, patients);
    }

    public void savePatient() {
        fileHandle.saveData(null, patients);
    }
}
