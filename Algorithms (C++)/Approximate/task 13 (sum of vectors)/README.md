# Задача 13. Сумма векторов
Имя входного файла: input.txt  
Имя выходного файла: output.txt  
Ограничение по времени: 1 с  
Ограничение по памяти: нет
        
Необходимо указать такой порядок суммирования векторов в $\mathbb{R}^m$, при котором все частичные суммы попадают в шар минимального радиуса в евклидовой метрике. Известно, что сумма всех векторов равна $0$.

# Формат входных данных
В первой строке находится число $n$ векторов ($1 \le n \le 3000$). Во второй строке — размерность $m$ пространства ($1 \le m \le 10$). Далее идут $n$ строк, содержащих по m целых чисел, разделённых пробелом,— координаты каждого вектора (каждое число не превосходит $10^9$ по абсолютной величине).

# Формат выходных данных
Выведите $n$ строк, содержащих номера векторов в порядке суммирования. Радиус шара, определяемый предложенным порядком суммирования не должен превосходить минимально возможный более чем в $2m$ раз.