package ru.unn.agile.Minesweeper.Infrastructure;

import org.hamcrest.Description;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regexp;

    public RegexMatcher(final String regexp) {
        this.regexp = regexp;
    }

    public boolean matches(final Object object) {
        return ((String) object).matches(regexp);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regexp);
    }

    public static Matcher<? super String> matchesPattern(final String regexp) {
        RegexMatcher matcher = new RegexMatcher(regexp);
        @SuppressWarnings("unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>) matcher;
        return castedMatcher;
    }
}

