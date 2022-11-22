package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.R
import com.jjmf.coffee.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.IO){
            delay(3000)
            withContext(Dispatchers.Main){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }
}