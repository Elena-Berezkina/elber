Реализация простого CRUD-приложения (Spring Boot), бэк-енд составляющей справочника с заданиями. 
Команды запускаются через поисковую строку:
- создание задания выполняется через команду "/tasks/" (PostMapping);
- определенное задание выводится через "/tasks/{id}";
- все задания выводятся командой "/tasks/" (GetMapping);
- изменение задания: "/tasks/{id}/{isDone}/{title}/{description}";
- удаление: "/delete/{id}".

  Для автоматической сборки использовался Maven.

  
