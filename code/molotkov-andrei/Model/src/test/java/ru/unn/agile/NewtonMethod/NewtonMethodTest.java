package ru.unn.agile.NewtonMethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class NewtonMethodTest {
    @RunWith(Parameterized.class)
    public static class WhenCanFindRoot {
        private NewtonMethod newtonMethod;
        private final String baseFunction;
        private final String derivativeFunction;
        private final double leftPoint;
        private final double rightPoint;
        private final double expectedRoot;
        private final double delta = 0.001;

        public WhenCanFindRoot(final String baseFunction,
                               final String derivativeFunction,
                               final double expectedRoot,
                               final double leftPoint,
                               final double rightPoint) {
            this.baseFunction = baseFunction;
            this.derivativeFunction = derivativeFunction;
            this.expectedRoot = expectedRoot;
            this.leftPoint = leftPoint;
            this.rightPoint = rightPoint;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFunctionsAndRangeWhenIsRoot() {
            return Arrays.asList(new Object[][]{
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -1.585, -2, 0},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -4.414, -5, -4},
                    {"(x+3)*(x-5)+(x-2)=", "2*x-1=", 4.653, 4, 5},
                    {"(x+3)*(x-5)+(x-2)=", "2*x-1=", -3.653, -4, -3}
            });
        }

        @Before
        public void initializeMethod() {
            newtonMethod = new NewtonMethod(baseFunction, derivativeFunction);
        }

        @Test
        public void whenCanFindRoot() {
            Double root = newtonMethod.searchRoot(leftPoint, rightPoint);

            assertEquals(expectedRoot, root, delta);
        }
    }
    @RunWith(Parameterized.class)
    public static class WhenCanNotFindRoot {
        private NewtonMethod newtonMethod;
        private final String baseFunction;
        private final String derivativeFunction;
        private final double leftPoint;
        private final double rightPoint;

        public WhenCanNotFindRoot(final String baseFunction,
                                  final String derivativeFunction,
                                  final double leftPoint,
                                  final double rightPoint) {
            this.baseFunction = baseFunction;
            this.derivativeFunction = derivativeFunction;
            this.leftPoint = leftPoint;
            this.rightPoint = rightPoint;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFunctionsAndRangeWhenNotRoot() {
            return Arrays.asList(new Object[][]{
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -2, -2},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -2.9, -1.7},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -4, -1}
            });
        }

        @Before
        public void initializeMethod() {
            newtonMethod = new NewtonMethod(baseFunction, derivativeFunction);
        }

        @Test(expected = IllegalArgumentException.class)
        public void whenCanNotFindRoot() {
            newtonMethod.searchRoot(leftPoint, rightPoint);
        }
    }
}
