# Задача 0.5. Хеш-таблица
Имя входного файла: input.txt                                                                                                   
Имя выходного файла: output.txt                                                                                                 
Ограничение по времени: 1 с                                                                                                     
Ограничение по памяти: 256 МБ                                                                                                   

Хеш-таблица состоит из $m$ ячеек (ячейки нумеруются целыми числами от $0$ до $m − 1$). Для разрешения коллизий используется метод открытой адресации. Функция $h(x, i) = ((x mod m) + c \cdot i) mod m$  задает линейную последовательность проб свободных ячеек, где $x$ — ключ, $i$ — номер попытки (попытки нумеруются с нуля), $c$ — константа.
В таблицу было последовательно добавлено n ключей. В случае, если ключ в таблице уже есть, повторного добавления не происходит.

Определите, какой ключ хранится в каждой ячейке таблицы по окончании выполнения всех операций.

# Формат входных данных:
В первой строке через пробел записаны три целых числа: размер $m$ таблицы $(2 \le m \le 10 000)$, константа $c$ $(1 \le c \le m − 1)$ и число $n$ ключей для добавления $(0 \le n \le 10 000)$. Число $c$ взаимно просто с числом $m$. Далее в последующих $n$ строках записаны ключи, которые добавляются в хеш-таблицу, — целые числа из промежутка от $0$ до $10^9$. Ключи могут повторяться, но гарантируется, что в таблице достаточно места для размещения всех уникальных ключей из $n$ заданных.

# Формат выходных данных:
Выведите ровно $m$ чисел, $i$-е число описывает $i$-ю ячейку $(0 \le i \le m − 1)$. Если ячейка пуста, выведите $−1$, иначе выведите ключ, который в ней содержится.

# Пример
<table>
    <thead>
        <tr>
            <th align="center">input.txt</th>
            <th align="center">output.txt</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=1>10 1 9<br>
                          11<br>
                          21<br>
                          35<br>
                          4<br>
                          32<br>
                          5<br>
                          6<br>
                          70<br>
                          100<br>
            </td>
            <td valign="top">70 11 21 32 4 35 5 6 100 -1</td>
        </tr>
        <tr>
            <td rowspan=1>4 3 2<br>
                          2<br>
                          6<br>
            </td>
            <td valign="top">-1 6 2 -1</td>
        </tr>
    </tbody>
</table>