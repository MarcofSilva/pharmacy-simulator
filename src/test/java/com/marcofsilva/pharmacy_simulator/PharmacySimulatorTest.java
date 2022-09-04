package com.marcofsilva.pharmacy_simulator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Tests for Pharmacy Simulator
 */
public class PharmacySimulatorTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        // Catch the output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Input validation tests ------------------------------------------------------------------------------------------

    @Test
    public void noArgsInvalid() {
        String[] args = new String[] {};
        PharmacySimulator.main(args);
        assertEquals("No arguments passed. At least one is needed\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void oneArgValid() {
        String[] args = new String[] {"H"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void oneArgInvalid() {
        String invalidState = "Ha";
        String[] args = new String[] {invalidState};
        PharmacySimulator.main(args);
        assertEquals(
                "Patient state code '" + invalidState + "' is invalid. Valid codes:[F, H, D, T, X]\r\n",
                outputStreamCaptor.toString()
        );
    }

    @Test
    public void twoArgsValid() {
        String[] args = new String[] {"H", "I"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void twoArgsSecondInvalid() {
        String invalidDrug = "In";
        String[] args = new String[] {"H", invalidDrug};
        PharmacySimulator.main(args);
        assertEquals(
                "Drug code '" + invalidDrug + "' is invalid. Valid codes:[As, An, I, P]\r\n",
                outputStreamCaptor.toString()
        );
    }

    @Test
    public void moreThanTwoArgsInvalid() {
        String[] args = new String[] {"H", "I", "An"};
        PharmacySimulator.main(args);
        assertEquals("Too many arguments. Only two are allowed.\r\n", outputStreamCaptor.toString());
    }

    // Use cases test --------------------------------------------------------------------------------------------------

    @Test
    public void diabeticsDieWithoutInsulin() {
        String[] args = new String[] {"D,D"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:0,D:0,T:0,X:2\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void paracetamolCuresFever() {
        String[] args = new String[] {"F", "P"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void insulinPreventsDiabeticDeath_antibioticsCureTuberculosis_insulinAndAntibioticsTurnHealthyInFever() {
        String[] args = new String[] {"T,F,D", "An,I"};
        PharmacySimulator.main(args);
        assertEquals("F:2,H:0,D:1,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

}
