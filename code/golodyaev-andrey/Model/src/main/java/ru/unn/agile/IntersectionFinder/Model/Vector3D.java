package ru.unn.agile.IntersectionFinder.model;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(final String vectorText) {
        this.parse(vectorText);
    }

    @Override
    public int hashCode() {
        final int a = 7;
        final int b = 71;
        final int c = 713;
        long temp = Double.doubleToLongBits(x);
        int result = (int) (a + temp);
        temp = Double.doubleToLongBits(y);
        result = (int) (result * b + temp);
        temp = Double.doubleToLongBits(z);
        result = (int) (result * c + temp);
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        Vector3D vector = (Vector3D) object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    public double dot(final Vector3D vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public Vector3D mul(final double alpha) {
        return new Vector3D(x * alpha, y * alpha, z * alpha);
    }

    public Vector3D add(final Vector3D vector) {
        return new Vector3D(x + vector.x, y + vector.y, z + vector.z);
    }

    private void parseX(final String vectorText) {
        int index = vectorText.indexOf(";");
        if (index <= 0) {
            throw new NumberFormatException();
        }
        String x = vectorText.substring(0, vectorText.indexOf(";"));
        x = x.trim();
        this.x = Double.parseDouble(x);
    }

    private void parseY(final String vectorText) {
        int index1 = vectorText.indexOf(";");
        if (index1 == vectorText.length() - 1) {
            throw new NumberFormatException();
        }
        int index2 = vectorText.indexOf(";", index1 + 1);
        if (index2 == -1) {
            throw new NumberFormatException();
        }
        String y = vectorText.substring(index1 + 1, index2);
            y = y.trim();
            this.y = Double.parseDouble(y);
    }

    private void parseZ(final String vectorText) {
        int index = vectorText.indexOf(";");
        index = vectorText.indexOf(";", index + 1);
        if (index == vectorText.length() - 1) {
            throw new NumberFormatException();
        }
        String z = vectorText.substring(index + 1);
        z = z.trim();
        this.z = Double.parseDouble(z);
    }

    public void parse(final String vectorText) {
        parseX(vectorText);
        parseY(vectorText);
        parseZ(vectorText);
    }

    public String toString() {
        return x + "; " + y + "; " + z;
    }
}
