/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import hospitalManage.NurseSet;
import hospitalManage.PatientSet;
import java.util.Scanner;
import utils.Validate;

/**
 *
 * @author user
 */
public class NurseManagementView {

    private Validate validate = new Validate();

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        NurseSet nurseManage = new NurseSet();
        PatientSet patientManage = new PatientSet();
        String inputChoice;
        int choice;
        do {
            System.out.println("HOSPITAL MANAGER:");
            System.out.println("1-Create a nurse");
            System.out.println("2-Find a nurse");
            System.out.println("3-Update a nurse");
            System.out.println("4-Delete a nurse");
            System.out.println("5-Add a patient");
            System.out.println("6-Display a patient");
            System.out.println("7-Sort a patient");
            System.out.println("8-Save data");
            System.out.println("9-Load data");
            System.out.println("10-Quit");
            do {
                System.out.print("Enter your choice:");
                inputChoice = scanner.nextLine();
            } while (!validate.checkNum(inputChoice)
                    || !(Integer.parseInt(inputChoice) >= 1
                    && Integer.parseInt(inputChoice) <= 10));
            
            choice = Integer.parseInt(inputChoice);

            switch (choice) {
                case 1:
                    nurseManage.createNurse();
                    break;
                case 2:
                    nurseManage.findNurse();
                    break;
                case 3:
                    nurseManage.updateNurse();
                    break;
                case 4:
                    nurseManage.deleteNurse();
                    break;
                case 5:
                    patientManage.addPatient();
                    break;
                case 6:
                    patientManage.displayPatients();
                    break;
                case 7:
                    patientManage.sortPatients();
                    break;
                case 8:
                    nurseManage.saveNurse();
                    patientManage.savePatient();
                    break;
                case 9:
                    nurseManage.loadNurse();
                    nurseManage.viewNurse();
                    patientManage.loadPatient();
                    patientManage.viewPatient();
                    break;
                case 10:
                    do {
                        System.out.println("Do you want to save[1/0-Y/N-T/F]: ");
                        inputChoice = scanner.nextLine();
                        inputChoice = inputChoice.toLowerCase();
                    } while (!inputChoice.matches("^[10yntf]{1}$"));

                    String isSave = inputChoice;
                    if ("1".equals(isSave) | "y".equals(isSave) | "t".equals(isSave)) {
                        nurseManage.saveNurse();
                        patientManage.savePatient();
                        System.out.println("Successfully save data");
                    }
                    System.out.println("End program");
                    break;
            }
        } while (choice != 10);
    }
}
