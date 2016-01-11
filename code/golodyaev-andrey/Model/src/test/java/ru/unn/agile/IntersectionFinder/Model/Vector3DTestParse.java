package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3DTestParse {
    private String vectorText;
    private Vector3D vector;

    @Before
    public void setUp() {
        vector = new Vector3D(5.3, 7.0, -6.0);
    }

    @Test
    public void canParseCorrectString() {
        vectorText = " 1  ;-2 ; 3.2";

        vector.parse(vectorText);

        assertEquals(vector, new Vector3D(1.0, -2.0, 3.2));
    }

    @Test
    public void canCreateVectorFromString() {
        vectorText = " 1  ;-2 ; 3.2";

        vector = new Vector3D(vectorText);

        assertEquals(vector, new Vector3D(1.0, -2.0, 3.2));
    }

    @Test(expected = Exception.class)
    public void cantParseEmptyString() {
        vectorText = "";

        vector.parse(vectorText);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithLetter() {
        vectorText = " 1a  ; 2; 3";

        vector.parse(vectorText);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithSpaceAtTheMiddle() {
        vectorText = "1; 2 2; 3";

        vector.parse(vectorText);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithOneParameter() {
        vectorText = "1;";

        vector.parse(vectorText);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithTwoParameters() {
        vectorText = "1;2;";

        vector.parse(vectorText);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithTwoParametersWithoutSemicolon() {
        vectorText = "1;2";

        vector.parse(vectorText);
    }

    @Test
    public void canConvertToString() {
        assertEquals(vector.toString(), "5.3; 7.0; -6.0");
    }
}
