package campus.tech.kakao.map

import androidx.lifecycle.ViewModel

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    init{
        setDefaultData()
    }

    private fun setDefaultData() {
        searchRepository.setDefaultData()
    }
}