USE Leonid_bintsarouski_2

---Создание таблицы Catalogs для хранения информации о каталогах
CREATE TABLE [dbo].[Catalogs] (
    [CatalogID]   INT IDENTITY(0, 1) PRIMARY KEY, ---Идентификатор каталога (автоинкрементный)
    [CatalogName] NVARCHAR(50)       UNIQUE ---Название каталога
);

GO

---Создание таблицы Books для хранения информации о книгах

CREATE TABLE [dbo].[Books] (
    [BookID]        INT IDENTITY(0, 1) PRIMARY KEY, ---Идентификатор книги (автоинкрементный)
    [Title]         NVARCHAR(40) NULL, ---Название книги
    [Author]        NVARCHAR(30) NULL, ---Автор книги
    [YearPublished] SMALLINT     NULL, ---Год издания книги
    [Publisher]     NVARCHAR(50) NULL, ---Издательство книги
    [Genre]         NVARCHAR(20) NULL, ---Жанр книги
    [CatalogID]     INT          NULL,
    FOREIGN KEY (CatalogID) 
    REFERENCES Catalogs(CatalogID) 
    ON DELETE SET NULL
);

GO

---Создание таблицы Archive для хранения информации о несданных книгах

CREATE TABLE [dbo].[Archive] (
    [Archive_BookID] INT, ---Идентификатор книги в архиве
    [Title]         NVARCHAR(50) NULL, ---Название книги
    [Author]        NVARCHAR(50) NULL, ---Автор книги
    [YearPublished] INT          NULL, ---Год издания книги
    [Borrower]      NVARCHAR(50) NULL, ---Заемщик книги
    [ExceededDays]  INT          NULL, ---Превышение срока возврата книги
);

GO

---Создание таблицы BorrowedBooks для отслеживания информации о займе книг

CREATE TABLE [dbo].[BorrowedBooks] (
    [BorrowID]      INT PRIMARY KEY IDENTITY(0, 1), ---Идентификатор займа
    [BookID]        INT             NULL, ---Идентификатор книги, связанный с таблицей Books
    [Borrower]      NVARCHAR(50)    NULL, ---Имя заемщика
    [BorrowDate]    DATE            NULL, ---Дата займа
    [DueDate]       DATE            NULL, ---Дата возврата
    [ReturnDate]    DATE            NULL, ---Дата фактического возврата
    CONSTRAINT BookID FOREIGN KEY (BookID) ---Внешний ключ для связи с таблицей Books
    REFERENCES Books(BookID) ---Ссылается на поле BookID в таблице Books
    ON UPDATE CASCADE ---При обновлении идентификатора книги в таблице Books, обновляется и в BorrowedBooks
);

GO
