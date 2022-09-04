package com.marcofsilva.pharmacy_simulator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Unit tests for Pharmacy
 */
public class PharmacyTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void administerDrugs_aspirinCuresFever() {
        String[] args = new String[] {"F", "As"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void administerDrugs_antibioticCuresTuberculosis() {
        String[] args = new String[] {"T", "An"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void administerDrugs_insulinPreventsDiabeticDeath() {
        String[] args = new String[] {"D", "I"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:0,D:1,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void administerDrugs_diabeticDiesWithoutInsulin() {
        String[] args = new String[] {"D"};
        PharmacySimulator.main(args);

        List<String> possibleOutputs = new ArrayList<String>();
        possibleOutputs.add("F:0,H:0,D:0,T:0,X:1\r\n");
        possibleOutputs.add("F:0,H:1,D:0,T:0,X:0\r\n"); // Flying Spaghetti Monster resurrects

        assertTrue(possibleOutputs.contains(outputStreamCaptor.toString()));
    }

    @Test
    public void administerDrugs_InsulinAndAntibioticCauseFeverToHealthy() {
        String[] args = new String[] {"H", "I,An"};
        PharmacySimulator.main(args);
        assertEquals("F:1,H:0,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void administerDrugs_paracetamolCuresFever() {
        String[] args = new String[] {"F", "P"};
        PharmacySimulator.main(args);
        assertEquals("F:0,H:1,D:0,T:0,X:0\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void administerDrugs_paracetamolAndAspirinKills() {
        String[] args = new String[] {"H", "P,As"};
        PharmacySimulator.main(args);

        List<String> possibleOutputs = new ArrayList<String>();
        possibleOutputs.add("F:0,H:0,D:0,T:0,X:1\r\n");
        possibleOutputs.add("F:0,H:1,D:0,T:0,X:0\r\n"); // Flying Spaghetti Monster resurrects

        assertTrue(possibleOutputs.contains(outputStreamCaptor.toString()));
    }
}
