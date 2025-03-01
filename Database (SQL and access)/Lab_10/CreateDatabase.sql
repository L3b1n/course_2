-- CREATE DATABASE Leonid_binzarovski;

GO

CREATE TABLE Издательство (
  Код_издательства INT PRIMARY KEY identity,
  Название NVARCHAR(100) NOT NULL,
  Место_издания NVARCHAR(100) NOT NULL
);

GO

CREATE TABLE Читатель (
  Код_читателя INT PRIMARY KEY identity,
  ФИО NVARCHAR(100) NOT NULL
);

GO

CREATE TABLE Автор (
  Код_автора INT PRIMARY KEY identity,
  ФИО NVARCHAR(100) NOT NULL
);

GO

CREATE TABLE Тип_издания (
  Код_типа_издания INT PRIMARY KEY identity,
  Тип_издания NVARCHAR(100) NOT NULL
);

CREATE TABLE Издание (
  Код_издания INT PRIMARY KEY identity,
  Код_издательства INT NOT NULL,
  Код_типа_издания INT NOT NULL,
  Код_автора INT NOT NULL,
  Код_читателя INT,
  Заглавие NVARCHAR(100) NOT NULL,
  Год_издания INT NOT NULL,
  Количество_страниц INT NOT NULL,
  Ключевые_слова NVARCHAR(100),
  Авторский_знак NVARCHAR(100),
  Индекс_классификатора NVARCHAR(100),
  Сведения_об_отвественности NVARCHAR(100),
  Дата_поступления DATE NOT NULL,
  Дата_списания DATE,
  На_руках NVARCHAR(3) NOT NULL,
  CONSTRAINT Код_издательства FOREIGN KEY (Код_издательства)
  REFERENCES Издательство(Код_издательства) 
  on update cascade,
  CONSTRAINT Код_типа_издания FOREIGN KEY (Код_типа_издания)
  REFERENCES Тип_издания(Код_типа_издания)
  on update cascade,
  CONSTRAINT Код_автора FOREIGN KEY (Код_автора)
  REFERENCES Автор(Код_автора)
  on update cascade,
  CONSTRAINT Код_читателя FOREIGN KEY (Код_читателя)
  REFERENCES Читатель(Код_читателя)
  on update cascade
);

GO

-- DROP TABLE Издание, Издательство, Читатель, Автор, Тип_издания
