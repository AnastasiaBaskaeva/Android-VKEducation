package com.android.vkeducation.baskaeva.domain.applist

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAppListUseCaseTest {

    private lateinit var useCase: GetAppListUseCase
    private lateinit var fakeRepository: FakeAppListRepository

    @Before
    fun setup() {
        fakeRepository = FakeAppListRepository()
        useCase = GetAppListUseCase(fakeRepository)
    }


    // GIVEN fakeRepo

    @Test
    fun `with 2 items WHEN invoke THEN returns list of size 2 from repository`() = runTest {
        fakeRepository.items = listOf(makeAppListItem("1"), makeAppListItem("2"))
        val result = useCase()
        assertEquals(2, result.size)
    }

    @Test
    fun `with empty WHEN invoke THEN returns empty list`() = runTest {
        fakeRepository.items = emptyList()
        val result = useCase()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `WHEN invoke THEN returns correct item id`() = runTest {
        fakeRepository.items = listOf(makeAppListItem(id = "abc"))
        val result = useCase()
        assertEquals("abc", result.first().id)
    }

    @Test
    fun `WHEN invoke THEN returns correct item name`() = runTest {
        fakeRepository.items = listOf(makeAppListItem(name = "QuickNote"))
        val result = useCase()
        assertEquals("QuickNote", result.first().name)
    }

    @Test
    fun `with 3 items WHEN invoke THEN returns 3 items in order`() = runTest {
        val items = listOf(
            makeAppListItem("1", "First"),
            makeAppListItem("2", "Second"),
            makeAppListItem("3", "Third")
        )
        fakeRepository.items = items
        val result = useCase()
        assertEquals(listOf("1", "2", "3"), result.map { it.id })
    }

    @Test
    fun `WHEN invoke THEN calls repository 1 time`() = runTest {
        useCase()
        assertEquals(1, fakeRepository.callCount)
    }

    private fun makeAppListItem(id: String = "id", name: String = "App") =
        AppListItem(id, name, "subtitle", "category", "icon")

    class FakeAppListRepository : AppListRepository {
        var items: List<AppListItem> = emptyList()
        var callCount = 0
        override suspend fun getAppList(): List<AppListItem> {
            callCount++
            return items
        }
    }
}