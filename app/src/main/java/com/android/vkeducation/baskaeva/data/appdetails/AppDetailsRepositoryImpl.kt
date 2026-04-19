package com.android.vkeducation.baskaeva.data.appdetails

import com.android.vkeducation.baskaeva.data.AppApi
import com.android.vkeducation.baskaeva.data.appdetails.local.AppDetailsDao
import com.android.vkeducation.baskaeva.data.appdetails.local.AppDetailsEntityMapper
import com.android.vkeducation.baskaeva.domain.appdetails.AppDetails
import com.android.vkeducation.baskaeva.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
)  : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val entity = dao.getAppDetails(id).first()
        return if (entity != null) {
            entityMapper.toDomain(entity)
        } else {
            val dto = appApi.getAppDetails(id)
            val domain = mapper.toDomain(dto)
            withContext(Dispatchers.IO) {
                dao.insertAppDetails(entityMapper.toEntity(domain))
            }
            domain
        }
    }
}