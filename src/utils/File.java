/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.Nurse;
import models.Patient;

/**
 *
 * @author user
 */
public class File {

    public ArrayList<String> readFileData(String filePath) {
        ArrayList<String> fileData = new ArrayList<>(); // storage data in file as array of strings
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            // read file line by line
            while (line != null) {
                fileData.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    public void loadData(HashMap<String, Nurse> nurses, HashMap<String, Patient> patients) {
        if (nurses != null) {
            ArrayList<String> nursesFromFile = this.readFileData("src/data/nurses.dat");
            for (String staffString : nursesFromFile) {
                String[] data = staffString.split(",");
                nurses.put(
                        data[0],
                        new Nurse(
                                data[0],//                    String staffId, 
                                data[1],//                    String department, 
                                data[2],//                    String shift, 
                                Double.parseDouble(data[3]),//                    Double salary, 
                                data[4],//                    String id, 
                                data[5],//                    String name, 
                                Integer.parseInt(data[6]),//                    int age, 
                                data[7],//                    String gender, 
                                data[8],//                    String address, 
                                data[9]//                    String phone
                        ));
            }

            System.out.println("Successfully load nurses");
        }

        if (patients != null) {
            ArrayList<String> patientsFromFile = this.readFileData("src/data/patients.dat");
            for (String patientString : patientsFromFile) {
                String[] data = patientString.split(",");
                patients.put(
                        data[4],
                        new Patient(
                                data[0], 
                                data[1], 
                                data[2], 
                                data[3], 
                                data[4],
                                data[5], 
                                Integer.parseInt(data[6]), 
                                data[7], 
                                data[8], 
                                data[9]
                        ));
            }

            System.out.println("Successfully load patients");
        }
    }

    public void saveData(HashMap<String, Nurse> nurses, HashMap<String, Patient> patients) {
        if (patients != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/patients.dat"));
                for (Map.Entry<String, Patient> entry : patients.entrySet()) {
                    Patient patientData = entry.getValue();
                    String writeString
                            = patientData.getDiagnosis() + "," + patientData.getAdmissionDate() + "," + patientData.getDischargeDate() + ","
                            + patientData.getNurseAssigned() + "," + patientData.getId() + "," + patientData.getName() + ","
                            + patientData.getAge() + "," + patientData.getGender() + "," + patientData.getAddress() + ","
                            + patientData.getPhone() + "\n";
                    writer.write(writeString);
                }
                System.out.println("Successfully save nurse");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (nurses != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/nurses.dat"));
                for (Map.Entry<String, Nurse> entry : nurses.entrySet()) {
                    Nurse nurseData = entry.getValue();
                    String writeString
                            = nurseData.getId() + "," + nurseData.getDepartment() + "," + nurseData.getShift() + ","
                            + nurseData.getSalary() + "," + nurseData.getId() + "," + nurseData.getName() + ","
                            + nurseData.getAge() + "," + nurseData.getGender() + "," + nurseData.getAddress() + ","
                            + nurseData.getPhone()+ "\n";
                    writer.write(writeString);
                }
                System.out.println("Successfully save patients");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
