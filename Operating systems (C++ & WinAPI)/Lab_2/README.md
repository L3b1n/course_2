                                            Тема: «Создание процессов».
Задание:
Написать программы двух консольных процессов Parent и Child, которые выполняют следующие действия.
Два проекта (процессы) хранить в одном Solution (Решении)!
В Solution (Решениии) настроить, что бы .exe файлы лежали в одном Debug!
Процесс Parent:
- Согласно индивидуальным вариантам выполняет :
    * Ввести размер массива, ввести элементы массива;
    * Для вариантов 1,4, 6, 8, 9 – ввести необходимые дополнительные значения согласно варианту (A,B,X,K);
    * Формирует командную строку, которая содержит информацию об размерности массива, элементах и т.д. (согласно                индивидуальному варианту);
    * Для консоли дочернего процесса устанавливает визуальные настройки, согласно индивидуальным вариантам:
        2. Установить ширину буфера для Сhild.
    * Запускает дочерний процесс Child, которому через командную строку передается информация об размерности массива,   элементах и т.д. (согласно варианту);

Процесс Child:
- Согласно индивидуальным вариантам Child выполняет:
2. Найти в массиве повторяющиеся элементы (разместить их группы в массиве слева, остальные (одиночные) - соответственно справа). Полученный массив вывести. Тип элементов - вещественные числа.