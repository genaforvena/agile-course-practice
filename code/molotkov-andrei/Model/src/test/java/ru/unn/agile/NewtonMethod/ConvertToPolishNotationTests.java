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
public class ConvertToPolishNotationTests {
    @RunWith(Parameterized.class)
     public static class CanConvertToPolishNotation {
        private ConverterToPolishNotation converter;
        private final String infixFormula;
        private final String postfixFormula;

        public CanConvertToPolishNotation(final String infixFormula,
                                          final String postfixFormula) {
            this.infixFormula = infixFormula;
            this.postfixFormula = postfixFormula;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFormulas() {
            return Arrays.asList(new Object[][]{
                    {"x+x=", "xx+="},
                    {"x-x=", "xx-="},
                    {"x*x=", "xx*="},
                    {"x/x=", "xx/="},
                    {"3+x=", "3x+="},
                    {"48*x=", "48x*="},
                    {"x*x+x=", "xx*x+="},
                    {"5*x*x+2*x-3=", "5x*x*2x*+3-="},
                    {"(3+x)*x=", "3x+x*="},
                    {"x+(x-3)*y-35/(x+y)=", "xx3-y*+35xy+/-="}
            });
        }

        @Before
        public void initializeConverter() {
            converter = new ConverterToPolishNotation();
        }

        @Test
        public void canConvertPrimitiveOperationsToPolishNotation() {
            String polishNotation = converter.convert(infixFormula);
            assertEquals(postfixFormula, polishNotation);
        }
    }

    @RunWith(Parameterized.class)
    public static class WhenConvertToPolishNotation {
        private ConverterToPolishNotation converter;
        private final String infixFormula;

        public WhenConvertToPolishNotation(final String infixFormula) {
            this.infixFormula = infixFormula;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFormulas() {
            return Arrays.asList(new Object[][]{
                    {"="}, {"x+xx="}, {"x+x7="},
                    {"x+x(x-3)="}, {"1+3x="},
                    {"x+3(5-x)="}, {"x++x="},
                    {"x*(x+7-)="}, {"x+(/x+5)="},
                    {"(x-5)x="}, {"(x-5)5="},
                    {"*x-5="}, {"x+5)-3(="},
                    {"x+x-((5-x)="},
                    {"x+x-((5-x)-5)+x*6)="},
                    {"x^x="}
            });
        }

        @Before
        public void initializeConverter() {
            converter = new ConverterToPolishNotation();
        }

        @Test(expected = ArithmeticException.class)
        public void canNotConvertPrimitiveOperationsToPolishNotation() {
            converter.convert(infixFormula);
        }
    }
}
