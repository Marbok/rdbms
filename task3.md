## Классы анализа
1. Row - строка с данными в таблице
2. TableMetaData - информация о данных в табице
3. Table - набор строк + мета данные
4. DBMetaData - информация о табицах в бд
5. Database - набор таблиц + мета данные

6. Operation и производные (Select, Update) - операция в базе данных
7. Condition и производные (More, Less) - часть Operation, показывает как выбирать данные
8. Join - часть Operation операция для объединения

9. SqlInterpreter - умеет преобразовывать sql запрос в операцию
10. DbTemplate - фасад для клиента, принимает запрос - возращает рузультат