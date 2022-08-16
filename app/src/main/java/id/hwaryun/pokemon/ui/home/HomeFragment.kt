package id.hwaryun.pokemon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.hwaryun.pokemon.data.model.Pokemon
import id.hwaryun.pokemon.databinding.FragmentHomeBinding
import id.hwaryun.pokemon.ui.MainViewModel
import id.hwaryun.pokemon.ui.adapter.PokemonAdapter
import id.hwaryun.pokemon.util.Result

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val pokemonAdapter by lazy { PokemonAdapter(::onItemClicked) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.btnAdd.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToAddEditFragment()
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.rvPokemon.adapter = pokemonAdapter
        binding.rvPokemon.layoutManager = GridLayoutManager(requireContext(), 2)
        observePokemon()
    }

    private fun observePokemon() {
        viewModel.getPokemon().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.rvPokemon.isVisible = false
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    binding.rvPokemon.isVisible = true
                    pokemonAdapter.submitList(result.data)
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onItemClicked(data: Pokemon) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.uuid)
        findNavController().navigate(directions)
    }
}