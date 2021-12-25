package com.marbok.rdbms.queries;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertTest {

    @Test
    public void execute() {
        Insert insert = new Insert("insert into student values(Bob, 12, 1A)");
        System.out.println(insert);
    }
}