package campus.tech.kakao.map

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import campus.tech.kakao.map.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchRepository = SearchRepository(this)
        val viewModelProviderFactory = SearchViewModelFactory(searchRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[SearchViewModel::class.java]

        delSearchWord()
        detectSearchWindowChanged()
        getSavedSearchWord()
    }

    private fun delSearchWord() {
        binding.delSearchWord.setOnClickListener {
            binding.searchWindow.text = null
        }
    }

    private fun detectSearchWindowChanged() {
        binding.searchWindow.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchWord = SearchWord(s.toString())
                viewModel.getSearchResults(searchWord)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        lifecycleScope.launch {
            viewModel.searchResults.collect {
                if (it.isEmpty()) {
                    showView(binding.searchResultEmpty, true)
                    showView(binding.searchResultsList, false)
                } else {
                    showView(binding.searchResultEmpty, false)
                    showView(binding.searchResultsList, true)
                    binding.searchResultsList.adapter = SearchResultsAdapter(it, layoutInflater, viewModel::saveSearchWord)
                    binding.searchResultsList.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }
    }

    private fun getSavedSearchWord() {
        lifecycleScope.launch {
            viewModel.savedSearchWords.collect{
                binding.savedSearchWordsList.adapter = SavedSearchWordsAdapter(it, layoutInflater, viewModel::delSavedSearchWord)
                binding.savedSearchWordsList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun showView(view: View, isShow: Boolean) {
        view.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}
