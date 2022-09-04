package com.marcofsilva.pharmacy_simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Available drugs constants. This is where to add new drugs if needed.
 */
public enum Drug {
    ASPIRIN("As", "Aspirin"),
    ANTIBIOTIC("An", "Antibiotic"),
    INSULIN ("I", "Insulin"),
    PARACETAMOL ("P", "Paracetamol");

    /**
     * Drug short code
     */
    private final String code;
    /**
     * Drug name
     */
    private final String description;

    Drug(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return the code associated with the drug
     */
    public String getCode() {
        return code;
    }

    /**
     * @return drug description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return all available drugs codes
     */
    public static List<String> getAllCodes() {
        List<String> drugCodes = new ArrayList<>();
        for (Drug drug : values()) {
            drugCodes.add(drug.code);
        }
        return drugCodes;
    }

    /**
     * Find the drug associated with a given code. The search by code is case-sensitive.
     * @param code the code associated with the drug
     * @return the drug associated with the given code
     * @throws IllegalArgumentException if an unknown code is passed as an argument
     */
    public static Drug findByCode(String code) throws IllegalArgumentException {
        Drug result = null;
        for (Drug drug : values()) {
            if (drug.code.equals(code)) {
                result = drug;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("Drug code '" + code + "' is invalid.");
        }
        return result;
    }
}
