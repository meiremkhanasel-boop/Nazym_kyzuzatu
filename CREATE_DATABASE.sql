-- ===================================================================
-- SQL скрипт для создания базы данных и таблиц
-- MSSQL Server 2019+
-- ===================================================================

-- 1. СОЗДАНИЕ БАЗЫ ДАННЫХ
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'NazymDatabase')
BEGIN
    CREATE DATABASE NazymDatabase;
END
GO

-- 2. ИСПОЛЬЗОВАНИЕ БД
USE NazymDatabase;
GO

-- 3. СОЗДАНИЕ ТАБЛИЦЫ ГОСТЕЙ
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Guests')
BEGIN
    CREATE TABLE Guests (
        GuestID INT PRIMARY KEY IDENTITY(1,1),
        Name NVARCHAR(255) NOT NULL,
        PhoneNumber NVARCHAR(20) NULL,
        GuestCount INT DEFAULT 1,
        Spouse NVARCHAR(10) DEFAULT 'Жоқ',  -- Иә/Жоқ
        Attendance NVARCHAR(20) DEFAULT 'pending',  -- yes/no/pending
        SubmittedAt DATETIME DEFAULT GETDATE(),
        CreatedAt DATETIME DEFAULT GETDATE(),
        UpdatedAt DATETIME DEFAULT GETDATE(),
        Notes NVARCHAR(500) NULL
    );

    -- Индексы для быстрого поиска
    CREATE INDEX IDX_Name ON Guests(Name);
    CREATE INDEX IDX_Attendance ON Guests(Attendance);
    CREATE INDEX IDX_SubmittedAt ON Guests(SubmittedAt DESC);

    PRINT 'Таблица Guests успешно создана';
END
GO

-- 4. СОЗДАНИЕ ТАБЛИЦЫ ДЛЯ ЛОГОВ (опционально)
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'GuestLogs')
BEGIN
    CREATE TABLE GuestLogs (
        LogID INT PRIMARY KEY IDENTITY(1,1),
        GuestID INT NOT NULL,
        Action NVARCHAR(50),
        OldValue NVARCHAR(500),
        NewValue NVARCHAR(500),
        CreatedAt DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (GuestID) REFERENCES Guests(GuestID)
    );

    PRINT 'Таблица GuestLogs успешно создана';
END
GO

-- 5. ПРОВЕРКА СОЗДАННЫХ ТАБЛИЦ
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo';
GO

-- 6. ПОКАЗАТЬ СТРУКТУРУ ТАБЛИЦЫ
EXEC sp_columns 'Guests';
GO

-- ===================================================================
-- ПРИМЕРЫ ТЕСТОВЫХ ДАННЫХ (опционально)
-- ===================================================================

-- Раскомментируйте для добавления тестовых данных:

-- INSERT INTO Guests (Name, PhoneNumber, GuestCount, Spouse, Attendance, Notes)
-- VALUES
--     ('Айша Сафина', '+7-701-123-4567', 2, 'Иә', 'yes', 'Подтверждает присутствие'),
--     ('Берик Жаксыбаев', '+7-702-234-5678', 1, 'Жоқ', 'yes', NULL),
--     ('Гаухар Нурманова', '+7-703-345-6789', 3, 'Иә', 'pending', 'Уточнит позже'),
--     ('Данияр Токтаров', '+7-704-456-7890', 1, 'Жоқ', 'no', 'Не может присутствовать'),
--     ('Елена Петрова', '+7-705-567-8901', 2, 'Иә', 'yes', NULL);
-- GO

-- SELECT * FROM Guests;
-- GO

-- ===================================================================

