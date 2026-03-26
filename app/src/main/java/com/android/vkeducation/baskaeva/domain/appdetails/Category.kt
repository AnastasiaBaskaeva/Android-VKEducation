package com.android.vkeducation.baskaeva.domain.appdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Category(val categoryText: String) {
    @SerialName("Приложения")
    APP("Приложения"),


    @SerialName("Видео")
    VIDEO("Видео"),

    @SerialName("Спорт")
    SPORTS("Спорт"),


    @SerialName("Путешествия")
    TRAVEL("Путешествия"),



    @SerialName("Производительность")
    PRODUCTIVITY("Производительность"),

    @SerialName("Здоровье и фитнес")
    HEALTH("Здоровье и фитнес"),

    @SerialName("Фото и видео")
    PHOTOGRAPHY("Фото и видео"),

    @SerialName("Еда и напитки")
    FOOD("Еда и напитки"),

    @SerialName("Образование")
    EDUCATION("Образование"),

    @SerialName("Образ жизни")
    LIFESTYLE("Образ жизни"),

    @SerialName("Шопинг")
    SHOPPING("Шопинг"),

    @SerialName("Новости")
    NEWS("Новости"),

    @SerialName("Музыка")
    MUSIC("Музыка"),

    @SerialName("Игры")
    GAME("Игры"),

    @SerialName("Финансы")
    FINANCE("Финансы"),

    @SerialName("Утилиты")
    UTILITIES("Утилиты"),

    @SerialName("Общение")
    SOCIAL("Общение"),

    @SerialName("Бизнес")
    BUSINESS("Бизнес"),

    @SerialName("Навигация")
    MAPS("Навигация"),

    @SerialName("Погода")
    WEATHER("Погода"),

    @SerialName("Развлечения")
    ENTERTAINMENT("Развлечения"),

    @SerialName("Книги и справочники")
    BOOKS("Книги и справочники"),
}