package com.android.vkeducation.baskaeva.data.applist

import com.android.vkeducation.baskaeva.data.AppApi
import com.android.vkeducation.baskaeva.data.appdetails.AppDetailsDto
import com.android.vkeducation.baskaeva.domain.appdetails.Category
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppListRepositoryImplTest {

    private lateinit var fakeApi: FakeAppApi
    private lateinit var repository: AppListRepositoryImpl

    @Before
    fun setup() {
        fakeApi = FakeAppApi()
        repository = AppListRepositoryImpl(fakeApi, AppListMapper())
    }

    @Test
    fun `GIVEN getAppList with empty list WHEN API returns empty THEN result isEmpty`() = runTest { //как runBlocking, но без остановок потока (корутины) пон
        fakeApi.items = emptyList()
        val result = repository.getAppList()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `GIVEN getAppList with 3 Dto WHEN API returns 3 items THEN result with correct count`() = runTest {
        fakeApi.items = listOf(makeDto("1"), makeDto("2"), makeDto("3"))
        val result = repository.getAppList()
        assertEquals(3, result.size)
    }

    @Test
    fun `GIVEN getAppList with id WHEN map id THEN return correct id`() = runTest {
        fakeApi.items = listOf(makeDto(id = "mapped-id"))
        val result = repository.getAppList()
        assertEquals("mapped-id", result.first().id)
    }

    @Test
    fun `GIVEN getAppList with name WHEN map name THEN return correct name`() = runTest {
        fakeApi.items = listOf(makeDto(name = "Pixtrix"))
        val result = repository.getAppList()
        assertEquals("Pixtrix", result.first().name)
    }

    @Test
    fun `GIVEN getAppList with description WHEN map description THEN return correct description`() = runTest {
        fakeApi.items = listOf(makeDto(description = "Фоторедактор с фильтрами"))
        val result = repository.getAppList()
        assertEquals("Фоторедактор с фильтрами", result.first().description)
    }

    private fun makeDto(
        id: String = "id",
        name: String = "App",
        description: String = "desc"
    ) = AppListItemDto(id, name, description, "Производительность", "icon")

    class FakeAppApi : AppApi {
        var items: List<AppListItemDto> = emptyList()

        override suspend fun getAppList(): List<AppListItemDto> = items

        override suspend fun getAppDetails(id: String): AppDetailsDto =
            AppDetailsDto(id, "name", "dev", Category.PRODUCTIVITY, 3, 8.0, "icon", null, "desc")
    }
}