## Сборка проект

Для сборки используется maven: https://maven.apache.org/

```shell
mvn clean install
```

Результирующий jar подключить в проект как библиотеку.

## Примеры использования

```java
class Main {
    public static void main(String[] args) {
        DbController dbController = DbController.memDatabase();
        dbController.executeQuery("create table student (name string, age int, avgScore double)");
        dbController.executeQuery("insert into student values ('Bob', 15, 3.5)");
        dbController.executeQuery("insert into student values ('Jane', 17, 4.15)");
        Result result = dbController.execute("select * from student");
        while (result.hasNext()) {
            result.next();
            System.out.println(new Student(result));
        }
    }
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
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", avgScore=" + avgScore +
                '}';
    }
}
```