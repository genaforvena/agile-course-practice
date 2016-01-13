package ru.unn.agile.QuadraticEquationSolver.Model;

import java.util.ArrayList;

public final class QuadraticEquationSolutionFormer {
    private QuadraticEquationSolutionFormer() {
    }

    public static float [] form(final ArrayList<Float> roots) {
        int length = roots.size();
        float [] answers = new float[length];
        for (int i = 0; i < length; i++) {
            answers[i] = roots.get(i);
        }
        return answers;
    }
}
