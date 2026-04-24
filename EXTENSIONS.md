# 🎨 РАСШИРЕННЫЕ ВОЗМОЖНОСТИ И ПРИМЕРЫ КАСТОМИЗАЦИИ

## 1. Смена Цветовой Схемы

### Вариант A: Королевский Пурпур и Серебро

```css
:root {
    --accent-gold: #C0C0C0;      /* Серебро */
    --accent-red: #4B0082;        /* Пурпур */
    --light-bg: #F8F8FF;          /* Ghost White */
    --white: #FFFFFF;
}
```

### Вариант B: Персиковый и Розовый

```css
:root {
    --accent-gold: #FFB6C1;       /* Бледно-розовый */
    --accent-red: #DB7093;        /* Темно-розовый */
    --light-bg: #FFF5EE;          /* Seashell */
    --white: #FFFFFF;
}
```

### Вариант C: Классический Черный и Серебро

```css
:root {
    --accent-gold: #E8E8E8;       /* Светло-серый */
    --accent-red: #1a1a1a;        /* Черный */
    --light-bg: #FAFAFA;          /* Белый с легким оттенком */
    --white: #FFFFFF;
}
```

---

## 2. Добавление Музыки на Странице

### Вставьте перед закрывающим тегом `</body>`:

```html
<!-- Audio Player -->
<div class="audio-player">
    <audio id="bgMusic" controls>
        <source src="path/to/your/music.mp3" type="audio/mpeg">
        Ваш браузер не поддерживает аудио.
    </audio>
</div>

<style>
    .audio-player {
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: rgba(139, 0, 0, 0.9);
        padding: 15px;
        border-radius: 10px;
        border: 2px solid #D4AF37;
        z-index: 1000;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.3);
    }
    
    .audio-player audio {
        width: 250px;
        outline: none;
    }
    
    @media (max-width: 480px) {
        .audio-player {
            bottom: 10px;
            right: 10px;
        }
        .audio-player audio {
            width: 100%;
            max-width: 200px;
        }
    }
</style>
```

---

## 3. Добавление Фотогалереи

### Вставьте в раздел Hero после фото невесты:

```html
<section class="gallery-section">
    <h3 class="h3-title">Назым фото галереясы</h3>
    <div class="gallery-grid">
        <div class="gallery-item">
            <img src="photo1.jpg" alt="Фото 1">
        </div>
        <div class="gallery-item">
            <img src="photo2.jpg" alt="Фото 2">
        </div>
        <div class="gallery-item">
            <img src="photo3.jpg" alt="Фото 3">
        </div>
        <div class="gallery-item">
            <img src="photo4.jpg" alt="Фото 4">
        </div>
    </div>
</section>
```

### CSS для галереи:

```css
.gallery-section {
    padding: 80px 20px;
    text-align: center;
}

.gallery-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 20px;
    max-width: 1000px;
    margin: 50px auto;
}

.gallery-item {
    position: relative;
    overflow: hidden;
    border-radius: 10px;
    border: 3px solid #D4AF37;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
    transition: all 0.5s cubic-bezier(0.34, 1.56, 0.64, 1);
}

.gallery-item img {
    width: 100%;
    height: 300px;
    object-fit: cover;
    display: block;
}

.gallery-item:hover {
    transform: scale(1.08) rotate(2deg);
    box-shadow: 0 20px 50px rgba(212, 175, 55, 0.4);
    border-color: #8B0000;
}
```

---

## 4. Добавление Гостевой Книги Отзывов

### После формы RSVP добавьте:

```html
<section class="section">
    <h3 class="h3-title text-center" data-aos="fade-down">Құттықтау Сөздері</h3>
    
    <form id="guestbookForm" class="guestbook-form">
        <div class="form-group">
            <label for="reviewName" class="form-label">Аты-жөніңіз</label>
            <input type="text" id="reviewName" name="reviewName" class="form-input" required>
        </div>
        
        <div class="form-group">
            <label for="reviewText" class="form-label">Құттықтау сөзінің</label>
            <textarea id="reviewText" name="reviewText" class="form-textarea" rows="5" placeholder="Назымға сұрттарыңызды енгізіңіз..." required></textarea>
        </div>
        
        <button type="submit" class="btn-rsvp">Құттықтау жіберу</button>
    </form>
    
    <div id="reviewsList" class="reviews-list" style="margin-top: 50px;">
        <!-- Отзывы будут добавлены сюда -->
    </div>
</section>
```

### CSS для отзывов:

