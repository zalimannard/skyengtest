# Тестовое задание Skyeng на Java Junior

Колесников Д.А.

WAR с выполненным заданием - в этой папке. [skyengtest-1.0.war](skyengtest-1.0.war). У меня нет денег на VPS, поэтому пока что могу показать только скриншот развёрнутого приложения ниже

# API

Оффлайн-версия API лежит здесь в папке [в pdf](readme-files/open-api.pdf)

Онлайн версия находится по пути /api/docs запущенного приложения и представляет собой документацию OpenAPI

Если коротко, то запросы получились такие:

1. Регистрация нового почтового отправления

```
/api/mailitem/register
```

2. Прибытие почтового отправления в промежуточное отделение

```
/api/movement/arrive
```

3. Убытие почтового отправления из промежуточного отделения

```
/api/movement/depart
```

4. Получение отправления конечным получателем

```
/api/mailitem/deliver/{itemId}
```

5. Просмотр статуса и полной истории движения почтового отправления

```
/api/mailitem/history/{itemId}
```

# Схема данных

Выделено 3 сущности. Из них 2 по заданию - Почтовое отправление и Почтовое отделение. Дополнительно таблица Перемещения - для отслеживания

![Схема данных](readme-files/schema.png)

# Покрытие тестами

Реализованы позитивные и негативные автотесты. Покрытие - 85%. Непокрытыми остались конфиги, main и т.д. Весь функционал по заданию покрыт

![Отчёт JaCoCo](readme-files/jacoco.png)

# Разворачивание

Приложение собрано в war-архив и запускается с помощью Apache Tomcat 10

![Apache Tomcat](readme-files/tomcat.png)

# Технологии

- Стек: Spring
- Способ передачи данных: JSON
- СУБД: PostgreSQL
- Реализация ORM: Hibernate
- Система сборки: Gradle
