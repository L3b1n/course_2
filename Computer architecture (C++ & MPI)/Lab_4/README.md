# Лабораторная работа 2
Баллы за разные пункты суммируются
## 3 балла:
Дан пример в виде файла lab1.go, который измеряет время доступа к кэшу и оперативной памяти, в случайном порядке итерируясь по элементам массивов разных размеров. Пример при работе выводит результат в виде двух столбцов, например:  
$5000\ \ \ 1.95686561\\
6000\ \ \ 1.94325336\\
7200\ \ \ 1.94303685\\
8640\ \ \ 1.94442978\\
10368\ \ \ 1.97553434\\
12441\ \ \ 1.97510052\\
14929\ \ \ 1.99337019\\
17915\ \ \ 2.28430303\\
21499\ \ \ 3.02394246\\
25798\ \ \ 3.23472403$

Где левый столбец это размер массива в байтах, правый — время доступа в наносекундах.   
Нужно запустить программу из примера (например при помощи команды go run lab1.go) и сохранить результат.  
Затем желательно построить график (например при помощи программы plot.py из примера, она считывает результат из файла data.txt и строит график при помощи matplotlib) и сравнить результаты с размером кэша своего процессора.  
Пример можно запустить скриптом run.sh  
Если не хочется запускать примеры на Go и Python программы можно написать на любых других.

## 7 баллов:
Проделать все те же действия, но написать программу для итераций самостоятельно на любом языке. Генерацию случайных чисел для каждой итерации использовать не стоит, это может быть медленно.