# Лабораторная работа No5                                                                                                       Тема: "Обмен данными по анонимному каналу с сервером".

# 5.2. Написать программы для консольных процессов Server и Part, которые обмениваются сообщениями по анонимному каналу. Создать ненаследуемые дескрипторы канала и создать наследуемые дубликаты дескрипторов канала.
Одновременно сообщение может передаваться только одним из процессов.                                                            
Процесс - Server, который выполняет следующие действия:                                                                         
    - Размер массива вводится с консоли. Тип массива: short                                                                       
    - Генерирует элементы массива                                                                                                 
    - Запускает процесс Part.                                                                                                     
    - Передаёт массив процессу Part.                                                                                             
    - Получает массив от процесса - Part.                                                                                         
    - Выводит переданную и полученную информацию по каналу на консоль.                                                            

Процесс - Part, который выполняет следующие действия:                                                                           
    - Получает размер массива чисел по анонимному каналу от процесса Server                                                       
    - Получает массив чисел по анонимному каналу от процесса Server                                                               
    - Запрашивает число числа $N$ и $M$ $(N < M)$.                                                                                
    - Определяет какие из чисел попали в отрезок $[N, M]$, передаёт их по анонимному каналу процессу Server.                     
    - Элементы массива передаются поэлементно.                                                                                    
    - Выводит переданную и полученную информацию по каналу на консоль.                                                            
    - Заканчивает работу.                                                                                                         