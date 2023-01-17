<details><summary><h2><strong>Russian</strong></h2></summary>

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

<details open><summary><h2><strong>English</strong></h2></summary>

## General task for industrial programming
### You need to implement a console application that:
 1. Reads data from the input file;
 2. Processes the received information;
 3. Writes data to the output file;
 
The input and output file can be in the following formats: $plain text$, $xml$, $json$. Also, input and output files can be archived and encrypted by different engines (only archived, only encrypted, first archived, and then encrypted, and vice versa).
 
The "type" of the input and output file is set by the console options.
The application can be implemented in two ways: without using Design Patterns and using Design Patterns (Decorator and Builder $\dots$ can be designed as a Singleton Builder), compare implementations.

Information processing at the first stage: find all arithmetic operations in the input file and replace them with the results in the output file.

Implement filtering in two ways without using regular expressions and using regular expressions (as well as the third way :) find a library that does everything for you, parsing and calculation, there are more than one). Conduct a comparative analysis of 2 implementation options.

### Next steps for our task:
 1. Add UI:
    * console;
    * use any Java graphics library of your choice;
    * compare CLI and UI based implementations;
    * talk with classmates and compare different Java graphics libraries;
 2. Implement logic as a Web Service:
    * Rest using any Java engine;
    * SOAP using any Java engine;
    * Compare Rest and SOAP implementations;
    * talk with classmates and compare different Rest/Soap Java engines;
 3. Connect everything together UI and Web Service;

</details>