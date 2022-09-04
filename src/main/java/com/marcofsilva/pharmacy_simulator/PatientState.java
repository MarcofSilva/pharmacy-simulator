package com.marcofsilva.pharmacy_simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Available patient states constants. This is where to add new states if needed.
 */
public enum PatientState {
    FEVER       ("F", "Fever"),
    HEALTHY     ("H", "Healthy"),
    DIABETES    ("D", "Diabetes"),
    TUBERCULOSIS("T", "Tuberculosis"),
    DEAD        ("X", "Dead");

    /**
     * Patient state short code
     */
    private final String code;
    /**
     * Patient state name
     */
    private final String description;

    PatientState(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return the code associated with the patient state
     */
    public String getCode() {
        return code;
    }

    /**
     * @return patient state description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return all available patient states codes
     */
    public static List<String> getAllCodes() {
        List<String> codes = new ArrayList<>();
        for (PatientState state : values()) {
            codes.add(state.code);
        }
        return codes;
    }

    /**
     * Find the patient state associated with the given code. The search by code is case-sensitive
     * @param code the code associated with the patient state
     * @return the patient state associated with the given code
     * @throws IllegalArgumentException if an unknown code is passed as an argument
     */
    public static PatientState findByCode(String code) throws IllegalArgumentException {
        PatientState result = null;
        for (PatientState state : values()) {
            if (state.code.equals(code)) {
                result = state;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("Patient state code '" + code + "' is invalid.");
        }
        return result;
    }
}
