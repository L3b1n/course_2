USE Leonid_bintsarouski_2

-- Добавить записи о новых книгах из списка приобретения:

INSERT INTO Books (Title, Author, YearPublished, Publisher, Genre)
VALUES  (N'Война и мир', N'Лев Толстой', 1869, N'Русский вестник', N'Роман'),
        (N'1984', N'Джордж Оруэлл', 1949, N'Secker & Warburg', N'Фантастика'),
        (N'Улисс', N'Джеймс Джойс', 1922, N'Sylvia Beach', N'Современная проза');

GO

-- Вставка данных в таблицу "BorrowedBooks"
INSERT INTO BorrowedBooks (BookID, Borrower, BorrowDate, DueDate, ReturnDate)
VALUES
    (0, 'John Smith', '2023-05-01', '2023-05-15', '2023-05-14'),
    (1, 'Jane Doe', '2021-05-03', '2021-05-17', NULL),
    (2, 'Michael Johnson', '2023-05-05', '2023-05-19', NULL);

GO

INSERT INTO Catalogs (CatalogName)
VALUES (N'Раритетные книги');

GO

-- Добавление примера данных в таблицу Catalogs
INSERT INTO Catalogs (CatalogName)
VALUES 
(N'Фантастика'), 
(N'Мистика'), 
(N'Научная фантастика');
GO
