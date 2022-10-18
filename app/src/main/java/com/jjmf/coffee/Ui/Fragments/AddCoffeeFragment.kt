package com.jjmf.coffee.Ui.Fragments

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.Permisos
import com.jjmf.coffee.R
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.databinding.FragmentAddCoffeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCoffeeFragment : BaseFragment<FragmentAddCoffeeBinding>(FragmentAddCoffeeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        events()
    }

    private var imagen:Uri? = null

    private fun init() {
        binding.icTitulo.tvPrincipal.text = "Añadir Coffee"
        binding.ivFoto.click {
            val alert = SweetAlertDialog(requireContext(),SweetAlertDialog.NORMAL_TYPE)
            alert.titleText = "Imagen"
            alert.contentText = "Seleccione de donde desee obtener la imagen"
            alert.setNeutralButton("Camara"){
                Permisos(this).camara { uri, intent ->
                    imagen = uri
                    resultado.launch(intent)
                }
                alert.dismissWithAnimation()
            }
            alert.setConfirmButton("Galeria"){
                Permisos(this).galeria {
                    resultado.launch(it)
                }
                alert.dismissWithAnimation()
            }
            alert.show()
        }
    }

    private val resultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            it.data?.data?.let{urr->
                imagen = urr
            }
            Glide.with(requireActivity()).load(imagen).into(binding.ivFoto)
        }
    }


    private fun events() {
        val nombre = binding.edtNombre.text.toString()
        val prepa = binding.edtPreparacion.text.toString()
    }
}