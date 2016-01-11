package ru.unn.agile.IntersectionOfSegments.model;

public class Intersection {
    private final TypeOfIntersection typeOfIntersection;
    private final Segment segment;

    Intersection(final TypeOfIntersection typeOfIntersection, final Segment segment) {
        this.typeOfIntersection = typeOfIntersection;
        this.segment = segment;
    }

    public TypeOfIntersection getTypeOfIntersection() {
        return typeOfIntersection;
    }

    public Segment getSegment() {
        return segment;
    }
}
