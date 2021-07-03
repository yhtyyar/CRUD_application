# CRUD_application

<a href="https://www.codefactor.io/repository/github/yhtyyar/crud_application"><img src="https://www.codefactor.io/repository/github/yhtyyar/crud_application/badge" alt="CodeFactor" /></a>

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Writer(id,  firstName,  lastName,  List<Post> posts,  Region region)

Post (id,  content,  created,  updated)

Region (id,  name)

 
# В качестве хранилища данных необходимо использовать текстовые файлы:
writers.json 
 
posts.json 
 
regions.json

 
# Пользователь в консоли должен иметь возможность:
-создания
 
-получения
 
-редактирования 
 
-удаления данных.

 
# Слои:
model - POJO классы
 
repository - классы, реализующие доступ к текстовым файлам
 
controller - обработка запросов от пользователя
 
view - все данные, необходимые для работы с консолью

 
 Результатом выполнения задания должен быть отдельный репозиторий 
который содержит описание задачи, проекта и инструкции запуска 
приложения через командную строку.
 
 # Инструкция по запуску

    1. Скачать приложение

    2. Распаковать архив

    3. Открыть проект в любом IDE у которого есть JDK 8 (IntelliJ IDEA)

    4. Запустить класс Main


