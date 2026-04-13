# Daily Planner

Минималистичный ежедневник с таймлайном дня и календарем

> **[Смотреть видео работы приложения](https://disk.360.yandex.ru/i/J61OhIGKlCCxPA)**

<p align="center">
  <img src="docs/screen_main.jpg" width="250" alt="Главный экран"/>
  &nbsp;&nbsp;
  <img src="docs/screen_create.jpg" width="250" alt="Создание задачи"/>
  &nbsp;&nbsp;
  <img src="docs/screen_detail.jpg" width="250" alt="Детали задачи"/>
</p>

## 🛠 Стек технологий

- **UI:** Jetpack Compose (Material 3).
- **Архитектура:** Clean Architecture + MVI.
- **Навигация:** Type-Safe Navigation Compose (Kotlin Serialization).
- **База данных:** Room + Coroutines/Flow.
- **DI:** Dagger Hilt.
- **Анализ кода:** Detekt.
- **Тесты:** JUnit4, MockK, Turbine.

## 📦 Структура проекта

Проект разбит на модули:
- `:app` - точка сборки (Umbrella) и DI графа.
- `:core:*` - переиспользуемые компоненты (UI, утилиты).
- `:feature:planner:api` - контракты (Domain/UI модели, интерфейсы).
- `:feature:planner:impl` - имплементация фичи (Экраны, ViewModels, UseCases, Room DAO).
