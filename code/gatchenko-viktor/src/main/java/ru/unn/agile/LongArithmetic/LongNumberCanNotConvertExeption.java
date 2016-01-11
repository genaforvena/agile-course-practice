package ru.unn.agile.LongArithmetic;

public class LongNumberCanNotConvertExeption extends RuntimeException {

    private static final long serialVersionUID = -7534820595007858302L;

    public LongNumberCanNotConvertExeption(final String message) {
        super(message);
    }
}
