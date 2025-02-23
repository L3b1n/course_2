USE Leonid_bintsarouski_2

---Удаление книг из книжного каталога, которые не были возвращены более полугода
---Сохранение информации о удаленных книгах в таблице Archive

---2. Создать каталог «Раритетные книги» и поместить в этот каталог книги, изданные более 50 лет назад.
DECLARE @CutoffDate DATE;
SET @CutoffDate = DATEADD(MONTH, -6, GETDATE()); ---Рассчитываем дату полугодового срока

---Вставка данных об удаленных книгах в таблицу Archive
INSERT INTO Archive (Archive_BookID ,Title, Author, YearPublished, Borrower, ExceededDays)
SELECT B.BookID, B.Title, B.Author, B.YearPublished, BB.Borrower, DATEDIFF(DAY, BB.DueDate, @CutoffDate) AS ExceededDays
FROM Books B
INNER JOIN BorrowedBooks BB ON B.BookID = BB.BookID
WHERE BB.ReturnDate IS NULL ---Книги, которые не были возвращены
    AND BB.DueDate <= @CutoffDate; ---Книги, у которых срок возврата превышает полугодовой срок

---Удаление книг из книжного каталога, которые не были возвращены более полугода
DECLARE @BookID INT;

SELECT @BookID = BookID
FROM BorrowedBooks
WHERE ReturnDate IS NULL ---Книги, которые не были возвращены
    AND DueDate <= @CutoffDate; ---Книги, у которых срок возврата превышает полугодовой срок

DELETE FROM BorrowedBooks
WHERE BookID = @BookID;

DELETE FROM Books
WHERE BookID = @BookID;

---3. Помещение в каталог "Раритетные книги" книг, изданных более 50 лет назад:
UPDATE Books
SET CatalogID = (SELECT CatalogID FROM Catalogs WHERE CatalogName = 'Раритетные книги')
WHERE YearPublished < YEAR(GETDATE()) - 50;