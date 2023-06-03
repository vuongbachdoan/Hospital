/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import models.Patient;

/**
 *
 * @author user
 */
public class Validate {

    public boolean checkText(String input) {
        return input.matches("^[a-zA-Z0-9]+$");
    }

    public boolean checkName(String input) {
        return input.matches("^[a-zA-Z ]+$");
    }

    public boolean checkDepartment(String departmentName) {
        if (departmentName.length() < 3 || departmentName.length() > 50) {
            return false;
        }
        return true;
    }

    public boolean checkAge(String age) {
        if (age.matches("^[0-9]+$") && Integer.parseInt(age) > 0) {
            return true;
        }
        return false;
    }

    public boolean checkNum(String num) {
        return num.matches("^[0-9]+$");
    }

    public boolean checkDouble(String num) {
        return num.matches("^-?[0-9]+(.[0-9]+)?$") && Double.parseDouble(num) >= 0.0;
    }

    public static boolean checkDate(String dateString) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        dateFormatter.setLenient(false);
        try {
            dateFormatter.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean checkDateBefore(String dateString1, String dateString2) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = dateFormatter.parse(dateString1);
            Date date2 = dateFormatter.parse(dateString2);
            return date1.before(date2);
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean checkNurseValid(String nurseId, HashMap<String, Patient> patients) {
        int count = 0;
        for (Map.Entry<String, Patient> entry : patients.entrySet()) {
            if (entry.getValue().getNurseAssigned().equals(nurseId)) {
                count++;
            }
        }
        if (count == 2) {
            return false;
        } else {
            return true;
        }
    }
}
