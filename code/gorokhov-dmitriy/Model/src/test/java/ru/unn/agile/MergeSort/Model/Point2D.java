package ru.unn.agile.MergeSort.Model;

class Point2D implements Comparable<Point2D> {
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object object) {
        Point2D point = (Point2D) object;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(final Point2D point) {
        if (this.x < point.x) {
            return -1;
        }

        if (this.x > point.x) {
            return 1;
        }

        if (this.y < point.y) {
            return -1;
        }

        if (this.y > point.y) {
            return 1;
        }

        return 0;
    }

    private double x;
    private double y;
}