```css
.form-textarea {
    width: 100%;
    padding: 15px;
    font-family: 'Montserrat', sans-serif;
    font-size: 1rem;
    color: var(--text-dark);
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(250, 248, 245, 0.95) 100%);
    border: 2px solid rgba(212, 175, 55, 0.3);
    border-radius: 10px;
    resize: vertical;
    min-height: 120px;
    transition: all 0.3s ease;
}

.form-textarea:focus {
    outline: none;
    border-color: var(--accent-gold);
    box-shadow: 0 0 20px rgba(212, 175, 55, 0.25);
}

.reviews-list {
    display: grid;
    gap: 25px;
    max-width: 800px;
    margin: 0 auto;
}

.review-item {
    padding: 30px;
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.5) 0%, rgba(250, 248, 245, 0.7) 100%);
    border-left: 4px solid var(--accent-gold);
    border-radius: 5px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}

.review-author {
    font-weight: 700;
    color: var(--accent-red);
    margin-bottom: 10px;
}

.review-text {
    font-style: italic;
    color: var(--text-dark);
    line-height: 1.8;
}
```

### JavaScript для отзывов:

```javascript
const guestbookForm = document.getElementById('guestbookForm');
const reviewsList = document.getElementById('reviewsList');

function displayReviews() {
    const reviews = JSON.parse(localStorage.getItem('invitationReviews') || '[]');
    reviewsList.innerHTML = '';
    
    if (reviews.length === 0) {
        reviewsList.innerHTML = '<p style="text-align: center; color: #999;">Әлі құттықтау сөздері жоқ</p>';
        return;
    }
    
    reviews.reverse().forEach(review => {
        const reviewDiv = document.createElement('div');
        reviewDiv.className = 'review-item';
        reviewDiv.innerHTML = `
            <div class="review-author">${review.name}</div>
            <div class="review-text">"${review.text}"</div>
            <small style="color: #999; margin-top: 10px;">${review.timestamp}</small>
        `;
        reviewsList.appendChild(reviewDiv);
    });
}

if (guestbookForm) {
    guestbookForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const review = {
            name: document.getElementById('reviewName').value,
            text: document.getElementById('reviewText').value,
            timestamp: new Date().toLocaleString('kk-KZ')
        };
        
        const reviews = JSON.parse(localStorage.getItem('invitationReviews') || '[]');
        reviews.push(review);
        localStorage.setItem('invitationReviews', JSON.stringify(reviews));
        
        alert('Рақмет! Өтінішіңіз сохраняется');
        guestbookForm.reset();
        displayReviews();
    });
}

displayReviews();
```

---

## 5. Вывод на Печать

### Добавьте CSS для печати:

```css
@media print {
    body::before,
    body::after,
    .petal,
    .scroll-indicator,
    .upload-btn,
    .photo-controls,
    .rsvp-form,
    .guestbook-form,
    .audio-player {
        display: none !important;
    }
    
    .hero {
        min-height: auto;
        padding: 20px;
    }
    
    .section {
        page-break-inside: avoid;
    }
    
    body {
        background: white;
    }
}
```

### Для печати: Ctrl+P или Menu → Print

---

## 6. Мобильное Меню (Для больших приложений)

### Добавьте в начало Hero Section:

```html
<nav class="mobile-menu">
    <button id="menuBtn" class="menu-btn">☰</button>
    <div id="menuContent" class="menu-content" style="display: none;">
        <a href="#invitation">Шақыру</a>
        <a href="#timer">Таймер</a>
        <a href="#program">Бағдарлама</a>
        <a href="#rsvp">RSVP</a>
        <a href="#map">Карта</a>
    </div>
</nav>
```

### CSS и JavaScript:

```css
.mobile-menu {
    display: none;
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 1000;
}

.menu-btn {
    background: linear-gradient(135deg, var(--accent-red) 0%, rgba(139, 0, 0, 0.9) 100%);
    color: white;
    border: 2px solid var(--accent-gold);
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1.5rem;
}

.menu-content {
    position: absolute;
    top: 50px;
    right: 0;
    background: var(--accent-red);
    border: 2px solid var(--accent-gold);
    border-radius: 5px;
    overflow: hidden;
}

.menu-content a {
    display: block;
    padding: 12px 20px;
    color: white;
    text-decoration: none;
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    transition: background 0.3s;
}

.menu-content a:last-child {
    border-bottom: none;
}

.menu-content a:hover {
    background: rgba(0, 0, 0, 0.2);
}

@media (max-width: 768px) {
    .mobile-menu {
        display: block;
    }
}
```

```javascript
const menuBtn = document.getElementById('menuBtn');
const menuContent = document.getElementById('menuContent');

menuBtn.addEventListener('click', function() {
    menuContent.style.display = menuContent.style.display === 'none' ? 'block' : 'none';
});

// Закрыть меню при клике на ссылку
document.querySelectorAll('.menu-content a').forEach(link => {
    link.addEventListener('click', function() {
        menuContent.style.display = 'none';
    });
});
```

---

## 7. Темный Режим

