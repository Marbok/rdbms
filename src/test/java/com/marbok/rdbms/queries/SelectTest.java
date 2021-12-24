package com.marbok.rdbms.queries;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelectTest {

    @Test
    public void parseQuery() {
        Select select = new Select("select * from student where age > 5");
        System.out.println(select);

        select = new Select("select * from teacher");
        System.out.println(select);
    }
}