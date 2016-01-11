package ru.unn.agile.IntersectionFinder.model;

public class Line {
    private final Vector3D point;
    private final Vector3D vector;

    public Line(final Vector3D point, final Vector3D vector) {
        this.point = point;
        this.vector = vector;
    }

    public Vector3D getPoint() {
        return point;
    }

    public Vector3D getVector() {
        return vector;
    }
}
