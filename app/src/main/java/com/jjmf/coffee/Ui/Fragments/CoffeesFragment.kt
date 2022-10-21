package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jjmf.coffee.Core.BaseAdapter
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Model.Coffee
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.CoffeesViewModel
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.databinding.CardCoffeesBinding
import com.jjmf.coffee.databinding.FragmentCoffeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeesFragment : BaseFragment<FragmentCoffeesBinding>(FragmentCoffeesBinding::inflate) {
    private val  viewModel : CoffeesViewModel by viewModels()
    private val adaptador = object : BaseAdapter<Coffee>(emptyList()){
        override fun getViewHolder(parent: ViewGroup): BaseAdapterViewHolder<Coffee> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_coffees,parent,false)
            val bind2 = CardCoffeesBinding.bind(view)
            return object : BaseAdapterViewHolder<Coffee>(view){
                override fun bind(entity: Coffee) {
                    bind2.tvNombre.text = entity.nombre
                    Glide.with(requireContext()).load(entity.foto.toUri()).error(R.drawable.cafe).into(bind2.ivFoto)
                    bind2.btnVer.click {
                        val dir = CoffeesFragmentDirections.actionMenuCoffeesFragmentToDetalleFragment(entity)
                        navigateToDirections(dir)
                    }
                }
            }
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        binding.recycler.adapter = adaptador
        binding.icTitulo.tvPrincipal.text = "Listado Coffee"
        geTList()
    }

    private fun geTList() {
        viewModel.getListLiveData().observe(viewLifecycleOwner){
            when(it){
                EstadosResult.Cargando -> {}
                is EstadosResult.Correcto -> {
                    it.datos!!.observe(viewLifecycleOwner){list->
                        adaptador.update(list)
                    }
                }
                is EstadosResult.Error -> {}
            }
        }
    }
}