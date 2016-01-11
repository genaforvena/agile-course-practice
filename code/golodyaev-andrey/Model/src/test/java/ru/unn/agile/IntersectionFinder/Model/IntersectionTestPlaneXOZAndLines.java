package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntersectionTestPlaneXOZAndLines {
    private Plane planeXOZ;

    @Before
    public void setUpPlane() {
        Vector3D pointPlane = new Vector3D(0.0, 0.0, 0.0);
        Vector3D normalPlane = new Vector3D(0.0, 1.0, 0.0);
        planeXOZ = new Plane(pointPlane, normalPlane);
    }

    @Test
    public void canFindPlaneXOZAndLineXZOffsetDoesntIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 2.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 1.0);
        Line lineXZOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXZOffset =
                new IntersectionFinder(lineXZOffset, planeXOZ);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOZAndLineXZOffset.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.NoIntersection);
    }

    @Test
    public void canFindPlaneXOZAndLineXYOffsetIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 1.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 1.0, 0.0);
        Line lineXYOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXYOffset =
                new IntersectionFinder(lineXYOffset, planeXOZ);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOZAndLineXYOffset.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.OneIntersection);
    }

    @Test
    public void canFindPlaneXOZContainsLineXZ() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 1.0);
        Line lineXZ = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXZ =
                new IntersectionFinder(lineXZ, planeXOZ);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXOZAndLineXZ.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.LineOnThePlane);
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXOZAndLineXZOffset() {
        Vector3D pointLine = new Vector3D(0.0, 2.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 1.0);
        Line lineXZOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXZOffset =
                new IntersectionFinder(lineXZOffset, planeXOZ);

        intersectionPlaneXOZAndLineXZOffset.getIntersectionPoint();
    }

    @Test
    public void canGetIntersectionPlaneXOZAndLineXYOffset() {
        Vector3D pointLine = new Vector3D(0.0, 1.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 1.0, 0.0);
        Line lineXYOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXYOffset =
                new IntersectionFinder(lineXYOffset, planeXOZ);

        assertEquals(intersectionPlaneXOZAndLineXYOffset.getIntersectionPoint(),
                new Vector3D(-1.0, 0.0, 0.0));
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXOZAndLineXZ() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 1.0);
        Line lineXZ = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXOZAndLineXZ =
                new IntersectionFinder(lineXZ, planeXOZ);

        intersectionPlaneXOZAndLineXZ.getIntersectionPoint();
    }

}
