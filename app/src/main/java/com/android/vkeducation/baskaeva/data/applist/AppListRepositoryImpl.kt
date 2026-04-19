package com.android.vkeducation.baskaeva.data.applist

import com.android.vkeducation.baskaeva.domain.applist.AppListItem
import com.android.vkeducation.baskaeva.domain.applist.AppListRepository
import javax.inject.Inject


class AppListRepositoryImpl @Inject constructor(
    private val mapper: AppListMapper
) : AppListRepository {

    private val hardcodedAppListDto = listOf(

        AppListItemDto(
            id = "1",
            name = "СберБанк Онлайн – с Салютом",
            subtitle = "Больше чем банк",
            category = "Финансы",
            icon = "https://avatars.mds.yandex.net/i?id=eaf1b1318e30a02bea6029d1febbaf03a26670bd-4628623-images-thumbs&n=13"
            ),

        AppListItemDto(
            id = "2",
            name = "Яндекс.Браузер — с Алисой",
            subtitle = "Быстрый и безопасный браузер",
            category = "Инструменты",
            icon = "https://avatars.mds.yandex.net/i?id=e52ac85a8113749260d4b606c0dc4cd979789079-11512952-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "3",
            name = "Почта Mail.ru",
            subtitle = "Почтовый клиент для любых ящиков",
            category = "Инструменты",
            icon = "https://yandex-images.clstorage.net/JwJ4X8224/164ed910/AvPOZCTOMeKn-I3ewYDPtdweJh6J69Lz_MEuJZJL0ClWtDdsWChtV_5yZXbPt8LABxEjODNFZYPmW8Z2qm5cxedni2AgClXzAn3Z37TAT4WMP_PKuPoXU7kQigBXr1D5g3Qn377vKnDo5ZYxHYDAFJIE5g0CoZSy8N23AKj6ayAR0t3XVPwyBUQY7KpQM14EPS6h3kkrBrOIAc7Bxw3X-MeBohxvTYkwKiUUgo0gQreQJsSf8WdFU9GcU6Bba3vRsPHf5FfcYZSmGa2q0RHsVe1_c90KyFdjCROskMb-92o2wdJMncyrIuu2JHF_QJDEUUdmHHJH1fKAXvJRigsr4ALwn3Xmb3GXJVv8KlADzcd8DfI9-Xu1UGuiLCMk7IeJ9dED_z8N2zC55jfRfMIBp9K2Ju2yVDVjUdzCYGj7WZGSUMz1VL2Rpcc5r9lxIo4Ezh4gDtoqtVEp0J5CJy_0eZYR4kxvzSgwaEbEMf7yAIaQRLVeQ8e2gtAMY6ML-GqTcaEs9UVP0uaEK77IQQC9df5-gl_5KMRye1CMcHWdxbmG8XI-j-0rAGvHxFGfMqBWQMRF3eFkdTHhzgFBuEl5cVDC7sZ1nJCVZ2nOe1NjfSacLRDdSxukQitAPrLFzDYbRlIzfF_cWEBJleVgnHBA1bB0VP0gRydRM_4Rkbq5iwIxcLyXx-3i5qYb7jjCAn23vq6SvvmaVrOrcV0R1S0VKwfRwAy9bHtgiHZHs-xgcfaABDStMRUl8oK8gSMIe-oRUvC9BlZ94_cVuL3I0oHMJt4uAg1oOKQwWkOfQlc-N7lX44AcfjwqcusmxmEPgoOEo4bkPnFF1JGTPsPiOGuqIVPR_lYmLgGV5omeSJBQrhXtLUK9GcvFovoAnKNGfdUKFgGh7O1NeqHYFndiL1LyBDLENX7wJfdSAY3CoBh5yfIT0Y4UFk5Dl9WaP0uAk80HDN0CnJs5pyJ5wf6gVD8UE",
        ),
        AppListItemDto(
            id = "4",
            name = "Яндекс Навигатор",
            subtitle = "Парковки и заправки – по пути",
            category = "Транспорт",
            icon = "https://avatars.mds.yandex.net/i?id=270652b9f072e3d54ccc9f5c9ded9ed0e59c5764-5904886-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "5",
            name = "Мой МТС",
            subtitle = "Мой МТС — центр экосистемы МТС",
            category = "Инструменты",
            icon = "https://avatars.mds.yandex.net/i?id=84540f8f6485d0be605ebc9891a4645fbc8379c9-5652956-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "6",
            name = "Яндекс — с Алисой",
            subtitle = "Яндекс — поиск всегда под рукой",
            category = "Инструменты",
            icon = "https://avatars.mds.yandex.net/i?id=e03b62b3fc6eda76cb8db6968761c93c3e8ea21d-4103093-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "7",
            name = "Wildberries",
            subtitle = "Как на алиэкспрессе, но дороже!",
            category = "Деньговысасыватель",
            icon = "https://avatars.mds.yandex.net/i?id=8e3efccb0ac64991b6ebbfb009f4e613735f2a0b-12421722-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "8",
            name = "Додо Пицца",
            subtitle = "Доставка еды и ресторан",
            category = "Еда",
            icon = "https://avatars.mds.yandex.net/i?id=f18f9e51e300526fa483a09fdcbbc8f2571b8b90-17394828-images-thumbs&n=13",
        ),
        AppListItemDto(
            id = "9",
            name = "Duolingo",
            subtitle = "Только забрось изучение испанского, птица придет за тобой",
            category = "Образование",
            icon = "https://avatars.mds.yandex.net/i?id=acd6451cd585ae76beb343ab7ad5fcf35d71a75f-4570154-images-thumbs&n=13",
        ),
    )

    override suspend fun getAppList(): List<AppListItem> {
        return hardcodedAppListDto.map{ mapper.toDomain(it) }
    }
}