package com.marbok.rdbms.database.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.marbok.rdbms.database.cells.Cell;
import com.marbok.rdbms.database.cells.StringCell;
import com.marbok.rdbms.exception.InvalidTypeException;

public class StringType extends Type<String> {

    public static final StringType INSTANCE = new StringType();

    private static final Pattern PATTERN = Pattern.compile("'\\w*'");
    private static final String EMPTY_VALUE = "''";
    private static final String EMPTY_STRING = "";

    private StringType() {
        // singleton
    }

    @Override
    protected String tryCast(String value) {
        if (EMPTY_VALUE.equals(value)) {
            return EMPTY_STRING;
        }
        checkString(value);
        return value.substring(1, value.length() - 1);
    }

    @Override
    public Cell<String> createCell(String value) {
        return new StringCell(cast(value));
    }

    private void checkString(String value) {
        final Matcher matcher = PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new InvalidTypeException(value + "is not string");
        }
    }


}
