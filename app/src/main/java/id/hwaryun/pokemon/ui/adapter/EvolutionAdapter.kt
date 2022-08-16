package id.hwaryun.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.hwaryun.pokemon.data.model.Pokemon
import id.hwaryun.pokemon.databinding.ItemEvolutionBinding
import id.hwaryun.pokemon.util.Extensions.loadImage

class EvolutionAdapter(
    private val onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, EvolutionAdapter.PokemonViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            ItemEvolutionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PokemonViewHolder(private val binding: ItemEvolutionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Pokemon) {
            with(binding) {
                tvNumber.text = data.number
                tvName.text = data.name
                ivPokemon.loadImage(data.avatar)
            }
            itemView.setOnClickListener { onClick(data) }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

}