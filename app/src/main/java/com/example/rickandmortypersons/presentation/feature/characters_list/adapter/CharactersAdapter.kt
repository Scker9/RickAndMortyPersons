package com.example.rickandmortypersons.presentation.feature.characters_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortypersons.R
import com.example.rickandmortypersons.databinding.CharacterRecyclerItemBinding
import com.example.rickandmortypersons.presentation.entities.CharacterListUI

class CharactersAdapter :
    PagingDataAdapter<CharacterListUI, CharactersAdapter.CharactersViewHolder>(
        CharacterUIDiffCallback()
    ) {
    var onItemTouch: ((Int) -> Unit)? = null

    inner class CharactersViewHolder(private val binding: CharacterRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameTextView: TextView = itemView.findViewById(R.id.characterNameTextView)
        private val raceTextView: TextView = itemView.findViewById(R.id.characterRaceTextView)
        private val sexTextView: TextView = itemView.findViewById(R.id.characterSexTextView)
        private val avatarImageView: ImageView =
            itemView.findViewById(R.id.characterAvatarImageView)

        fun bind(character: CharacterListUI) {
            with(binding) {
                root.setOnClickListener {
                    onItemTouch?.invoke(getItem(absoluteAdapterPosition)!!.id.toInt())
                }
                nameTextView.text = character.name
                raceTextView.text = character.race
                sexTextView.text = character.gender
                avatarImageView.load(character.image)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            CharacterRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    private class CharacterUIDiffCallback :
        DiffUtil.ItemCallback<CharacterListUI>() {
        override fun areItemsTheSame(
            oldItem: CharacterListUI,
            newItem: CharacterListUI
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterListUI,
            newItem: CharacterListUI
        ): Boolean {
            return oldItem == newItem
        }


    }
}