-- ===================================================================
-- ПОЛНЫЙ SQL СКРИПТ ДЛЯ СОЗДАНИЯ БД ГОСТЕЙ
-- Откройте в SQL Server Management Studio и нажмите Execute (Ctrl+E)
-- ===================================================================

-- 1. СОЗДАНИЕ БАЗЫ ДАННЫХ
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'NazymDatabase')
BEGIN
    CREATE DATABASE NazymDatabase;
    PRINT '✓ База данных NazymDatabase создана успешно!';
END
ELSE
BEGIN
    PRINT '✓ База данных NazymDatabase уже существует';
END
GO

-- 2. ПЕРЕКЛЮЧЕНИЕ НА БД
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
        Spouse NVARCHAR(10) DEFAULT 'Жоқ',
        Attendance NVARCHAR(20) DEFAULT 'pending',
        SubmittedAt DATETIME DEFAULT GETDATE(),
        CreatedAt DATETIME DEFAULT GETDATE(),
        UpdatedAt DATETIME DEFAULT GETDATE(),
        Notes NVARCHAR(500) NULL
    );

    PRINT '✓ Таблица Guests создана успешно!';

    -- СОЗДАНИЕ ИНДЕКСОВ ДЛЯ БЫСТРОГО ПОИСКА
    CREATE INDEX IDX_Name ON Guests(Name);
    CREATE INDEX IDX_Attendance ON Guests(Attendance);
    CREATE INDEX IDX_SubmittedAt ON Guests(SubmittedAt DESC);

    PRINT '✓ Индексы созданы!';
END
ELSE
BEGIN
    PRINT '✓ Таблица Guests уже существует';
END
GO

-- 4. ДОБАВЛЕНИЕ ТЕСТОВЫХ ДАННЫХ (ОПЦИОНАЛЬНО - РАСКОММЕНТИРУЙТЕ ЕСЛИ ХОТИТЕ)
-- Раскомментируйте строки ниже если хотите добавить примеры данных

BEGIN
    -- Проверяем, пусто ли таблица
    IF (SELECT COUNT(*) FROM Guests) = 0
    BEGIN
        INSERT INTO Guests (Name, PhoneNumber, GuestCount, Spouse, Attendance, Notes)
        VALUES
            (N'Айша Сафина', N'+7-701-123-4567', 2, N'Иә', N'yes', N'Подтверждает присутствие'),
            (N'Берик Жаксыбаев', N'+7-702-234-5678', 1, N'Жоқ', N'yes', NULL),
            (N'Гаухар Нурманова', N'+7-703-345-6789', 3, N'Иә', N'pending', N'Уточнит позже'),
            (N'Данияр Токтаров', N'+7-704-456-7890', 1, N'Жоқ', N'no', N'Не может присутствовать'),
            (N'Елена Петрова', N'+7-705-567-8901', 2, N'Иә', N'yes', NULL);

        PRINT '✓ Тестовые данные добавлены (5 гостей)!';
    END
    ELSE
    BEGIN
        PRINT '✓ Таблица уже содержит данные';
    END
END
GO

-- 5. СОЗДАНИЕ ПОЛЬЗОВАТЕЛЯ 'test' ЕСЛИ ЕГО ЕЩЕ НЕТ (ОПЦИОНАЛЬНО)
-- Раскомментируйте если нужен пользователь 'test'

/*
IF NOT EXISTS (SELECT * FROM sys.sql_logins WHERE name = 'test')
BEGIN
    CREATE LOGIN test WITH PASSWORD = N'test123';
    PRINT '✓ Пользователь test создан!';
END

USE NazymDatabase;
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'test')
BEGIN
    CREATE USER test FOR LOGIN test;
    ALTER ROLE db_owner ADD MEMBER test;
    PRINT '✓ Пользователю test выданы права на БД!';
END
*/

-- 6. ПРОВЕРКА РЕЗУЛЬТАТА
PRINT '';
PRINT '═════════════════════════════════════════════════════════';
PRINT '✓ БАЗА ДАННЫХ ПОЛНОСТЬЮ ГОТОВА!';
PRINT '═════════════════════════════════════════════════════════';
PRINT '';

-- ПОКАЗАТЬ ТАБЛИЦЫ В БД
PRINT 'Созданные таблицы:';
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo';

-- ПОКАЗАТЬ КОЛИЧЕСТВО ЗАПИСЕЙ
PRINT '';
PRINT 'Количество гостей в таблице:';
SELECT COUNT(*) as [Всего гостей] FROM Guests;

-- ПОКАЗАТЬ ПЕРВЫЕ 5 ЗАПИСЕЙ
PRINT '';
PRINT 'Примеры данных:';
SELECT TOP 5 GuestID, Name, GuestCount, Spouse, Attendance FROM Guests;

-- 7. ИНФОРМАЦИЯ ДЛЯ ПОДКЛЮЧЕНИЯ
PRINT '';
PRINT '═════════════════════════════════════════════════════════';
PRINT 'ИНФОРМАЦИЯ ДЛЯ ПОДКЛЮЧЕНИЯ JAVA ПРИЛОЖЕНИЯ:';
PRINT '═════════════════════════════════════════════════════════';
PRINT 'Server: localhost';
PRINT 'Port: 1433';
PRINT 'Database: NazymDatabase';
PRINT 'Username: sa (или test)';
PRINT 'Password: [ваш пароль]';
PRINT '';
PRINT 'Connection URL для Java:';
PRINT 'jdbc:sqlserver://localhost:1433;databaseName=NazymDatabase;encrypt=true;trustServerCertificate=true;';
PRINT '═════════════════════════════════════════════════════════';

GO

