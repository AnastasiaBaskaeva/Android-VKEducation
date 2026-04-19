package com.android.vkeducation.baskaeva.data.applist

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppListMapperTest {

    private lateinit var mapper: AppListMapper

    @Before
    fun setup() {
        mapper = AppListMapper()
    }

    @Test
    fun `WHEN toDomain() THEN maps id correctly`() {
        val dto = makeDto(id = "abc-123")
        val result = mapper.toDomain(dto)
        assertEquals("abc-123", result.id)
    }

    @Test
    fun `WHEN toDomain() THEN maps name correctly`() {
        val dto = makeDto(name = "QuickNote")
        val result = mapper.toDomain(dto)
        assertEquals("QuickNote", result.name)
    }

    @Test
    fun `WHEN toDomain() THEN maps description correctly`() {
        val dto = makeDto(description = "Лёгкое приложение для заметок")
        val result = mapper.toDomain(dto)
        assertEquals("Лёгкое приложение для заметок", result.description)
    }

    @Test
    fun `WHEN toDomain() THEN maps category correctly`() {
        val dto = makeDto(category = "Производительность")
        val result = mapper.toDomain(dto)
        assertEquals("Производительность", result.category)
    }

    @Test
    fun `WHEN toDomain() THEN maps icon correctly`() {
        val dto = makeDto(icon = "https://example.com/icon.png")
        val result = mapper.toDomain(dto)
        assertEquals("https://example.com/icon.png", result.icon)
    }

    @Test
    fun `WHEN toDomain() THEN maps all fields correctly`() {
        val dto = AppListItemDto(
            id = "1",
            name = "TinyChef",
            description = "Быстрые рецепты",
            category = "Еда и напитки",
            icon = "https://example.com/chef.png"
        )
        val result = mapper.toDomain(dto)
        assertEquals("1", result.id)
        assertEquals("TinyChef", result.name)
        assertEquals("Быстрые рецепты", result.description)
        assertEquals("Еда и напитки", result.category)
        assertEquals("https://example.com/chef.png", result.icon)
    }


    private fun makeDto(
        id: String = "default-id",
        name: String = "Default Name",
        description: String = "Default description",
        category: String = "Производительность",
        icon: String = "https://example.com/default.png"
    ) = AppListItemDto(id, name, description, category, icon)
}