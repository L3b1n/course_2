--Бинцаровский Леонид 2 группа 1 вариант

--First task
SELECT * FROM ПОСТАВЩИК;
GO

--Second task
SELECT * FROM ПОСТАВЩИК
WHERE НАИМЕНОВАНИЕ = 'ОАО «Горизонт»';
GO

--Third task
SELECT ЗАВ_НОМ, НАИМЕНОВАНИЕ, ЦЕНА
FROM ТОВАР
WHERE НОМ_СКЛАДА = (SELECT НОМ_СКЛАДА FROM СКЛАД WHERE РАСПОЛОЖЕНИЕ = 'г. Урюпинск')
GO

--Fourth task
SELECT COUNT(*) as Количество_Заказов FROM ТОВАР 
SELECT * FROM ТОВАР
GO

--Fifth task
SELECT ПОСТАВЩИК.НАИМЕНОВАНИЕ, ПОСТАВЩИК.АДРЕС, ПОСТАВЩИК.КОНТАКТ
FROM ПОСТАВЩИК
JOIN ТОВАР ON ПОСТАВЩИК.КОД_ПОСТАВЩИКА = ТОВАР.КОД_ПОСТАВЩИКА 
GROUP BY ПОСТАВЩИК.НАИМЕНОВАНИЕ, ПОСТАВЩИК.АДРЕС, ПОСТАВЩИК.КОНТАКТ
HAVING SUM(ТОВАР.ЦЕНА) > (SELECT AVG(ТОВАР.ЦЕНА) FROM ТОВАР)