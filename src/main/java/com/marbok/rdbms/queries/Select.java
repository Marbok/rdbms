package com.marbok.rdbms.queries;

import java.util.Arrays;
import java.util.List;

import com.marbok.rdbms.database.Cell;
import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.inmemory.InMemoryTable;
import com.marbok.rdbms.database.Row;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.exception.InvalidQueryException;

public class Select extends Operation {
    public static final int CONDITION_LENGTH = 3;

    private String columnsString;
    private String[] columns;
    private String table;
    private String conditionString;
    private Condition condition;

    public Select(String query) {
        super(query);
    }

    @Override
    public Table execute(Database database) {
        final Row[] rows = database.getTable(table)
                .select(condition, columns);

        return new InMemoryTable(List.of(rows));
    }

    @Override
    protected void parseQuery(String query) {
        final String removeSelect = query.replace("select ", "");
        final String[] split = removeSelect.split(" from ", 2);
        columnsString = split[0].trim();
        columns = Arrays.stream(columnsString.trim().split(", "))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
        final String[] wheres = split[1].split("where", 2);
        table = wheres[0].trim();
        if (wheres.length <= 1) {
            return;
        }
        conditionString = wheres[1].trim();
        final String[] conditionTokens = Arrays.stream(conditionString.split(" "))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
        if (conditionTokens.length != CONDITION_LENGTH) {
            throw new InvalidQueryException(wheres[1] + "is not valid condition");
        }

        condition = row -> {
            String value = conditionTokens[2];

            final Cell<?> cell = row.getCell(conditionTokens[0]);
            switch (conditionTokens[1]) {
                case ">":
                    return cell.gt(value);
                case "<":
                    return cell.lt(value);
                case "=":
                    return cell.eq(value);
                case "<>":
                    return cell.ne(value);
                default:
                    throw new UnsupportedOperationException(conditionTokens[1] + " not supported yet");
            }
        };
    }

    @Override
    public String toString() {
        return "Select{" +
                "columns='" + columnsString + '\'' +
                ", table='" + table + '\'' +
                ", condition='" + conditionString + '\'' +
                '}';
    }
}
