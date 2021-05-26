package com.example.nikeshop.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nikeshop.common.NikeFragment
import com.example.nikeshop.R
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainFragment: NikeFragment() {

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.productsLiveData.observe(viewLifecycleOwner) {
           Timber.i(it.toString())
        }

        mainViewModel.progressBarLiveData.observe(viewLifecycleOwner){
            setProgressIndicator(it)
        }
    }

}