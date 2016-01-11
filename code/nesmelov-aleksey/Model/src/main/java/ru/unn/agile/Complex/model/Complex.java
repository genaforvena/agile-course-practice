package ru.unn.agile.Complex.model;

public class Complex {
    private double real = 0.0;
    private double imaginary = 0.0;

    public Complex(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(final double real) {
        this.real = real;
    }

    public Complex() {
        real = 0.0;
        imaginary = 0.0;
    }

    @Override
    public boolean equals(final Object object) {
        Complex complexNumber = (Complex) object;
        return this.real == complexNumber.real
               && this.imaginary == complexNumber.imaginary;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        long longReal = Double.doubleToLongBits(real);
        long longImaginary = Double.doubleToLongBits(imaginary);

        int result = prime * (int) (longReal ^ (longReal >>> (prime + 1)))
                     + (int) (longImaginary ^ (longImaginary >>> (prime + 1)));

        return result;
    }

    @Override
    public String toString() {
        String result = "";
        if (isZero()) {
            return "0.0";
        }
        if (Math.abs(real) > Double.MIN_VALUE) {
            result += Double.toString(real);
        }
        if (Math.abs(imaginary) > Double.MIN_VALUE) {
            if (Math.abs(real) > Double.MIN_VALUE
                && imaginary > Double.MIN_VALUE) {
                result += "+";
            }
            result += Double.toString(imaginary) + "i";
        }
        return result;
    }

    public void setReal(final double real) {
        this.real = real;
    }

    public void setImaginary(final double imaginary) {
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public boolean isZero() {
        return Math.abs(real) < Double.MIN_VALUE
               && Math.abs(imaginary) < Double.MIN_VALUE;
    }

    public Complex add(final Complex other) {
        Complex result = new Complex(real + other.real, imaginary + other.imaginary);

        return result;
    }
    public Complex subtract(final Complex other) {
        Complex result = new Complex(real - other.real, imaginary - other.imaginary);

        return result;
    }
    public Complex multiply(final Complex other) {
        Complex result = new Complex(real * other.real - imaginary * other.imaginary,
                                     imaginary * other.real + real * other.imaginary);

        return result;
    }
    public Complex divide(final Complex other) {
        Complex result;

        if (other.isZero()) {
            throw new IllegalArgumentException("Divider can't be zero!");
        } else {
            result = new Complex((real * other.real + imaginary * other.imaginary)
                               / (other.real * other.real + other.imaginary * other.imaginary),
                                 (imaginary * other.real - real * other.imaginary)
                               / (other.real * other.real + other.imaginary * other.imaginary));
        }
        return result;
    }
}
