package campus.tech.kakao.map

import android.provider.BaseColumns

data class SearchWord(
    val searchword: String
)

object SearchWordEntry : BaseColumns {
    const val TABLE_NAME = "searchWord"
    const val SEARCH_WORD = "searchWord"
}