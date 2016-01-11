package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntersectionTestPlaneXYZOffsetAndLines {
    private Plane planeXYZOffset;

    @Before
    public void setUpPlane() {
        Vector3D pointPlane = new Vector3D(1.0, 0.0, 0.0);
        Vector3D normalPlane = new Vector3D(1.0, 1.0, 1.0);
        planeXYZOffset = new Plane(pointPlane, normalPlane);
    }

    @Test
    public void canFindPlaneXYZOffsetAndParallelLineOffsetDoesntIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, -1.0, 0.0);
        Line parallelLineOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndParallelLineOffset =
                new IntersectionFinder(parallelLineOffset, planeXYZOffset);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXYZOffsetAndParallelLineOffset.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.NoIntersection);
    }

    @Test
    public void canFindPlaneXYZOffsetAndLineOXIntersect() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOX = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndLineOX =
                new IntersectionFinder(lineOX, planeXYZOffset);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXYZOffsetAndLineOX.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.OneIntersection);
    }

    @Test
    public void canFindPlaneXYZOffsetContainsLineOnThePlane() {
        Vector3D pointLine = new Vector3D(1.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, -1.0, 0.0);
        Line lineOnThePlane = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndLineOnThePlane =
                new IntersectionFinder(lineOnThePlane, planeXYZOffset);

        IntersectionFinder.TypeOfIntersection type =
                intersectionPlaneXYZOffsetAndLineOnThePlane.getTypeOfIntersection();

        assertEquals(type, IntersectionFinder.TypeOfIntersection.LineOnThePlane);
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXYZOffsetAndParallelLineOffset() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, -1.0, 0.0);
        Line parallelLineOffset = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndParallelLineOffset =
                new IntersectionFinder(parallelLineOffset, planeXYZOffset);

        intersectionPlaneXYZOffsetAndParallelLineOffset.getIntersectionPoint();
    }

    @Test
    public void canGetIntersectionPlaneXYZOffsetAndLineOX() {
        Vector3D pointLine = new Vector3D(0.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, 0.0, 0.0);
        Line lineOX = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndLineOX =
                new IntersectionFinder(lineOX, planeXYZOffset);

        assertEquals(intersectionPlaneXYZOffsetAndLineOX.getIntersectionPoint(),
                new Vector3D(1.0, 0.0, 0.0));
    }

    @Test(expected = Exception.class)
    public void canCatchExceptionIfGetIntersectionPlaneXYZOffsetAndLineOnThePlane() {
        Vector3D pointLine = new Vector3D(1.0, 0.0, 0.0);
        Vector3D vectorLine = new Vector3D(1.0, -1.0, 0.0);
        Line lineOnThePlane = new Line(pointLine, vectorLine);
        IntersectionFinder intersectionPlaneXYZOffsetAndLineOnThePlane =
                new IntersectionFinder(lineOnThePlane, planeXYZOffset);

        intersectionPlaneXYZOffsetAndLineOnThePlane.getIntersectionPoint();
    }

}
