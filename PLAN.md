# План разработки MVP магазина приложений

## Общая структура

| # | Epic | Оценка |
|---|------|--------|
| 1 | Список приложений | 5 дней |
| 2 | Карточка приложения | 5 дней |
| 3 | Установка и удаление приложений | 4 дня |

---

## Epic 1 — Список приложений
**Оценка: 5 дней**  
Реализация экрана со списком приложений, получаемых с сервера, с поддержкой состояний загрузки и ошибки.

| Задача | Описание | Оценка |
|--------|----------|--------|
| 1.1 Настройка сети | Подключить Retrofit, настроить базовый URL и конвертер JSON. Добавить `AppApi` с методом `GET /catalog`. | 2 ч |
| 1.2 Data-слой: DTO и маппер | Создать `AppListItemDto` с полями `id`, `name`, `description`, `category`, `iconUrl`. Написать `AppListMapper` для преобразования DTO → Domain. | 2 ч |
| 1.3 Domain-слой | Создать `AppListItem`, интерфейс `AppListRepository`, `GetAppListUseCase`. | 1 ч |
| 1.4 Repository: получение данных | Реализовать `AppListRepositoryImpl` — вызов API и маппинг результата. | 2 ч |
| 1.5 DI-модуль | Добавить в `AppModule` провайдеры для Retrofit, `AppApi`, маппера, репозитория и UseCase. | 1 ч |
| 1.6 ViewModel и State | Создать `AppListViewModel` с состояниями Loading / Content / Error. В `init` запускать корутину загрузки. | 2 ч |
| 1.7 UI: элемент списка | Реализовать `AppListItem` — иконка, название, описание, категория. Загрузка изображений через Coil. | 2 ч |
| 1.8 UI: экран со списком | Реализовать `AppListScreen` со `Scaffold`, `LazyColumn`, тулбаром. Подписаться на стейт ViewModel. Обработать все три состояния. | 3 ч |
| 1.9 Навигация | Настроить `NavHost`, добавить маршруты `AppList` и `AppDetails`. Передавать `appId` при переходе на карточку. | 2 ч |

---

## Epic 2 — Карточка приложения
**Оценка: 5 дней**  
Реализация экрана с подробной информацией о приложении, кешированием в Room и функцией Wishlist.

| Задача | Описание | Оценка |
|--------|----------|--------|
| 2.1 Сетевой запрос деталей | Добавить в `AppApi` метод `GET /catalog/{id}`. Создать `AppDetailsDto` с полями `id`, `name`, `developer`, `category`, `ageRating`, `size`, `iconUrl`, `screenshots`, `description`. | 2 ч |
| 2.2 Domain-модель и UseCase | Создать `AppDetails`, `Category` (enum с `displayName`), интерфейс `AppDetailsRepository`, `GetAppDetailsUseCase`. | 2 ч |
| 2.3 Кеш в Room: Entity и DAO | Создать `AppDetailsEntity` с полем `isInWishlist`. Реализовать `AppDetailsDao` с методами `getAppDetails`, `insertAppDetails`, `updateWishlistStatus`. Настроить `AppDatabase` и `CategoryConverter`. | 3 ч |
| 2.4 Маппер Entity ↔ Domain | Реализовать `AppDetailsEntityMapper`: `toDomain` и `toEntity`, включая поле `isInWishlist`. | 1 ч |
| 2.5 Repository: кеш + сеть | Реализовать `AppDetailsRepositoryImpl`: сначала читать из Room, при отсутствии — запросить сеть и сохранить в БД на IO-потоке. Добавить `observeAppDetails` (Flow из DAO) и `toggleWishlist`. | 3 ч |
| 2.6 ViewModel | Реализовать `AppDetailsViewModel`: получать `appId` через `SavedStateHandle`, запускать одноразовую загрузку и подписку на `observeAppDetails`. Добавить `toggleWishlist()`. | 2 ч |
| 2.7 UI: шапка карточки | Реализовать `AppDetailsHeader` — иконка, название, разработчик, категория, рейтинг, размер. | 2 ч |
| 2.8 UI: тулбар с Wishlist | Реализовать `Toolbar` с кнопками «Назад», «Wishlist» (иконка меняется по `isInWishlist`), «Поделиться». | 2 ч |
| 2.9 UI: описание и скриншоты | Реализовать `AppDescription` (сворачиваемый текст) и `ScreenshotsList` (горизонтальный список). | 3 ч |
| 2.10 UI: экран карточки | Собрать `AppDetailsScreen` — `LazyColumn` со всеми компонентами. Обработать состояния Loading / Content / Error. | 2 ч |

---

## Epic 3 — Установка и удаление приложений
**Оценка: 4 дня**  
Управление жизненным циклом приложения: установка, отслеживание прогресса, удаление, отображение актуального статуса.

| Задача | Описание | Оценка |
|--------|----------|--------|
| 3.1 Domain: статус и методы | Добавить в `AppDetails` поле `installStatus: InstallStatus` (enum: `NOT_INSTALLED`, `INSTALLING`, `INSTALLED`). Добавить в `AppDetailsRepository` методы `installApp(id)` и `uninstallApp(id)`. | 2 ч |
| 3.2 Имитация установки | Реализовать в `AppDetailsRepositoryImpl` логику установки: обновлять `installStatus` в Room через `updateInstallStatus`. Имитировать прогресс через `delay` + Flow. | 3 ч |
| 3.3 DAO: обновление статуса | Добавить в `AppDetailsDao` метод `updateInstallStatus(id, status)` через `@Query UPDATE`. Room-Flow автоматически уведомит подписчиков об изменении. | 1 ч |
| 3.4 ViewModel: установка/удаление | Добавить в `AppDetailsViewModel` методы `installApp()` и `uninstallApp()`. Стейт обновляется автоматически через `observeAppDetails`. | 2 ч |
| 3.5 UI: кнопка установки | Реализовать `InstallButton` с тремя состояниями: «Установить» / прогресс-бар / «Удалить». Кнопка блокируется во время установки. | 3 ч |
| 3.6 UI: отображение статуса в списке | Добавить в `AppListItem` индикатор установленных приложений (иконка или бейдж). Обновить маппер списка для передачи статуса. | 2 ч |
| 3.7 Интеграционное тестирование | Проверить полный сценарий: нажатие «Установить» → прогресс → смена кнопки на «Удалить» → нажатие «Удалить» → возврат к «Установить». | 2 ч |

---

## Итого

| Epic | Оценка |
|------|--------|
| Epic 1 — Список приложений | 5 дней |
| Epic 2 — Карточка приложения | 5 дней |
| Epic 3 — Установка и удаление | 4 дня |
| **Итого** | **14 дней** |