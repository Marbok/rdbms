package com.marbok.rdbms.database;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.marbok.rdbms.exception.CannotCreateDatabaseDirectory;
import com.marbok.rdbms.exception.PathNotDirectoryException;
import com.marbok.rdbms.exception.TableNotExistException;
import com.marbok.rdbms.exception.WriteNotAllowedException;

public abstract class Database {

    protected final File dbDir;
    protected Map<String, Table> tables = new HashMap<>();

    /**
     * preconditions: pathToDbDirectory is directory, application can create or use files in this directory, files in
     * directory have right format
     * postconditions: create directory on disk or initialize database
     */
    public Database(Path pathToDbDirectory) {
        dbDir = new File(pathToDbDirectory.toUri());
        writeAccessValidate(dbDir);
        if (dbDir.exists()) {
            fileIsDirectoryValidate(dbDir);
            initDatabase(dbDir);
        } else {
            if (!dbDir.mkdirs()) {
                throw new CannotCreateDatabaseDirectory("Can't create database directory: " + dbDir.getPath());
            }
        }
    }

    /**
     * postcondition: create table in database
     */
    public void createTable(Table table) {
        tables.put(table.name(), table);
        table.create();
    }

    /**
     * precondition: table is exist
     * postcondition: remove table from database
     */
    public void dropTable(String tableName) {
        if (!tables.containsKey(tableName)) {
            throw new TableNotExistException(tableName + " doesn't exist");
        }
        tables.get(tableName).drop();
    }

    public abstract void alterTable(Table table); // todo - нужно ли это тут??

    /**
     * postcondition: initialize database
     */
    protected abstract void initDatabase(File dbDir);

    private void fileIsDirectoryValidate(File file) {
        if (!file.isDirectory()) {
            throw new PathNotDirectoryException(file.getPath() + " is not directory");
        }
    }

    private void writeAccessValidate(File file) {
        if (!file.canWrite()) {
            throw new WriteNotAllowedException("Application can't write in " + file.getPath());
        }
    }
}
