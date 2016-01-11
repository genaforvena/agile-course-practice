package ru.unn.agile.IntersectionOfSegments.model;

public class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    Point(final Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void set(final Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long longX = Double.doubleToLongBits(x);
        long longY = Double.doubleToLongBits(y);
        result = prime * result + (int) (longX ^ (longX >>> (prime + 1)));
        result = prime * result + (int) (longY ^ (longY >>> (prime + 1)));
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        Point point = (Point) object;
        return this.x == point.getX() && this.y == point.getY();
    }

    @Override
    public String toString() {
        return "(" + Double.toString(x) + "; " + Double.toString(y) + ")";
    }

    public void swapXY() {
        double tmp = x;
        x = y;
        y = tmp;
    }
}
