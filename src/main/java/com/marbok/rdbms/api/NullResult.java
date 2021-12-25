package com.marbok.rdbms.api;

public class NullResult implements Result {
    @Override
    public Integer getInt(String column) {
        return null;
    }

    @Override
    public String getString(String column) {
        return null;
    }

    @Override
    public Double getDouble(String column) {
        return null;
    }

    @Override
    public void next() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
