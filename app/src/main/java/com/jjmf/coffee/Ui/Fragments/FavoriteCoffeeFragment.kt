package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.R
import com.jjmf.coffee.databinding.FragmentFavoriteCoffeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCoffeeFragment : BaseFragment<FragmentFavoriteCoffeeBinding>(FragmentFavoriteCoffeeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.icTitulo.tvPrincipal.text = "Favorite Coffee"
    }
}