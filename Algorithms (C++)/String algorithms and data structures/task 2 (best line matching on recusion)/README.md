# Задача 2. Наилучшее совмещение строк
Имя входного файла: стандартный ввод  
Имя выходного файла: стандартный вывод  
Ограничение по времени: 3 с  
Ограничение по памяти: 256 МБ
        
Вам даны две строки $\alpha$ и $\beta$. $|\alpha| = |\beta| = 2^{k} = m,\ k > 1$. Причем это не простые строки, а циклические, то есть их можно начинать читать с любой позиции. Необходимо определить коэффициент похожести, который вычисляется как максимальное количество совпадающих символом при наилучшем совмещении таких строк. Чем больше символов совпадает, тем лучше совмещение. Требуется найти лучшее совмещение.

# Формат входных данных
В первой строке записано одно число $m\ (4 \le m \le 131072)$. В следующих двух строках записаны строки $\alpha$ и $\beta$, состоящие ровно из $m$ символов из множества ${'A', 'B', 'C', 'D'}$.

# Формат выходных данных
Выведите два числа — максимальное количество совпадающих символов и значение оптимального сдвига — неотрицательное количество символов строки $\beta$, которые необходимо перенести из конца строки в её начало для достижения наилучшего совмещения. Если таких сдвигов несколько, то вывести минимальный.

# Пример
<table>
    <thead>
        <tr>
            <th align="center">стандартный ввод</th>
            <th align="center">стандартный вывод</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>4<br>
                ABAB<br>
                BABA
            </td>
            <td valign="top">4 1</td>
        </tr>
        <tr>
            <td>16
                BCBCCDDACBDBBCBA<br>
                CBCADBBDCBCBBBBB<br>
            </td>
            <td valign="top">8 3</td>
        </tr>
    </tbody>
</table>