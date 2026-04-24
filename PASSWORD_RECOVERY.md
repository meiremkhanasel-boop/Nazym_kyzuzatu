🔐 ИНСТРУКЦИЯ ПО ВОССТАНОВЛЕНИЮ ДОСТУПА К SQL SERVER

═════════════════════════════════════════════════════════════════════════════

⚠️ СИТУАЦИЯ: Вы забыли пароль от SA (администратор БД)

═════════════════════════════════════════════════════════════════════════════

✅ РЕШЕНИЕ 1: ИСПОЛЬЗОВАТЬ СУЩЕСТВУЮЩИЙ ЛОГИН (БЫСТРО!)
─────────────────────────────────────────────────────────────────────────────

Если у вас уже есть логин "test" (как на скриншоте):

1. Я уже обновил DatabaseConnection.java:
   ├─ USERNAME = "test"
   └─ PASSWORD = "" (оставить пустым)

2. Пересоберите проект:
   ├─ В IntelliJ: Ctrl+F9 (Rebuild Project)
   └─ Или: mvn clean install

3. Запустите приложение:
   └─ Shift+F10

4. Готово! Будет подключение через логин "test"


═════════════════════════════════════════════════════════════════════════════

✅ РЕШЕНИЕ 2: СБРОСИТЬ ПАРОЛЬ SA (если нужен администратор)
─────────────────────────────────────────────────────────────────────────────

СПОСОБ A: Через PowerShell (если вы администратор ПК)
──────────────────────────────────────────────────────

1. Откройте PowerShell как администратор:
   ├─ Нажмите Windows+X
   ├─ Выберите "Windows PowerShell (администратор)"
   └─ Нажмите "Да"

2. Остановите SQL Server:
   
   net stop "SQL Server (SQLEXPRESS)" /yes

   Дождитесь сообщения: "The SQL Server (SQLEXPRESS) service has been stopped successfully"

3. Запустите sqlservr.exe в режиме однопользователя:

   cd "C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\Binn"
   
   sqlservr.exe -m

   Дождитесь: "Server is listening on..."

4. Откройте НОВУЮ вкладку PowerShell и подключитесь с правами администратора:

   sqlcmd -S localhost\SQLEXPRESS -U sa

   (без пароля, так как режим однопользователя)

5. Выполните команду для смены пароля:

   ALTER LOGIN sa WITH PASSWORD = 'НовыйПароль123';
   GO

6. Выйдите из sqlcmd:

   exit

7. Остановите однопользовательский режим:
   ├─ Нажмите Ctrl+C в первом окне PowerShell
   └─ Дождитесь остановки

8. Перезагрузите SQL Server нормально:

   net start "SQL Server (SQLEXPRESS)"

9. Проверьте доступ в SSMS:
   ├─ Откройте SSMS
   ├─ Server: localhost\SQLEXPRESS
   ├─ Login: sa
   └─ Password: НовыйПароль123


СПОСОБ B: Через SQL Server Configuration Manager
─────────────────────────────────────────────────

1. Откройте SQL Server Configuration Manager
2. SQL Server Services
3. Правая кнопка на "SQL Server (SQLEXPRESS)"
4. Properties
5. Advanced
6. Startup Parameters
7. Добавьте: -m (однопользовательский режим)
8. OK, перезагрузите сервис
9. Подключитесь через SSMS без пароля
10. ALTER LOGIN sa WITH PASSWORD = 'НовыйПароль123';
11. Удалите -m из параметров запуска
12. Перезагрузите сервис


═════════════════════════════════════════════════════════════════════════════

✅ РЕШЕНИЕ 3: СОЗДАТЬ НОВОГО ПОЛЬЗОВАТЕЛЯ
──────────────────────────────────────────────────────────────────────────────

Если не можете восстановить SA, создайте нового:

В SSMS (если у вас есть доступ через "test"):

1. Object Explorer → Security → Logins
2. Правая кнопка → New Login
3. Login name: admin
4. Password: ВашПароль123
5. Default database: NazymDatabase
6. Server Roles → Отметьте "sysadmin"
7. OK

Затем обновите DatabaseConnection.java:

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "ВашПароль123";


═════════════════════════════════════════════════════════════════════════════

📋 ЧТО Я УЖЕ СДЕЛАЛ:
─────────────────────────────────────────────────────────────────────────────

✅ Обновил DatabaseConnection.java:
   ├─ USERNAME = "test" (существующий логин)
   └─ PASSWORD = "" (пусто)

✅ Проект готов к пересборке

═════════════════════════════════════════════════════════════════════════════

🎯 СЛЕДУЮЩИЙ ШАГ:
─────────────────────────────────────────────────────────────────────────────

1. Если хотите использовать "test" → просто запустите проект
2. Если хотите восстановить SA → следуйте инструкции выше
3. Если хотите создать нового пользователя → используйте РЕШЕНИЕ 3

═════════════════════════════════════════════════════════════════════════════

🔗 ПОЛЕЗНЫЕ ССЫЛКИ:

Microsoft MSSQL Password Reset:
https://learn.microsoft.com/ru-ru/sql/sql-server/failover-clusters/windows/recover-a-sql-server-database-engine

SQL Server Authentication:
https://learn.microsoft.com/ru-ru/sql/relational-databases/security/choose-an-authentication-mode

═════════════════════════════════════════════════════════════════════════════

