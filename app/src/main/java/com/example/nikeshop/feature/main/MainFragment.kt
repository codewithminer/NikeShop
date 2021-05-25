package com.example.nikeshop.feature.main

import com.example.nikeshop.NikeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: NikeFragment() {

    val mainViewModel: MainViewModel by viewModel()

}