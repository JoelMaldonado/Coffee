package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Model.Coffee
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.DetalleViewModel
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.databinding.FragmentDetalleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleFragment : BaseFragment<FragmentDetalleBinding>(FragmentDetalleBinding::inflate) {
    private val viewModel : DetalleViewModel by viewModels()
    private lateinit var cafe : Coffee
    private var bool = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
        events()
    }

    private fun events() {
        binding.btnEliminar.click {
            alertaEliminar()
        }
        binding.btnFavorito.click {
            viewModel.actualizar(cafe.copy(favorito = !cafe.favorito)).observe(viewLifecycleOwner){
                when(it){
                    EstadosResult.Cargando -> {}
                    is EstadosResult.Correcto -> {
                        cafe.favorito = !cafe.favorito
                        setearFavorito()
                    }
                    is EstadosResult.Error -> {}
                }
            }
        }
        binding.btnEditar.click {
            bool = !bool
            if (bool){
                binding.tilDescrip.visibility = View.VISIBLE
                binding.tvDescrip.visibility = View.GONE
                binding.btnActualizar.visibility = View.VISIBLE
            }else{
                binding.tilDescrip.visibility = View.GONE
                binding.tvDescrip.visibility = View.VISIBLE
                binding.btnActualizar.visibility = View.GONE
            }
        }
        binding.btnActualizar.click {
            val edt = binding.edtDescrip.text.toString()
            cafe.preparacion = edt
            viewModel.actualizar(cafe).observe(viewLifecycleOwner){
                when(it){
                    EstadosResult.Cargando -> {}
                    is EstadosResult.Correcto -> {
                        findNavController().popBackStack()
                    }
                    is EstadosResult.Error -> {}
                }
            }
        }
    }

    private fun alertaEliminar() {
        val alert = SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
        alert.titleText = "Cuidado"
        alert.contentText = "Estas apunto de eliminar ${cafe.nombre}, estas Seguro?"
        alert.setCancelButton("Cancelar") { alert.dismissWithAnimation() }
        alert.setConfirmButton("Confirmar") {
            alert.dismissWithAnimation()
            viewModel.delete(cafe).observe(viewLifecycleOwner){resultado->
                when(resultado){
                    EstadosResult.Cargando -> {
                        binding.progress.isGone = false
                    }
                    is EstadosResult.Correcto -> {
                        binding.progress.isGone = true
                        show(resultado.datos.toString())
                        findNavController().popBackStack()
                    }
                    is EstadosResult.Error -> {
                        binding.progress.isGone = true
                        show(resultado.mensajeError)
                    }
                }
            }
        }
        alert.show()
    }

    private fun init() {
        arguments?.let {
            cafe = DetalleFragmentArgs.fromBundle(it).cafe
        }

        setearInfo()
    }

    private fun setearInfo() = with(binding){
        binding.icTitulo.tvPrincipal.text = cafe.nombre
        tvDescrip.text = cafe.preparacion
        Glide.with(requireContext()).load(cafe.foto).error(R.drawable.cafe).into(ivFoto)
        edtDescrip.setText(cafe.preparacion)
        setearFavorito()
    }

    private fun setearFavorito() = with(binding) {
        if (cafe.favorito){
            btnFavorito.setImageResource(R.drawable.ic_favorito_si)
        }else {
            btnFavorito.setImageResource(R.drawable.ic_favorito_no)
        }
    }
}