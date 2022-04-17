package com.example.rickandmortypersons.presentation.feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.rickandmortypersons.databinding.CharactersFragmentBinding
import com.example.rickandmortypersons.presentation.base.BaseFragment
import com.example.rickandmortypersons.presentation.decorator.MarginItemDecoration
import com.example.rickandmortypersons.presentation.feature.adapter.CharactersAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersFragment : BaseFragment<CharactersFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharactersFragmentBinding
        get() = CharactersFragmentBinding::inflate


    private val viewModel: CharactersViewModel by viewModels()

    private val adapter by lazy { CharactersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterRecycler.addItemDecoration(MarginItemDecoration(32))
        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }
        adapter.onItemTouch = { id ->
            Log.d("CharactersFragment", id.toString())
        }

        adapter.addLoadStateListener {
            binding.swipeToRefreshLayout.isRefreshing = it.refresh == LoadState.Loading
            if (it.refresh is LoadState.Error) {
                onError((it.refresh as LoadState.Error).error.localizedMessage)
            }
        }
        binding.characterRecycler.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pager.collectLatest {
                adapter.submitData(it)
            }
        }
    }


    fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): CharactersFragment {
            return CharactersFragment()
        }
    }
}