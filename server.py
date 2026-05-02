#!/usr/bin/env python3
"""
Веб-сервер для приглашения на Қыз ұзату
Запускает HTTP сервер с поддержкой CORS на порту 8000
"""

import http.server
import socketserver
import os
from pathlib import Path

PORT = 8000

class CORSRequestHandler(http.server.SimpleHTTPRequestHandler):
    def end_headers(self):
        # Добавляем CORS заголовки
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'Content-Type, Range')
        self.send_header('Cache-Control', 'no-cache, no-store, must-revalidate')
        self.send_header('Pragma', 'no-cache')
        self.send_header('Expires', '0')

        # Для аудиофайлов добавляем правильный MIME-type
        if self.path.endswith('.mp3'):
            self.send_header('Content-Type', 'audio/mpeg')
        elif self.path.endswith('.jpeg') or self.path.endswith('.jpg'):
            self.send_header('Content-Type', 'image/jpeg')
        elif self.path.endswith('.html'):
            self.send_header('Content-Type', 'text/html; charset=utf-8')

        super().end_headers()

    def do_GET(self):
        # Логирование запросов
        print(f'📁 GET {self.path}')

        # Проверяем, есть ли файл
        file_path = self.translate_path(self.path)
        if not os.path.exists(file_path) and self.path != '/':
            print(f'   ❌ Файл не найден: {file_path}')

        super().do_GET()

    def do_OPTIONS(self):
        self.send_response(200)
        self.end_headers()

    def log_message(self, format, *args):
        # Красивое логирование
        if '404' in str(args):
            print(f'❌ {format % args}')
        elif '200' in str(args):
            pass  # Скрываем 200 OK чтобы не спамить
        else:
            print(f'{format % args}')


# Меняем рабочую директорию на папку скрипта
os.chdir(os.path.dirname(os.path.abspath(__file__)))

print('=' * 60)
print('🎉 ПРИГЛАШЕНИЕ НА КЫЗ УЗАТУ')
print('=' * 60)
print()
print(f'✅ Веб-сервер запущен')
print(f'📍 Адрес: http://localhost:{PORT}')
print(f'📁 Папка: {os.getcwd()}')
print()

# Проверяем наличие файлов
required_files = ['index.html', 'music.mp3', 'bg_image.jpeg', 'photo6.jpeg']
print('📋 Проверка файлов:')
for fname in required_files:
    if os.path.exists(fname):
        size = os.path.getsize(fname)
        size_kb = size / 1024
        print(f'   ✅ {fname:20} ({size_kb:,.1f} KB)')
    else:
        print(f'   ❌ {fname:20} (НЕ НАЙДЕН)')

print()
print('🌐 Откройте в браузере: http://localhost:8000')
print()
print('⚠️  Нажмите Ctrl+C чтобы остановить сервер')
print('=' * 60)
print()

# Запускаем сервер
with socketserver.TCPServer(("", PORT), CORSRequestHandler) as httpd:
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print('\n\n✅ Сервер остановлен')
        print('До встречи на той! 👋')

