# Sensor-cloud-ESP8622

**Микросервис написан тестовом режиме, не для релиза. **

Ознакомительный Микросервис на Spring Для сборки информации с ESP8622 с сенсора BME280 или другого
Использует keycloak для безопасности и postgres для сохранения данных

Микросервисы писались с соблюдением SOLID. Работа с базой данных реализовывалась с паттерна "Одиночка".

Работа с реализована с лишь одним датчиком BME280, не рализована работа с множеством датчиков. Релизовывать можно через Фабричный метод, но задачи не было такой.

Скетч залит на отправку сообщений через каждую минуту. Для проверки аварий. Но при увеличении кол-ва датчиков возникнет проблема по записи данных в базу данных. 


	Решается асинхронным методом сохранения данных через R2DBC, брокера соощений Kafka, балансировкой нагрузки K8S. Работа с RestApi необходима через WebFlux. 

	Большинство Exception не перехвачена и не обработана, частично выведена информация о операциях для WebUI через Thymeleaf.

	Безопасность реализована только на микросервисе WebController через Keycloak. Нет защиты на RESTApi так как важна скорость обработки данных, и работа этого микросервиса будет отдельной локальной сети без выхода в сеть и привязки других устройств. OpenSource Проекты имеют свои недостатки, так как уязвимость может быть всеобщедоступнеой. Не реализовывал через Spring Security OAuth2 Authorization Server v1.2.0

	Нет проверки на ограничения ввода в Thymeleaf данных, хотя это не так долго делать.

	Необходимо скрыть Логин после авторизации, указать FirstName. Для безопасности отображать логин не серьезно.

	Нет микросервиса на Spring AOP для перехвата данных и обработки аварий с REST Api.
