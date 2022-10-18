package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.R
import com.jjmf.coffee.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        events()
    }

    private fun events() {
        binding.bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_listado->{
                    Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMenu).navigate(R.id.menu_coffeesFragment)
                    true
                }
                R.id.menu_agregar->{
                    Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMenu).navigate(R.id.menu_addCoffeeFragment)
                    true
                }
                R.id.menu_favoritos->{
                    Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMenu).navigate(R.id.menu_favoriteCoffeeFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}