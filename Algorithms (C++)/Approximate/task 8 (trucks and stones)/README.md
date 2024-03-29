# Задача 8. Грузовики и камни
Имя входного файла: input.txt  
Имя выходного файла: output.txt  
Ограничение по времени: 1 с  
Ограничение по памяти: нет

Имеется $n$ камней и $m$ машин в очереди. Камни характеризуются массой, машины — грузоподъемностью. Необходимо определить порядок загрузки, при котором минимизируется число используемых машин.

## Замечание

Известно, что любой камень помещается в любую машину, а также что если искомое размещение существует, то общее число машин не менее чем вдвое превосходит минимально возможное число машин.

# Формат входных данных

В первой строке находится число m грузовиков $(1 \le m \le 300 000)$. В следующих $m$ строках записаны грузоподъёмности грузовиков — целые числа от $1$ до $10^{13}$. В следующей строке находится число $n$ камней $(1 \le n,\ n + m \le 500 000)$. Далее в $n$ строках записаны массы камней — целые числа от $1$ до $10^{13}$.

# Формат выходных данных

Если решение существует, то выведите $2m + 1$ строк. В первой — число $m$, а далее для каждой машины в первой строке должна находиться грузоподъёмность грузовика, а во второй — последовательно через пробел массы камней, положенных в грузовик (пустая строка, если в грузовик ничего не положено). Число используемых машин не должно превышать минимально возможное более чем в два раза.
Если решений нет, то выведите $no\ solution$.