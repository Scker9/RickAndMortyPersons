package com.example.rickandmortypersons.presentation.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickandmortypersons.databinding.CharacterDetailFragmentBinding
import com.example.rickandmortypersons.presentation.base.BaseFragment

class CharacterDetailFragment : BaseFragment<CharacterDetailFragmentBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharacterDetailFragmentBinding
        get() = CharacterDetailFragmentBinding::inflate

    private val viewModel by viewModels<CharacterDetailViewModel>()
    private var characterID: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterID = arguments?.getInt(TAG)
        viewModel.characterLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                characterAvatarImageView.load(it.image)
                characterNameTextView.text = it.name
                characterLocationTextView.text = it.location
                characterEpisodesTextView.text = it.episodeCount
                characterRaceTextView.text = it.race
                characterSexTextView.text = it.gender
                characterStatusTextView.text = it.status
            }
        }
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                CharacterDetailState.LOADING -> {
                    viewModel.fetchData(characterID!!)
                }
                CharacterDetailState.SHOW -> {
                    binding.detailProgressBar.visibility = View.GONE
                    binding.detailConstraint.visibility = View.VISIBLE
                }
                CharacterDetailState.ERROR -> {
                    onError(viewModel.getErrorMsg())
                }
            }
        }
    }

    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "CharacterID"
        fun newInstance(characterId: Int): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val bundle = Bundle()
            bundle.putInt(TAG, characterId)
            fragment.arguments = bundle
            return fragment
        }
    }
}