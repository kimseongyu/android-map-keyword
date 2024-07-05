package campus.tech.kakao.map

import android.content.ContentValues
import android.content.Context

class SearchRepository(context: Context) {

    private val dbHelper = StoreInfoDBHelper(context)

    fun setDefaultData() {
        delAllStoreInfo()
        for (i in 1..100) {
            val storeInfo = StoreInfo("cafe ${i}th street", "충남대학교", " 카페")
            addStoreInfo(storeInfo)
        }
    }

    fun delAllStoreInfo() {
        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM ${StoreInfoEntry.TABLE_NAME}")
    }

    fun addStoreInfo(storeInfo: StoreInfo) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(StoreInfoEntry.STORE_NAME, storeInfo.name)
            put(StoreInfoEntry.STORE_LOCATION, storeInfo.location)
            put(StoreInfoEntry.STORE_CATEGORY, storeInfo.category)
        }
        db.insert(StoreInfoEntry.TABLE_NAME, null, values)
    }
}