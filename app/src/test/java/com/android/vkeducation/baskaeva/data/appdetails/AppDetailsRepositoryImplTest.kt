package com.android.vkeducation.baskaeva.data.appdetails

import com.android.vkeducation.baskaeva.data.AppApi
import com.android.vkeducation.baskaeva.data.appdetails.local.AppDetailsDao
import com.android.vkeducation.baskaeva.data.appdetails.local.AppDetailsEntity
import com.android.vkeducation.baskaeva.data.appdetails.local.AppDetailsEntityMapper
import com.android.vkeducation.baskaeva.domain.appdetails.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppDetailsRepositoryImplTest {

    private lateinit var fakeDao: FakeAppDetailsDao
    private lateinit var fakeApi: FakeAppApi
    private lateinit var repository: AppDetailsRepositoryImpl

    @Before
    fun setup() {
        fakeDao = FakeAppDetailsDao()
        fakeApi = FakeAppApi()
        repository = AppDetailsRepositoryImpl(
            appApi = fakeApi,
            dao = fakeDao,
            mapper = AppDetailsMapper(),
            entityMapper = AppDetailsEntityMapper()
        )
    }

    // getAppDetails()


//Given буду опускать, т.к. входные данные повторяются
    @Test
    fun `WHEN entity in DB and not in API THEN getAppDetails does not call API`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "cached-id")
        repository.getAppDetails("cached-id")
        assertFalse(fakeApi.wasCalled)
    }

    @Test
    fun `WHEN entity in API and not in DB THEN getAppDetails does call API`() = runTest {
        fakeDao.storedEntity = null
        fakeApi.dto = makeDto(id = "new-id")
        repository.getAppDetails("new-id")
        assertTrue(fakeApi.wasCalled)
    }

    @Test
    fun `WHEN entity in DB and not in API THEN getAppDetails returns data from DB`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "cached-id", name = "Cached App")
        val result = repository.getAppDetails("cached-id")
        assertEquals("Cached App", result.name)
    }

    @Test
    fun `WHEN entity in API and not in DB THEN getAppDetails saves to DB after network call`() = runTest {
        fakeDao.storedEntity = null
        fakeApi.dto = makeDto(id = "new-id", name = "Fresh App")
        repository.getAppDetails("new-id")
        assertEquals("Fresh App", fakeDao.insertedEntity?.name)
    }

    @Test
    fun `WHEN entity in DB and not in API THEN getAppDetails returns network data`() = runTest {
        fakeDao.storedEntity = null
        fakeApi.dto = makeDto(id = "net-id", name = "Network App")
        val result = repository.getAppDetails("net-id")
        assertEquals("Network App", result.name)
    }


    // observeAppDetails()

    @Test
    fun `WHEN entity in DB and not in API THEN observeAppDetails emits mapped domain from DAO flow`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "obs-id", name = "Observed App")
        repository.observeAppDetails("obs-id").collect { result ->
            assertEquals("Observed App", result.name)
        }
    }

    @Test
    fun `WHEN entity in DB and not in API THEN observeAppDetails emits correct id`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "obs-id-2")
        repository.observeAppDetails("obs-id-2").collect { result ->
            assertEquals("obs-id-2", result.id)
        }
    }


    // toggleWishlist()

    @Test
    fun `GIVEN entity in DB WHEN toggleWishlist() THEN flips isInWishlist from false to true`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "w-id", isInWishlist = false)
        repository.toggleWishlist("w-id")
        assertTrue(fakeDao.lastWishlistStatus!!)
    }

    @Test
    fun `GIVEN entity in DB WHEN toggleWishlist() THEN flips isInWishlist from true to false`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "w-id", isInWishlist = true)
        repository.toggleWishlist("w-id")
        assertFalse(fakeDao.lastWishlistStatus!!)
    }

    @Test
    fun `GIVEN entity in DB WHEN toggleWishlist() THEN toggleWishlist passes correct id to dao`() = runTest {
        fakeDao.storedEntity = makeEntity(id = "toggle-id")
        repository.toggleWishlist("toggle-id")
        assertEquals("toggle-id", fakeDao.lastWishlistId)
    }








    private fun makeEntity(
        id: String = "test-id",
        name: String = "Test App",
        isInWishlist: Boolean = false
    ) = AppDetailsEntity(
        id = id, name = name, developer = "Dev", category = Category.PRODUCTIVITY,
        ageRating = 3, size = 8f, iconUrl = "icon", description = "desc",
        isInWishlist = isInWishlist
    )

    private fun makeDto(
        id: String = "test-id",
        name: String = "Test App"
    ) = AppDetailsDto(
        id = id, name = name, developer = "Dev", category = Category.PRODUCTIVITY,
        ageRating = 3, size = 8.0, icon = "icon", screenshots = null, description = "desc"
    )







    class FakeAppDetailsDao : AppDetailsDao {
        var storedEntity: AppDetailsEntity? = null
        var insertedEntity: AppDetailsEntity? = null
        var lastWishlistId: String? = null
        var lastWishlistStatus: Boolean? = null

        override fun getAppDetails(id: String): Flow<AppDetailsEntity?> =
            flowOf(storedEntity)

        override fun insertAppDetails(appDetails: AppDetailsEntity) {
            insertedEntity = appDetails
            storedEntity = appDetails
        }

        override fun updateWishlistStatus(id: String, isInWishlist: Boolean): Int {
            lastWishlistId = id
            lastWishlistStatus = isInWishlist
            return 1
        }
    }

    class FakeAppApi : AppApi {
        var dto: AppDetailsDto = AppDetailsDto("id", "name", "dev", Category.PRODUCTIVITY, 3, 8.0, "icon", null, "desc")
        var wasCalled = false

        override suspend fun getAppList(): List<com.android.vkeducation.baskaeva.data.applist.AppListItemDto> = emptyList()

        override suspend fun getAppDetails(id: String): AppDetailsDto {
            wasCalled = true
            return dto
        }
    }
}