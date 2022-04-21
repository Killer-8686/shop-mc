# shop-mc

Собственный pet проект. 
Микросервисы (Eureka Netflix).

REST
Feign - branch useFeign. Вызывается из oder-module, проверяет,есть ли в таблице user  с определенным id. Вызывается модуль user-module, контроллер.


Модули:

1) Eureka Server
2) Config Server (с использованием github репозитория для хранения распределенных настроек для микросервисов https://github.com/Killer-8686/shop-properties).
3) API Gateway. Ручное добавление маршрутов для существующих МС.
4) Модуль User - симуляция работы с данными покупателей.
5) Модуль Order - симуляция работы модуля осуществления покупок.
6) Entity module - все entity собраны тут.
