package com.marcofsilva.pharmacy_simulator;

import java.util.*;

/**
 * PharmacySimulator holds the main method and take care of validating and parsing the input,
 * as well as generating the output.
 */
public class PharmacySimulator {

    /**
     * Pharmacy instance, which will manage patients and administration of drugs
     */
    private static final Pharmacy pharmacy = new Pharmacy();
    /**
     * Counter of patients for the available states. Used during the generation of the output
     */
    private static final Map<String, Integer> patientStateCounter = new HashMap<String, Integer>();

    /**
     * The main method. Will not allow more than two arguments.
     * @param args the command line arguments. First argument: patient states; second argument: drugs prescribed
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments passed. At least one is needed");
        } else if (args.length == 1) {
            System.out.println(runSimulation(args[0], ""));
        } else if (args.length == 2) {
            System.out.println(runSimulation(args[0], args[1]));
        } else {    // args.length > 2
            System.out.println("Too many arguments. Only two are allowed.");
        }
    }

    /**
     * Execute the simulation of patient states transitions with the administration of prescribed drugs
     * @param statesArg the input argument with patient states
     * @param drugsArg the input argument with prescribed drugs
     * @return the output result string
     */
    private static String runSimulation(String statesArg, String drugsArg) {
        // Validation
        if (statesArg.isBlank()) {
            return generateOutput();
        }

        // Parsing arguments
        String[] patientsStatesCodes = statesArg.split(",");
        String[] drugsToAdministerCodes = {};
        if (!drugsArg.isBlank()) {
            drugsToAdministerCodes = drugsArg.split(",");
        }

        // Parse drugs to administer
        List<Drug> drugsToAdminister = new ArrayList<Drug>();
        Drug currentDrug;
        for (String drugCode : drugsToAdministerCodes) {
            try {
                currentDrug = Drug.findByCode(drugCode);
                drugsToAdminister.add(currentDrug);
            } catch (IllegalArgumentException ex) {
                return ex.getMessage() + " Valid codes:" + Drug.getAllCodes();
            }
        }

        // Create patients with given current states and drugs to administer
        List<Patient> patients = new ArrayList<Patient>();
        for (String patientStateCode : patientsStatesCodes) {
            try {
                PatientState currentPatientState = PatientState.findByCode(patientStateCode);
                Patient currentPatient = new Patient(currentPatientState);
                currentPatient.setDrugsToAdminister(drugsToAdminister);
                patients.add(currentPatient);
            } catch (IllegalArgumentException ex) {
                return ex.getMessage() + " Valid codes:" + PatientState.getAllCodes();
            }
        }

        pharmacy.setPatients(patients);
        pharmacy.administerDrugs();

        return generateOutput();
    }

    /**
     * Update the patient states counters map with the current patient states, and get the output
     * @return the output result string
     */
    private static String generateOutput() {
        // Initialize patientsPerStateCode map
        for (String patientStateCode : PatientState.getAllCodes()) {
            patientStateCounter.put(patientStateCode, 0);
        }
        // Update patient counters by state in map
        for (Patient patient : pharmacy.getPatients()) {
            patientStateCounter.computeIfPresent(patient.getState().getCode(), (key, value) -> value + 1);
        }
        return formatOutputString();
    }

    /**
     * Generate a formatted string from a map with patient states counters
     * @return the output result string
     */
    private static String formatOutputString() {
        StringBuilder outputResult = new StringBuilder();
        for (String stateCode: PatientState.getAllCodes()) {
            if (outputResult.length() != 0) {
                // Prefix state counter with a comma (,) except for the first element
                outputResult.append(",");
            }
            outputResult.append(stateCode);
            outputResult.append(":");
            outputResult.append(patientStateCounter.get(stateCode));
        }
        return outputResult.toString();
    }
}
