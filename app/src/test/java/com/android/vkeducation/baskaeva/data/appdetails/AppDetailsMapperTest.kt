package com.android.vkeducation.baskaeva.data.appdetails

import com.android.vkeducation.baskaeva.domain.appdetails.Category
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AppDetailsMapperTest {
    private lateinit var mapper: AppDetailsMapper

    @Before
    fun setup() {
        mapper = AppDetailsMapper()
    }

    @Test
    fun `WHEN toDomain() THEN maps id correctly`() {
        val dto = makeDto(id = "fa2e31b8-1234-4cf7-9914-108a170a1b01")
        val result = mapper.toDomain(dto)
        assertEquals("fa2e31b8-1234-4cf7-9914-108a170a1b01", result.id)
    }

    @Test
    fun `WHEN toDomain() THEN maps name correctly`() {
        val dto = makeDto(name = "QuickNote")
        val result = mapper.toDomain(dto)
        assertEquals("QuickNote", result.name)
    }

    @Test
    fun `WHEN toDomain() THEN maps developer correctly`() {
        val dto = makeDto(developer = "QuickSoft Inc.")
        val result = mapper.toDomain(dto)
        assertEquals("QuickSoft Inc.", result.developer)
    }

    @Test
    fun `WHEN toDomain() THEN maps category correctly`() {
        val dto = makeDto(category = Category.PRODUCTIVITY)
        val result = mapper.toDomain(dto)
        assertEquals(Category.PRODUCTIVITY, result.category)
    }

    @Test
    fun `WHEN toDomain() THEN maps iconUrl correctly`() {
        val dto = makeDto(icon = "https://example.com/icon.png")
        val result = mapper.toDomain(dto)
        assertEquals("https://example.com/icon.png", result.iconUrl)
    }

    @Test
    fun `WHEN toDomain() THEN maps null screenshots correctly`() {
        val dto = makeDto(screenshots = null)
        val result = mapper.toDomain(dto)
        assertNull(result.screenshotUrlList)
    }

    @Test
    fun `WHEN toDomain() THEN maps non-null screenshots correctly`() {
        val screenshots = listOf("https://example.com/s1.png", "https://example.com/s2.png")
        val dto = makeDto(screenshots = screenshots)
        val result = mapper.toDomain(dto)
        assertEquals(screenshots, result.screenshotUrlList)
    }

    @Test
    fun `WHEN toDomain() THEN maps description correctly`() {
        val dto = makeDto(description = "Лёгкое приложение для заметок")
        val result = mapper.toDomain(dto)
        assertEquals("Лёгкое приложение для заметок", result.description)
    }

    @Test
    fun `WHEN toDomain() THEN maps ageRating correctly`() {
        val dto = makeDto(ageRating = 18)
        val result = mapper.toDomain(dto)
        assertEquals(18, result.ageRating)
    }

    private fun makeDto(
        id: String = "test-id",
        name: String = "Test App",
        developer: String = "Test Dev",
        category: Category = Category.PRODUCTIVITY,
        ageRating: Int = 3,
        size: Double = 8.0,
        icon: String = "https://example.com/icon.png",
        screenshots: List<String>? = null,
        description: String = "Test description"
    ) = AppDetailsDto(id, name, developer, category, ageRating, size, icon, screenshots, description)

}