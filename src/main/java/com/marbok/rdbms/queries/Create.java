package com.marbok.rdbms.queries;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.exception.InvalidQueryException;

public class Create extends Operation {

    private String tableName;
    private String columnString;
    private Column[] columns;

    public Create(String query) {
        super(query);
    }

    @Override
    public Table execute(Database database) {
        database.createTable(tableName, columns);
        return null;
    }

    @Override
    protected void parseQuery(String query) {
        String removeSysWords = query.trim().replace("create table", "");
        int openBracket = removeSysWords.indexOf("(");
        if (openBracket == -1) {
            throw new InvalidQueryException(query);
        }
        tableName = removeSysWords.substring(0, openBracket).trim();
        int closeBracket = removeSysWords.indexOf(")");
        columnString = removeSysWords.substring(openBracket + 1, closeBracket);
        columns = Arrays.stream(columnString.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(s -> s.split("\\s+"))
                .peek(this::checkColumnLength)
                .map(tokens -> new Column(tokens[0], tokens[1]))
                .toArray(Column[]::new);
        columnsNotEmpty();
        validateUniqueColumns();
    }

    private void columnsNotEmpty() {
        if (columns.length == 0) {
            throw new InvalidQueryException("Empty columns section: " + query);
        }
    }

    private void validateUniqueColumns() {
        final Map<String, Long> notUniqueColumns = Arrays.stream(columns)
                .collect(Collectors.groupingBy(Column::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (!notUniqueColumns.isEmpty()) {
            throw new InvalidQueryException(String.join(", ", notUniqueColumns.keySet())
                    + " not unique column in query: " + query);
        }
    }

    private void checkColumnLength(String[] tokens) {
        if (tokens.length != 2) {
            throw new InvalidQueryException(query);
        }
    }

    @Override
    public String toString() {
        return "Create{" +
                "tableName='" + tableName + '\'' +
                ", columnString='" + columnString + '\'' +
                '}';
    }
}
