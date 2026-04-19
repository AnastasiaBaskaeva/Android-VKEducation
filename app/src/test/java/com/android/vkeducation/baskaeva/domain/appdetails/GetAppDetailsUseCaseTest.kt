package com.android.vkeducation.baskaeva.domain.appdetails

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAppDetailsUseCaseTest {
    private lateinit var useCase: GetAppDetailsUseCase
    private lateinit var fakeRepository: FakeAppDetailsRepository

    @Before
    fun setup() {
        fakeRepository = FakeAppDetailsRepository()
        useCase = GetAppDetailsUseCase(fakeRepository)
    }


    // GIVEN fakeRepo

    @Test
    fun `WHEN invoke THEN passes correct id to repository`() = runTest {
        fakeRepository.appDetails = makeAppDetails("target-id")
        useCase("target-id")
        assertEquals("target-id", fakeRepository.lastRequestedId)
    }

    @Test
    fun `WHEN invoke THEN returns correct name`() = runTest {
        fakeRepository.appDetails = makeAppDetails(name = "TinyChef")
        val result = useCase("any")
        assertEquals("TinyChef", result.name)
    }

    @Test
    fun `WHEN invoke THEN returns correct isInWishlist`() = runTest {
        fakeRepository.appDetails = makeAppDetails(isInWishlist = true)
        val result = useCase("any")
        assertTrue(result.isInWishlist)
    }

    @Test
    fun `WHEN toggleWishlist() THEN delegates to repository`() = runTest {
        useCase.toggleWishlist("some-id")
        assertEquals("some-id", fakeRepository.lastToggledId)
    }

    @Test
    fun `WHEN observe THEN returns flow from repository`() = runTest {
        val expected = makeAppDetails("obs-id")
        fakeRepository.appDetails = expected
        val flow = useCase.observe("obs-id")
        flow.collect { result ->
            assertEquals("obs-id", result.id)
        }
    }

    private fun makeAppDetails(
        id: String = "test-id",
        name: String = "Test",
        isInWishlist: Boolean = false
    ) = AppDetails(id, name, "Dev", Category.PRODUCTIVITY, 3, 8f, "icon", null, "desc", isInWishlist)

    class FakeAppDetailsRepository : AppDetailsRepository {
        var appDetails: AppDetails = AppDetails("id", "name", "dev", Category.PRODUCTIVITY, 0, 0f, "", null, "", false)
        var lastRequestedId: String? = null
        var lastToggledId: String? = null

        override suspend fun getAppDetails(id: String): AppDetails {
            lastRequestedId = id
            return appDetails
        }

        override fun observeAppDetails(id: String): Flow<AppDetails> = flowOf(appDetails)

        override suspend fun toggleWishlist(id: String) {
            lastToggledId = id
        }
    }

}