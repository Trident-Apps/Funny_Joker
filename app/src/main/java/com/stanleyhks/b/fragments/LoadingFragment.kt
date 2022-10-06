package com.stanleyhks.b.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stanleyhks.b.R
import com.stanleyhks.b.model.UrlDatabase
import com.stanleyhks.b.repository.JokerRepository
import com.stanleyhks.b.viewmodel.JokerViewModel
import com.stanleyhks.b.viewmodel.JokerViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoadingFragment : Fragment(R.layout.loading_fragment) {
    lateinit var viewModel: JokerViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = UrlDatabase.getInstance(requireContext()).urlDao
        val rep = JokerRepository(dao)

        val viewModelFactory =
            JokerViewModelFactory(requireActivity().application, rep)

        viewModel = ViewModelProvider(this, viewModelFactory)[JokerViewModel::class.java]
        Log.d("customTag", "vm created")

        lifecycleScope.launch(Dispatchers.IO) {
            val savedUrl = viewModel.urlEntity?.url
            Log.d("customTag", "saved url is $savedUrl")
            if (savedUrl == null) {
                    viewModel.getDeepLink(requireActivity())
                    Log.d("customTag", "started apps")

                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.urlLiveData.observe(viewLifecycleOwner) {
                        startWeb(it)
                        Log.d("customTag", "started web from new")
                    }
                }
            } else {
                lifecycleScope.launch(Dispatchers.Main) {
                    startWeb(savedUrl.toString())
                    Log.d("customTag", "started web from saved")
                }
            }
        }
    }

    private fun startWeb(url: String) {
        val bundle = bundleOf("url" to url)
        findNavController().navigate(R.id.webFragment, bundle)
    }
}