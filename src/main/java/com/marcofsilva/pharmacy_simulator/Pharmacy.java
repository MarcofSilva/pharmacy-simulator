package com.marcofsilva.pharmacy_simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * This Entity manage the administration of drugs to its patients.
 * Basically providing the rules about drugs and state transitions.
 */
public class Pharmacy {

    /**
     * List of patients managed by the pharmacy
     */
    private List<Patient> patients = new ArrayList<Patient>();

    /**
     * @return the pharmacy patients
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Set the pharmacy patients
     * @param patients the patients for the pharmacy
     */
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Administer the drugs prescribed to each patient.
     * Rules about the state transitions caused by the drugs are defined here.
     */
    public void administerDrugs() {
        for (Patient patient : this.patients) {
            PatientState patientState = patient.getState();
            List<Drug> drugsToAdminister = patient.getDrugsToAdminister();

            // Transitions between states with priority ----------------------------------------------------------------
            if (drugsToAdminister.contains(Drug.PARACETAMOL) && drugsToAdminister.contains(Drug.ASPIRIN)) {
                patientState = PatientState.DEAD;
            }

            // Remaining transition by current state -------------------------------------------------------------------
            if (patientState == PatientState.FEVER) {
                if (
                    (drugsToAdminister.contains(Drug.ASPIRIN) && !drugsToAdminister.contains(Drug.PARACETAMOL)) ||
                    !(drugsToAdminister.contains(Drug.ASPIRIN) && drugsToAdminister.contains(Drug.PARACETAMOL))
                ) {
                    patientState = PatientState.HEALTHY;
                }
            }
            if (patientState == PatientState.DIABETES) {
                if (!drugsToAdminister.contains(Drug.INSULIN)) {
                    patientState = PatientState.DEAD;
                }
            }
            if (patientState == PatientState.TUBERCULOSIS) {
                if (drugsToAdminister.contains(Drug.ANTIBIOTIC)) {
                    patientState = PatientState.HEALTHY;
                }
            }
            if (patientState == PatientState.HEALTHY) {
                if (drugsToAdminister.contains(Drug.INSULIN) && drugsToAdminister.contains(Drug.ANTIBIOTIC)) {
                    patientState = PatientState.FEVER;
                }
            }
            if (patientState == PatientState.DEAD) {
                patientState = FlyingSpaghettiMonster.mightShowNoodlyPower(patientState);
            }

            // Update patient with new state after drugs administered
            patient.setState(patientState);
        }
    }
}
