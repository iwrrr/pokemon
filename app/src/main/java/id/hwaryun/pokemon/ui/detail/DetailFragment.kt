package id.hwaryun.pokemon.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import id.hwaryun.pokemon.databinding.FragmentDetailBinding
import id.hwaryun.pokemon.ui.MainViewModel
import id.hwaryun.pokemon.ui.adapter.EvolutionAdapter
import id.hwaryun.pokemon.util.Extensions.loadImage
import id.hwaryun.pokemon.util.Result

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    private val evolutionAdapter by lazy { EvolutionAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        binding.rvEvolution.adapter = evolutionAdapter
        binding.rvEvolution.layoutManager = LinearLayoutManager(requireContext())

        observeData()
    }

    private fun observeData() {
        viewModel.getPokemonById(args.uuid).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.cvData.isVisible = false
                    binding.cvEvolution.isVisible = false
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    binding.cvData.isVisible = true
                    binding.cvEvolution.isVisible = true
                    if (result.data != null) {
                        binding.apply {
                            ivPokemon.loadImage(result.data.avatar)
                            tvName.text = result.data.name
                            tvNumber.text = result.data.number
                            tvClassification.text = result.data.classification
                            result.data.types?.forEach {
                                setupChipTypes(it?.name)
                            }
                            result.data.resistances?.forEach {
                                setupChipResistances(it.name)
                            }

                            if (!result.data.evolutions.isNullOrEmpty()) {
                                evolutionAdapter.submitList(result.data.evolutions)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Evo tidak ada",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    binding.cvData.isVisible = false
                    binding.cvEvolution.isVisible = false
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupChipTypes(types: String?) {
        val chip = Chip(requireContext())
        chip.apply {
            text = types
            isClickable = false
            binding.chipTypes.addView(chip)
            setOnCloseIconClickListener {
                binding.chipTypes.removeView(chip)
            }
        }
    }

    private fun setupChipResistances(resistant: String?) {
        val chip = Chip(requireContext())
        chip.apply {
            text = resistant
            isClickable = false
            binding.chipResistants.addView(chip)
            setOnCloseIconClickListener {
                binding.chipTypes.removeView(chip)
            }
        }
    }
}