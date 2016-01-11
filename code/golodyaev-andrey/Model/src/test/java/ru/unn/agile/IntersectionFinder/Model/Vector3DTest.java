package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3DTest {
    private final double delta = Double.MIN_VALUE;
    private Vector3D vector;

    @Before
    public void setUp() {
        vector = new Vector3D(1.0, 2.0, 3.0);
    }

    @Test
    public void canCreateVector3D() {
        assertNotNull(vector);
    }

    @Test
    public void canGetCoordX() {
        assertEquals(1.0, vector.getX(), delta);
    }

    @Test
    public void canGetCoordY() {
        assertEquals(2.0, vector.getY(), delta);
    }

    @Test
    public void canGetCoordZ() {
        assertEquals(3.0, vector.getZ(), delta);
    }

    @Test
    public void canSetCoordX() {
        vector.setX(4.0);

        assertEquals(4.0, vector.getX(), delta);
    }

    @Test
    public void canSetCoordY() {
        vector.setY(4.0);

        assertEquals(4.0, vector.getY(), delta);
    }

    @Test
    public void canSetCoordZ() {
        vector.setZ(4.0);

        assertEquals(4.0, vector.getZ(), delta);
    }

    @Test
    public void canDot() {
        Vector3D secondVector = new Vector3D(4.0, 5.0, 6.0);

        double dotResult = vector.dot(secondVector);

        assertEquals(32.0, dotResult, delta);
    }

    @Test
    public void canCheckThatEquals() {
        Vector3D secondVector = new Vector3D(1.0, 2.0, 3.0);

        assertEquals(vector, secondVector);
    }

    @Test
    public void canCheckThatNotEquals() {
        Vector3D secondVector = new Vector3D(2.0, 2.0, 3.0);

        assertNotEquals(vector, secondVector);
    }

    @Test
    public void canMultiplyToDouble() {
        assertEquals(vector.mul(2), new Vector3D(2.0, 4.0, 6.0));
    }

    @Test
    public void canAdd() {
        Vector3D secondVector = new Vector3D(4.0, 5.0, 6.0);

        assertEquals(vector.add(secondVector), new Vector3D(5.0, 7.0, 9.0));
    }

    @Test
    public void sameVectorsHasSameHashCodes() {
        Vector3D secondVector = new Vector3D(1.0, 2.0, 3.0);

        assertEquals(vector.hashCode(), secondVector.hashCode());
    }
}