### Добавьте в стиль:

```css
@media (prefers-color-scheme: dark) {
    body {
        background: linear-gradient(180deg, #1a1a1a 0%, #2a2a2a 50%, #1f1f1f 100%);
        color: #e0e0e0;
    }
    
    .section {
        background: linear-gradient(135deg, rgba(30, 30, 30, 0.8) 0%, rgba(40, 40, 40, 0.9) 100%);
    }
    
    .rsvp-form {
        background: linear-gradient(135deg, rgba(50, 50, 50, 0.8) 0%, rgba(60, 60, 60, 0.9) 100%);
    }
    
    .form-input,
    .form-select {
        background: linear-gradient(135deg, rgba(80, 80, 80, 0.9) 0%, rgba(70, 70, 70, 0.95) 100%);
        color: #e0e0e0;
        border-color: rgba(212, 175, 55, 0.5);
    }
}
```

---

## 8. Многоязычность

### Добавьте селектор языка в Hero:

```html
<div class="language-selector" style="position: absolute; top: 20px; left: 20px;">
    <button class="lang-btn active" data-lang="kk">Қазақша</button>
    <button class="lang-btn" data-lang="ru">Русский</button>
    <button class="lang-btn" data-lang="en">English</button>
</div>
```

### JavaScript:

```javascript
const translations = {
    kk: {
        title: "Қыз ұзату",
        subtitle: "Шақыру хат",
        // ... остальные переводы
    },
    ru: {
        title: "Уводы невесты",
        subtitle: "Пригласительное письмо",
    },
    en: {
        title: "Girl's Send-off",
        subtitle: "Invitation Letter",
    }
};

document.querySelectorAll('.lang-btn').forEach(btn => {
    btn.addEventListener('click', function() {
        const lang = this.getAttribute('data-lang');
        // Обновите текст на странице
        document.querySelectorAll('[data-translate]').forEach(el => {
            el.textContent = translations[lang][el.getAttribute('data-translate')];
        });
    });
});
```

---

## 9. Share Buttons (Поделиться)

### После футера добавьте:

```html
<div class="share-buttons" style="text-align: center; padding: 20px; margin-top: 30px;">
    <h4 style="color: var(--accent-red); margin-bottom: 20px;">Пригласи друзей:</h4>
    
    <a href="https://wa.me/?text=Check%20out%20this%20invitation:%20URL" class="share-btn whatsapp" target="_blank">
        <i class="fab fa-whatsapp"></i> WhatsApp
    </a>
    
    <a href="https://www.facebook.com/sharer/sharer.php?u=URL" class="share-btn facebook" target="_blank">
        <i class="fab fa-facebook"></i> Facebook
    </a>
    
    <a href="https://twitter.com/intent/tweet?text=Check%20out%20this%20invitation&url=URL" class="share-btn twitter" target="_blank">
        <i class="fab fa-twitter"></i> Twitter
    </a>
    
    <a href="mailto:?subject=Wedding%20Invitation&body=You're%20invited%20to%20my%20wedding!" class="share-btn email" target="_blank">
        <i class="fas fa-envelope"></i> Email
    </a>
</div>
```

### CSS:

```css
.share-buttons {
    display: flex;
    gap: 15px;
    justify-content: center;
    flex-wrap: wrap;
}

.share-btn {
    display: inline-flex;
    align-items: center;
    gap: 10px;
    padding: 12px 20px;
    border-radius: 5px;
    color: white;
    text-decoration: none;
    font-weight: 600;
    transition: all 0.3s ease;
    border: 2px solid;
}

.share-btn.whatsapp { background: #25D366; border-color: #20BA58; }
.share-btn.facebook { background: #1877F2; border-color: #165FD8; }
.share-btn.twitter { background: #1DA1F2; border-color: #1A91DA; }
.share-btn.email { background: #EA4335; border-color: #C5221F; }

.share-btn:hover {
    transform: translateY(-3px);
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
}
```

---

## 10. Конвертер Времени (для разных часовых поясов)

```javascript
function convertEventTime() {
    const eventDate = new Date('2026-06-19T18:00:00'); // Время в Казахстане (UTC+6)
    
    // Для других временных зон:
    const timezones = {
        'Москва': 'Europe/Moscow',
        'Анкара': 'Europe/Istanbul',
        'Дубай': 'Asia/Dubai'
    };
    
    Object.entries(timezones).forEach(([city, tz]) => {
        const formatter = new Intl.DateTimeFormat('kk-KZ', {
            timeZone: tz,
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
        
        console.log(`${city}: ${formatter.format(eventDate)}`);
    });
}

convertEventTime();
```

---

**Все эти примеры полностью совместимы с основным приложением!**
Просто скопируйте нужный код и добавьте в соответствующие секции.

