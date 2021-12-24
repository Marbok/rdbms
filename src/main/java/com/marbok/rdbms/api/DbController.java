package com.marbok.rdbms.api;

import java.util.Map;
import java.util.function.Function;

import com.marbok.rdbms.database.Database;
import com.marbok.rdbms.database.Table;
import com.marbok.rdbms.database.inmemory.InMemoryDatabase;
import com.marbok.rdbms.exception.InvalidQueryException;
import com.marbok.rdbms.queries.Create;
import com.marbok.rdbms.queries.Insert;
import com.marbok.rdbms.queries.Operation;
import com.marbok.rdbms.queries.Select;

public class DbController {

    private final Map<String, Function<String, Operation>> operations = Map.of(
            "select", Select::new,
            "create", Create::new,
            "insert", Insert::new
    );

    private Database database;

    /**
     * precondition: query is valid
     *
     * @return
     */
    public Result execute(String query) {

        final String formatQuery = query.trim().toLowerCase();

        final int firstSpace = formatQuery.indexOf(' ');

        if (firstSpace == -1) {
            throw new InvalidQueryException(query);
        }

        Function<String, Operation> operationF = operations.get(formatQuery.substring(firstSpace));
        if (operationF == null) {
            throw new InvalidQueryException(query);
        }

        Table table = operationF.apply(formatQuery)
                .execute(database);

        return new ResultImpl(table);
    }

    public void initInMemoryDatabase() {
        database = new InMemoryDatabase();
    }
}
