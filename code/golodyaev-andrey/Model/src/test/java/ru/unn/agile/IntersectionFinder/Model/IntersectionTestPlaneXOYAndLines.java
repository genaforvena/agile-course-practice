package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntersectionTestPlaneXOYAndLines {
    private Plane planeXOY;

    @Before
    public void setUpPlane() {
        Vector3D pointPlane = new Vector3D(0.0, 0.0, 0.0);
        Vector3D normalPlane = new Vector3D(0.0, 0.0, 1.0);
        planeXOY = new Plane(pointPlane, normalPlane);
    }

    @Test
    public void canFindPlaneXOYAndLineOXOffsetDoesntIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 3.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOXOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOXOffset =
                new IntersectionFinder(lineOXOffset, planeXOY);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOYAndLineOXOffset.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.NoIntersection);
    }

    @Test
    public void canFindPlaneXOYAndLineOZIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(0.0, 0.0, 1.0);
        Line lineOZ = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOZ =
                new IntersectionFinder(lineOZ, planeXOY);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOYAndLineOZ.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.OneIntersection);
    }

    @Test
    public void canFindPlaneXOYContainsLineOX() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOX = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOX =
                new IntersectionFinder(lineOX, planeXOY);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOYAndLineOX.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.LineOnThePlane);
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXOYAndLineOXOffset() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 3.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOXOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOXOffset =
                new IntersectionFinder(lineOXOffset, planeXOY);

        intersectionPlaneXOYAndLineOXOffset.getIntersectionPoint();
    }

    @Test
    public void canGetIntersectionPlaneXOYAndLineOZ() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(0.0, 0.0, 1.0);
        Line lineOZ = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOZ =
                new IntersectionFinder(lineOZ, planeXOY);

        assertEquals(intersectionPlaneXOYAndLineOZ.getIntersectionPoint(),
                new Vector3D(0.0, 0.0, 0.0));
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXOYAndLineOX() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOX = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOYAndLineOX =
                new IntersectionFinder(lineOX, planeXOY);

        intersectionPlaneXOYAndLineOX.getIntersectionPoint();
    }
}
