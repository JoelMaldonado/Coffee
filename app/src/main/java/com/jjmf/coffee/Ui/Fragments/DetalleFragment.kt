package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Model.Coffee
import com.jjmf.coffee.R
import com.jjmf.coffee.databinding.FragmentDetalleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleFragment : BaseFragment<FragmentDetalleBinding>(FragmentDetalleBinding::inflate) {
    private lateinit var cafe : Coffee
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
    }

    private fun init() {
        arguments?.let {
            cafe = DetalleFragmentArgs.fromBundle(it).cafe
        }

        setearInfo()
    }

    private fun setearInfo() = with(binding){
        tvNombre.text = cafe.nombre
        tvDescrip.text = cafe.preparacion
        Glide.with(requireContext()).load(cafe.foto).error(R.drawable.cafe).into(ivFoto)
    }
}