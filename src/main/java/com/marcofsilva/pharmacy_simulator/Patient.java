package com.marcofsilva.pharmacy_simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a Patient
 */
public class Patient {

    /**
     * Current state of the patient
     */
    private PatientState state;
    /**
     * List of drugs to administer to the patient
     */
    private List<Drug> drugsToAdminister = new ArrayList<>();


    Patient(PatientState state) {
        this.state = state;
    }

    /**
     * @return the current state of the patient
     */
    public PatientState getState() {
        return state;
    }

    /**
     * Set a new state for the patient
     * @param state the new state for the patient
     */
    public void setState(PatientState state) {
        this.state = state;
    }

    /**
     * @return the drugs to administer to the patient
     */
    public List<Drug> getDrugsToAdminister() {
        return drugsToAdminister;
    }

    /**
     * Set the drugs to administer to the patient
     * @param drugsToAdminister the drugs to administer to the patient
     */
    public void setDrugsToAdminister(List<Drug> drugsToAdminister) {
        this.drugsToAdminister = drugsToAdminister;
    }
}
