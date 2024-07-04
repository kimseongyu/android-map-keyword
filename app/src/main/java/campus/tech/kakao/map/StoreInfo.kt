package campus.tech.kakao.map

import android.provider.BaseColumns

data class StoreInfo(
    val name: String,
    val location: String,
    val category: String
)

object StoreInfoEntry : BaseColumns {
    const val TABLE_NAME = "storeInfo"
    const val STORE_NAME = "name"
    const val STORE_LOCATION = "location"
    const val STORE_CATEGORY = "category"
}