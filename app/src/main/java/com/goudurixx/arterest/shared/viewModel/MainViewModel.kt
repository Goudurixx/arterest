package com.goudurixx.arterest.shared.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val localList = List(20) { index ->
        ArterestPost(
            id = index.toString(),
            imageUrl = "https://picsum.photos/200/300?random=$index",
            title = "Post Title $index",
            description = "This is the description for post $index."
        )
    }

    private val _posts = MutableStateFlow<List<ArterestPost>>(emptyList())
    val posts: StateFlow<List<ArterestPost>> get() = _posts.asStateFlow()

    private var _searchTerm = MutableStateFlow<String>("")
    val searchTerm: StateFlow<String> = _searchTerm.asStateFlow()

    private var _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun search(term: String, limit: Int = 12) {
        if (term.isBlank()) {
            // reject
            return
        }

        _loading.update { true }

        viewModelScope.launch {
            try {
                val response = localList.filter {
                    it.title.contains(searchTerm.value)
                }

                _posts.update { response }
            } catch (th: Throwable) {
                Log.e("MainViewModel", "Error while fetching posts with tern $term.", th)
            } finally {
                _loading.update { false }
            }
        }
    }

    init {
        // Simulate loading posts
        _posts.value = localList
    }
}

data class ArterestPost(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String
)