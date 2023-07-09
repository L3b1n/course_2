# Задача 30. Flood It!
Имя входного файла: floodit.in  
Имя выходного файла: floodit.out  
Ограничение по времени: 1 с  
Ограничение по памяти: нет  

«Flood it!» — это простая, но крайне увлекательная игра. Правила её не очень затейливы: имеется прямоугольное поле, которое разбито на $n$ строк и $m$ столбцов. Перед началом игры каждая из $n \times m$ клеток покрашена в один из $k$ цветов. Прежде чем продолжать изложение условий игры, дадим несколько определений. Две клетки назовём соседними, если они имеют общую сторону. Несколько клеток назовём областью, если выполняются следующие условия:
 * между любыми двумя клетками области существует маршрут, проходящий по соседним клеткам этой области;
 * все клетки области окрашены в один цвет — цвет области;
 * ни одна клетка, не принадлежащая области, но соседствующая с ней, не окрашена в цвет области.

Цель игры состоит в захвате максимально возможного числа клеток игрового поля. Изначально во владение игрока отдаётся область, содержащая верхнюю левую клетку. На каждом ходу игрок выбирает какой-либо цвет из $k$ заданных и красит свои владения в этот цвет. Если перед ходом с одной их захваченных ранее клеток соседствует область, имеющая выбранный цвет, то игрок захватывает эту область.

На основании исходного состояния игрового поля и информации о первых t ходах игрока нарисуйте игровое поле, получившееся после этих ходов.

# Формат входных данных:
В первой строке заданы четыре целых положительных числа: $n$ и $m\ (1 \le n, m \le 1000)$ — размеры поля, $k\ (1 \le k \le 1 000 000)$ — число различных цветов и $t\ (1 \le t \le 100 000)$ — длина последовательности ходов.
Далее следует $n$ строк, в каждой из которых записано по $m$ чисел в диапазоне от $1$ до $k$, где числа обозначают цвета начальной раскраски.

В последней строке даны $t$ чисел в диапазоне от $1$ до $t$ — последовательность ходов.

# Формат выходных данных:
Необходимо вывести $n$ строк, в каждой из которых записано $m$ чисел в диапазоне от $1$ до $k$ — состояние игрового поля по прошествии $t$ заданных ходов.

# Пример
<table>
    <thead>
        <tr>
            <th align="center">floodit.in</th>
            <th align="center">floodit.out</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>3 4 5 3<br>
                1 2 3 5<br>
                2 3 1 1<br>
                2 1 3 2<br>
                2 3 2<br>
            </td>
            <td valign="top">2 2 2 5<br>
                             2 2 1 1<br>
                             2 1 3 2<br>
            </td>
        </tr>
        <tr>
            <td>2 5 6 2<br>
                1 3 3 3 6<br>
                2 4 6 3 4<br>
                3 1<br>
            </td>
            <td valign="top">1 1 1 1 6<br>
                             2 4 6 1 4<br>
            </td>
        </tr>
    </tbody>
</table>