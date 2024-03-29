# Лабораторная работа №3. OpenMP
## Вариант 2
### Численное интегрирование (до 6 баллов)
Численно вычислите $∫[a; b] f(x)dx$ методом Симпсона для заданной функции $f(x)$ на $N + 1$ точке отрезка и выведите результат в консоль. Распараллелить задачу при помощи OpenMP. Число $N$ получить как аргумент командной строки.  
	$[a, b] = [0; 5].$  
    $f(x) = 1 − cos x; x ∈ [0;\ \pi / 2]$,    
	$f(x) = x − \pi / 2; x ∈ [\pi / 2;\ 5]$
 a. Используйте оптимальные директивы (1 балл).  
 b. Затраты по памяти: $m(N) = O(1)$ (1 балл).  
 c. Считая за нагрузку вычисление $f(x)$, минимизируйте нагрузку (1 балл).  
 d. Вычислите точное значение и сравните его с приближённым. Сделайте вывод (1 балл).  
 e. Создайте секции и параллельно запустите задачу для $N_2 = 4N$. Гарантируйте вложенные параллельные вычисления (2 балла).  