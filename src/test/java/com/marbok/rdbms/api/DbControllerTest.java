package com.marbok.rdbms.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.marbok.rdbms.database.types.DoubleType;
import org.junit.Test;

import static org.junit.Assert.*;

public class DbControllerTest {

    @Test
    public void integrationTest() {
        DbController dbController = DbController.memDatabase();
        dbController.executeQuery("create table student (name string, age int, avgScore double)");
        dbController.executeQuery("insert into student values ('Bob', 15, 3.5)");
        dbController.executeQuery("insert into student values ('Jane', 17, 4.15)");
        Result result = dbController.execute("select * from student");
        List<Student> studentRes = new ArrayList<>();
        while (result.hasNext()) {
            result.next();
            studentRes.add(new Student(result));
        }
        final Student[] students = studentRes.toArray(Student[]::new);
        assertArrayEquals(students, new Student[] {
                new Student("Bob", 15, 3.5),
                new Student("Jane", 17, 4.15)
        });

        final Result jane = dbController.execute("select * from student where age > 15");
        jane.next();
        assertEquals(new Student(jane), new Student("Jane", 17, 4.15));
    }

    class Student {
        private final String name;
        private final Integer age;
        private final Double avgScore;

        public Student(Result result) {
            this(result.getString("name"), result.getInt("age"), result.getDouble("avgScore"));
        }

        public Student(String name, Integer age, Double avgScore) {
            this.name = name;
            this.age = age;
            this.avgScore = avgScore;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return Objects.equals(name, student.name) && Objects.equals(age, student.age) && Objects.equals(avgScore,
                    student.avgScore);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, avgScore);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", avgScore=" + avgScore +
                    '}';
        }
    }
}