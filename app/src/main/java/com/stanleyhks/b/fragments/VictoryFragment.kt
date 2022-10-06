package com.stanleyhks.b.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stanleyhks.b.R
import com.stanleyhks.b.databinding.VictoryFragmentBinding

class VictoryFragment: Fragment() {
    private var _binding: VictoryFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VictoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })

        binding.victoryText.text = arguments?.getString("text")
        binding.victoryBtn.setOnClickListener {
            findNavController().navigate(R.id.startGameFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}