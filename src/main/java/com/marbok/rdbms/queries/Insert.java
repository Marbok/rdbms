package com.marbok.rdbms.queries;

import java.util.Arrays;

import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Row;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.exception.InvalidQueryException;

public class Insert extends Operation {

    private String tableName;
    private String valuesString;
    private String[] values;

    public Insert(String query) {
        super(query);
    }

    @Override
    public Table execute(Database database) {
        final Table table = database.getTable(tableName);
        final Column[] columns = table.getColumns();

        if (columns.length != values.length) {
            throw new InvalidQueryException("Support only insert with all column");
        }

        var rowBuilder = new Row.RowBuilder();
        for (int i = 0; i < values.length; i++) {
            rowBuilder.addValue(columns[i], values[i]);
        }

        table.insertRow(rowBuilder.build());
        return null;
    }

    @Override
    protected void parseQuery(String query) {
        final String removeQuery = query.replace("insert into", "");
        final String[] tokens = removeQuery.split("values", 2);
        if (tokens.length != 2) {
            throw new InvalidQueryException(query);
        }
        tableName = tokens[0].trim();
        valuesString = tokens[1].trim();
        int openBracket = valuesString.indexOf('(');
        int closeBracket = valuesString.indexOf(')');
        if (openBracket != 0 && closeBracket != valuesString.length() - 1) {
            throw new InvalidQueryException("Not valid values block: '" + valuesString + "'");
        }
        values = Arrays.stream(valuesString.substring(openBracket + 1, closeBracket)
                        .split(", "))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .toArray(String[]::new);
    }

    @Override
    public String toString() {
        return "Insert{" +
                "tableName='" + tableName + '\'' +
                ", valuesString='" + valuesString + '\'' +
                '}';
    }
}
