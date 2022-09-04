package com.marcofsilva.pharmacy_simulator;

import java.util.SplittableRandom;

/**
 * FlyingSpaghettiMonster is the Entity that, with a tiny probability,
 * might show a noodly power and resurrect a Dead patient.
 */
public class FlyingSpaghettiMonster {

    private static final SplittableRandom random = new SplittableRandom();
    /**
     * Probability of the Flying Spaghetti Monster to show his noodly power
     */
    private static final float probability = 0.000001f;

    /**
     * With a constant probability, will resurrect a Dead patient, leaving him Healthy.
     * Only Dead state might get a Healthy state returned.
     * @param state the state of the patient
     * @return the new state for the patient
     */
    public static PatientState mightShowNoodlyPower(PatientState state) {
        if (state.equals(PatientState.DEAD) && random.nextInt(Math.round(1/probability)) == 1) {
            return PatientState.HEALTHY;
        }
        return state;
    }
}
