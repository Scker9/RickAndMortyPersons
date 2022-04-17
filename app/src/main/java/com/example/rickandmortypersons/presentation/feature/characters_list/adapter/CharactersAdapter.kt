package com.example.rickandmortypersons.presentation.feature.characters_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortypersons.R
import com.example.rickandmortypersons.data.entities.Character

class CharactersAdapter :
    PagingDataAdapter<Character, CharactersAdapter.CharactersViewHolder>(
        CharacterUIDiffCallback()
    ) {
    var onItemTouch: ((Int) -> Unit)? = null

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.characterNameTextView)
        private val raceTextView: TextView = itemView.findViewById(R.id.characterRaceTextView)
        private val sexTextView: TextView = itemView.findViewById(R.id.characterSexTextView)
        private val avatarImageView: ImageView =
            itemView.findViewById(R.id.characterAvatarImageView)

        fun bind(character: Character) {
            itemView.setOnClickListener {
                onItemTouch?.invoke(getItem(absoluteAdapterPosition)!!.id.toInt())
            }
            nameTextView.text = character.name
            raceTextView.text = character.race
            sexTextView.text = character.gender
            avatarImageView.load(character.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    private class CharacterUIDiffCallback :
        DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem == oldItem
        }


    }
}