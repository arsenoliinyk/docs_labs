# docs-lab
## Лабораторна 2
### Завдання (Реалізація GoF-паттерну стратегія)
Необхідно   вичитати   дані   із   отриманого   згідно   варіанту   датасету   і
записати   їх   в   файл.   Потрібно   застосувати   шаблон   проектування
стратегія   для   виводу   отриманих   даних   в   різні   сховища.  Код,   який
виводить рядки в консоль, має бути відділений від коду, що робить
вичитку даних з файлу. Код виводу в консоль має бути організований з
використанням паттерну стратегія, і дозволяти переключити вивід з
консолі на запис у кафку/Redis з мінімальними змінами в коді (тобто
без змін в самому коді, а лише з-за допомогою зміни в конфігураційних
файлах).

### HOW TO RUN:
1. ``docker compose up -d``
2. Create ``.env`` file. Example:
```
WRITE_TYPE=KAFKA
KAFKA_TOPIC=quickstart
```
3. Run DocsLabApplication locally. Add ``.env`` to its configuration.


### INFO:
Create topic:
``docker exec kafka kafka-topics --bootstrap-server kafka:9092 --create --topic quickstart``

Produce messages:
``docker exec --interactive --tty kafka kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart``

Consume messages:
``docker exec --interactive --tty kafka kafka-console-consumer --bootstrap-server kafka:9092 --topic quickstart --from-beginning``

