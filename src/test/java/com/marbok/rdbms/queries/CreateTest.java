package com.marbok.rdbms.queries;

import org.junit.Test;

public class CreateTest {

    @Test
    public void execute() {
        Create create = new Create("create table student (name string, age int)");
        System.out.println(create);
    }
}