package ru.unn.agile.IntersectionFinder.model;

public class Plane {
    private final Vector3D point;
    private final Vector3D normal;
    private static final double EPS = Double.MIN_VALUE;

    public Plane(final Vector3D point, final Vector3D normal) {
        this.point = point;
        this.normal = normal;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public Vector3D getPoint() {
        return point;
    }

    public boolean isPointOnPlane(final Vector3D pointToCheck) {
        return Math.abs(normal.dot(pointToCheck) - normal.dot(point)) < EPS;
    }
}
