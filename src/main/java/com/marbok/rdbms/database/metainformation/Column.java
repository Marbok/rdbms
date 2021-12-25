package com.marbok.rdbms.database.metainformation;

import java.util.Map;
import java.util.Objects;

import com.marbok.rdbms.database.types.DoubleType;
import com.marbok.rdbms.database.types.IntType;
import com.marbok.rdbms.database.types.StringType;
import com.marbok.rdbms.database.types.Type;
import com.marbok.rdbms.exception.InvalidQueryException;

public class Column {

    private final String name;
    private final Type<?> type;

    private static final Map<String, Type<?>> CLASSES = Map.of(
            "int", IntType.INSTANCE,
            "string", StringType.INSTANCE,
            "double", DoubleType.INSTANCE
    );

    public Column(String name, String type) {
        Objects.requireNonNull(name, "Column name is null");
        Objects.requireNonNull(type, "Column type is null");
        this.name = name;
        Type<?> aClass = CLASSES.get(type);
        if (aClass == null) {
            throw new InvalidQueryException(type + "is invalid type");
        }
        this.type = aClass;
    }

    public Column(String name, Type<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type<?> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Column column = (Column) o;
        return name.equals(column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
