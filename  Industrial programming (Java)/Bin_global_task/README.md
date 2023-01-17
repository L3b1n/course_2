<details><summary>Russian</summary>

## Общая задача по промышленному программированию
### Необходимо реализовать консольное приложение, которое:
 1. Читает данные из входного файла;
 2. Обрабатывает полученную информацию;
 3. Записывает данные в выходной файл;
 
Входной и выходной файл могут быть следующих форматов: $plain text$, $xml$, $json$. Так же входные и выходные файлы могут быть архивированы и зашифрованы, разными engines (только архивирован, только зашифрован, сперва архивирован, а потом зашифрован и наоборот).
 
«Тип» входного и выходного файла задаются параметрами консоли.  
Приложение реализовать двумя способами: без использования Design Patterns и c использованием Design Patterns (Decorator и Builder $\dots$ можно оформить Builder в виде Singleton-а), сравнить реализации.
 
Обработка информации на первом этапе: найти все арифметические операции во входном файле и заменить на результаты в выходном файле.

Реализовать фильтрацию двумя способами без использования регулярных выражений и с использованием регулярных выражений (а так же третьим :) найти библиотеку, которая все делает за вас, парсинг и калькуляцию, такие есть и не одна). Провести сравнительный анализ 2-х вариантов реализации.

### Следующие шаги по нашей задаче:
 1. Добавить UI:
    * консольный;
    * использую любую графическую библиотеку Java на Ваш выбор;
    * сравнить CLI и UI based реализации;
    * поговорить с одногруппниками и сравнить различные графические Java библиотеки;
 2. Реализовать логику как Web Service:
    * Rest, используя любой Java engine;
    * SOAP, используя любой Java engine;
    * Сравнить Rest и SOAP реализации;
    * поговорить с одногруппниками и сравнить различные Rest/Soap Java engines;
 3. Соединить все вместе UI и Web Service;

</details>

<details open><summary><big><big><strong>English</strong></big></big></summary>

## General task for industrial programming
### You need to implement a console application that:
 1. Reads data from the input file;
 2. Processes the received information;
 3. Writes data to the output file;
 
The input and output file can be in the following formats: $plain text$, $xml$, $json$. Also, input and output files can be archived and encrypted by different engines (only archived, only encrypted, first archived, and then encrypted, and vice versa).
 
The "type" of the input and output file is set by the console options.
The application can be implemented in two ways: without using Design Patterns and using Design Patterns (Decorator and Builder $\dots$ can be designed as a Singleton Builder), compare implementations.

</details>