# Проект с автотестами для сайта [reqres.in](https://reqres.in/)

## :gear: Стек технологий:
| IDEA | Java | Rest-Assured | Allure Report | Gradle | Junit5 | GitHub | Jenkins |
|:--------:|:-------------:|:-------:|:----:|:------:|:----:|:----:|:------:|
| <img src="images/Intelij_IDEA.svg" width="40" height="40"> | <img src="images/JAVA.svg" width="40" height="40"> | <img src="images/Rest-Assured.svg" width="40" height="40"> | <img src="images/Allure_Report.svg" width="40" height="40"> | <img src="images/Gradle.svg" width="40" height="40"> | <img src="images/Junit5.svg" width="40" height="40"> | <img src="images/GitHub.svg" width="40" height="40"> | <img src="images/Jenkins.svg" width="40" height="40"> |
___

## :heavy_check_mark: Тестируемая функциональность
- Получение данных всех пользователей
- Получение данных пользователя по id
- Получение данных пользователя по id (Пользователь не найден)
- Создание нового пользователя
- Изменение данных пользователя
- Удаление пользователя
- Успешная регистрация пользователя
- Неуспешная регистрация пользователя
- Успешная авторизация пользователя
- Неуспешная авторизация пользователя

## :pushpin: В качестве CI системы использован Jenkins
### [Проект](https://jenkins.autotests.cloud/job/08-levikss-rest_api_allure/)

![alt "Jenkins"](./images/Jenkins.png "Jenkins")

## :arrow_forward: Запуск тестов

Для запуска тестов необходимо выполнить следующую команду:

```bash
gradle clean test
```

## :bar_chart: Генерация отчета происходит в Allure Report

Для генерации отчета необходимо выполнить следующую команду:

```bash
allure serve build/allure-results
```

![alt "Allure Report"](./images/Allure_report1.png "Allure Report")

К каждому тесту прикладываются:
- Данные, переданные в запросе
- Данные, полученные в ответе

![alt "Allure Report"](./images/Allure_report2.png "Allure Report")
