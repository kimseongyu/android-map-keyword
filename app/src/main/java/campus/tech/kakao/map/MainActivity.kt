package campus.tech.kakao.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import campus.tech.kakao.map.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchRepository = SearchRepository(this)
        val viewModelProviderFactory = SearchViewModelFactory(searchRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel::class.java)
    }
}
