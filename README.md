# CRUD-приложение Monitor Sensor

# Стек: Java 17, Spring Boot, Spring Security (JWT), Hibernate. В системе все данные хранятся в БД (PostgreSQL).
Сущность датчиков включает в себя:
- Название датчика
- Модель
- Тип (Давления, температуры, влажности)
- Радиус работы
- Единица измерения (Паскали, градусы, проценты)
- Местоположение (комната, кухня)

# Пример json-модели запроса:
# {
    "name": "Barometer",
    "model": "ac-23",
    "range": {
        "from": 22,
        "to": 45
    },
    "type": "bars",
    "unit": "dataType",
    "location": "kitchen",
    "description": "description"
}
#
- Name* – текстовое поле. Обязательное. Валидация – не пустое поле и количество символов не меньше 3 и не превышает 30.
- Model* – текстовое поле. Обязательное. Валидация – не пустое поле и количество символов не превышает 15.
- Range from, to* – числовое поле. Диапазон значений датчика, в котором предусмотрена его работа. Валидация – при вводе двух значений, from должно быть меньше to. Положительные целые числа.
- Type* – список выбора. Предустановленные данные, берутся из БД. Значения - Pressure, Voltage, Temperature, Humidity.
- Unit – список выбора. Предустановленные данные, берутся из БД. Значения - bar, voltage, °С, %.
- Location – местоположение сенсора. Текстовое поле. Валидация не больше 40 символов.
- Description – описание датчика. Валидация не больше 200 символов.
- В системе 2 предустановленных пользователя:
-  	admin (роль Administrator)
-  	user (роль Viewer)
Для аутентификации и авторизации используются Spring Security. 
- Пользователю с ролью Administrator разрешен доступ ко всем сущностям и разрешены все действия в приложении (добавление, удаление, редактирование, просмотр)
- Пользователю с ролью Viewer разрешен только просмотр  списка датчиков. 
- Пользователю с ролью Viewer или Administrator разрешено просматривать данные таблицы, использовать поиск по данным – Поля “name” и “model”. Поиск происходит по частичному совпадению введенного текста. 

Опциональная часть задания
  - Добавить swagger
  - Добавить миграции БД
  - Добавить возможность поднятия приложения в docker
  * Добавить новый сервис, который обращается к эндпоинтам Monitor Sensors для получения информации. Сервис должен ежедневно в 02:00 получать датчики и формировать статистику по ним (общее количество, количество датчиков по каждому типу). Сохранять в отдельную БД. Должна быть возможность получить статистику во временном промежутке (например: от 1.12.2023 по 10.12.2023) (в процессе)
