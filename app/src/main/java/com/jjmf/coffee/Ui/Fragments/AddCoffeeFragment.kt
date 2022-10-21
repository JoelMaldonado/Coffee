package com.jjmf.coffee.Ui.Fragments

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Core.Permisos
import com.jjmf.coffee.Model.Coffee
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.AddCoffeeViewModel
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.databinding.FragmentAddCoffeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCoffeeFragment :
    BaseFragment<FragmentAddCoffeeBinding>(FragmentAddCoffeeBinding::inflate) {
    private val viewModel: AddCoffeeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        events()

    }

    private var imagen: Uri? = null

    private fun init() {
        binding.icTitulo.tvPrincipal.text = "Añadir Coffee"
        binding.cardFoto.click {
            val alerta = SweetAlertDialog(requireContext(), SweetAlertDialog.NORMAL_TYPE)
            alerta.titleText = "Imagen"
            alerta.contentText = "Seleccione de donde obtendra la imagen"
            alerta.setConfirmButton("Galeria") {
                alerta.dismissWithAnimation()
                Permisos(this).galeria { intent ->
                    imagenObtenida.launch(intent)
                }
            }
            alerta.setNeutralButton("Camara") {
                alerta.dismissWithAnimation()
                Permisos(this).camara() { uri, intent ->
                    imagen = uri
                    imagenObtenida.launch(intent)
                }
            }
            alerta.show()
        }
    }

    private val imagenObtenida =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    imagen = uri
                }
                Glide.with(requireContext()).load(imagen).into(binding.ivFoto)

            } else {
                show("No se obtuvo ninguna imagen")
            }
        }


    private fun events() {
        binding.btnAgregar.click {
            val nombre = binding.edtNombre.text.toString()
            val prepa = binding.edtPreparacion.text.toString()
            val imagen = if (imagen != null) imagen.toString() else ""
            val coffee = Coffee(nombre, prepa, imagen)
            viewModel.insert(coffee).observe(viewLifecycleOwner) {
                when (it) {
                    EstadosResult.Cargando -> {}
                    is EstadosResult.Correcto -> {
                        show(it.datos.toString())
                        findNavController().navigate(R.id.menu_coffeesFragment)
                    }
                    is EstadosResult.Error -> {}
                }
            }
        }
    }
}