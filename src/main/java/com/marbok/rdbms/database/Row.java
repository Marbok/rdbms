package com.marbok.rdbms.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marbok.rdbms.database.cells.Cell;
import com.marbok.rdbms.database.metainformation.Column;
import com.marbok.rdbms.exception.ColumnNotExistsException;

public class Row {

    private final Map<String, Cell<?>> store = new HashMap<>();
    private Column[] columns;

    private Row() {
        // use builder
    }

    public static class RowBuilder {

        private final List<String> values = new ArrayList<>();
        private final List<Column> columns = new ArrayList<>();

        public void addValue(Column column, String value) {
            columns.add(column);
            values.add(value);
        }

        public Row build() {
            final Row row = new Row();
            for (int i = 0; i < values.size(); i++) {
                final Column column = columns.get(i);
                final String value = values.get(i);
                row.store.put(column.getName(), column.getType().createCell(value));
            }
            row.columns = columns.toArray(Column[]::new);
            return row;
        }
    }

    /**
     * precondition: column exists.
     *
     * @return
     */
    public Cell<?> getCell(String column) {
        final Cell<?> cell = store.get(column);
        if (cell == null) {
            throw new ColumnNotExistsException(column + " doesn't exists");
        }
        return cell;
    }

    public Column[] getColumns() {
        return columns;
    }
}
