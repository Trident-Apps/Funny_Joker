package com.stanleyhks.b.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stanleyhks.b.R
import com.stanleyhks.b.databinding.StartGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartGameFragment : Fragment() {
    private var _binding: StartGameBinding? = null
    private val binding get() = _binding!!
    private var tries = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })

        listOf(
            binding.imageView,
            binding.imageView2,
            binding.imageView3,
            binding.imageView4,
            binding.imageView5,
            binding.imageView6
        ).forEach { imageView ->
            imageView.setOnClickListener {
                play(it as ImageView)
            }
        }
    }

    private fun play(view: ImageView) {
        view.animate().apply {
            duration = 1000L
            rotationYBy(360f)
        }.withEndAction {
            val list = listOf(1, 2, 3).shuffled()
            if (list[0] == 1) {
                lifecycleScope.launch {
                    view.setImageResource(R.drawable.ic)
                    delay(1000)
                    startAfterMatch(YOU_WON)
                }
            } else {
                val ivList = listOf(R.drawable.ic3, R.drawable.ic4).shuffled()
                view.setImageResource(ivList[0])
                view.isClickable = false
                tries--
                binding.triesTv.text = "You have $tries tries"
            }
            if (tries == 0) {
                lifecycleScope.launch {
                    delay(1000)
                    startAfterMatch(YOU_LOOSE)
                }
            }
        }
    }


    private fun startAfterMatch(text: String) {
        val bundle = bundleOf("text" to text)
        findNavController().navigate(R.id.victoryFragment, bundle)
    }

    companion object {
        const val YOU_WON = "You won, try again?"
        const val YOU_LOOSE = "You loose, try again?"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}