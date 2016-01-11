package ru.unn.agile.Metrics.model;

public class DistanceCalculator {

    public float calculateDistance(final float[] firstVector, final float[] secondVector,
                                   final Metric metric) {
        boolean isIncorrectInput = firstVector == null || secondVector == null
                || firstVector.length != secondVector.length;
        if (isIncorrectInput) {
            throw new IllegalArgumentException("Input is incorrect: null or different size");
        }
        boolean isTheSame = firstVector == secondVector;
        if (isTheSame) {
            return 0.0f;
        }
        switch (metric) {
            case RHO_INF:
                return calculateDistanceInRhoInf(firstVector, secondVector);
            case RHO_ONE:
                return calculateDistanceInRhoOne(firstVector, secondVector);
            default:
                return calculateMinkowskiDistance(firstVector, secondVector, metric.getParam());
        }
    }

    private float calculateDistanceInRhoInf(final float[] firstVector, final float[] secondVector) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            float diff = Math.abs(firstVector[i] - secondVector[i]);
            if (diff > distance) {
                distance = diff;
            }
        }
        return distance;
    }

    private float calculateDistanceInRhoOne(final float[] firstVector, final float[] secondVector) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            distance += Math.abs(firstVector[i] - secondVector[i]);
        }
        return distance;
    }

    private float calculateMinkowskiDistance(final float[] firstVector, final float[] secondVector,
                                             final double metricParameter) {
        float distance = 0.0f;
        for (int i = 0; i < firstVector.length; i++) {
            float diff = Math.abs(firstVector[i] - secondVector[i]);
            distance += ((float) Math.pow(((double) diff), metricParameter));
        }
        return ((float) Math.pow(((double) distance), 1.0 / metricParameter));
    }
}
