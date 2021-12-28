## Сборка проект

Для сборки используется maven: https://maven.apache.org/

```shell
mvn clean install
```

Результирующий jar подключить в проект как библиотеку.

## Примеры использования

```java
DbController dbController = DbController.memDatabase();
dbController.executeQuery("create table student (name string, age int, avgScore double)");
dbController.executeQuery("insert into student values ('Bob', 15, 3.5)");
dbController.executeQuery("insert into student values ('Jane', 17, 4.15)");
Result result = dbController.execute("select * from student");
List<Student> studentRes = new ArrayList<>();
while(result.hasNext()){
    result.next();
    studentRes.add(new Student(result));
}
```