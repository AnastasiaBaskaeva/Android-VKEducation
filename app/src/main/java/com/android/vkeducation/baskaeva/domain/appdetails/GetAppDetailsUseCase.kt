package com.android.vkeducation.baskaeva.domain.appdetails

import kotlinx.coroutines.flow.Flow

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): AppDetails =
        appDetailsRepository.getAppDetails(id)

    fun observe(id: String): Flow<AppDetails> =
        appDetailsRepository.observeAppDetails(id)

    suspend fun toggleWishlist(id: String) =
        appDetailsRepository.toggleWishlist(id)
}