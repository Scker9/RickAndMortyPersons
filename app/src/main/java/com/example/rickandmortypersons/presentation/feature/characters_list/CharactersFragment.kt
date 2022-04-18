package com.example.rickandmortypersons.presentation.feature.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.rickandmortypersons.databinding.CharactersFragmentBinding
import com.example.rickandmortypersons.presentation.Screens
import com.example.rickandmortypersons.presentation.base.BaseFragment
import com.example.rickandmortypersons.presentation.feature.characters_list.adapter.CharactersAdapter
import com.example.rickandmortypersons.presentation.feature.characters_list.adapter.decorator.MarginItemDecoration
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharactersFragment : BaseFragment<CharactersFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharactersFragmentBinding
        get() = CharactersFragmentBinding::inflate

    private val router by inject<Router>()

    private val viewModel by viewModels<CharactersViewModel>()

    private val adapter by lazy { CharactersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterRecycler.addItemDecoration(MarginItemDecoration(32))
        binding.swipeToRefreshLayout.isEnabled = false

        adapter.onItemTouch = { id ->
            navigateToDetail(id)
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


    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetail(characterId: Int) {
        router.navigateTo(Screens.getCharacterDetailFragment(characterId))
    }

    companion object {
        fun newInstance(): CharactersFragment {
            return CharactersFragment()
        }
    }
}