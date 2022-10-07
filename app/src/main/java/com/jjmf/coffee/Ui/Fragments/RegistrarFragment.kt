package com.jjmf.coffee.Ui.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Model.Usuario
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.RegistrarViewModel
import com.jjmf.coffee.Util.clear
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.Util.er
import com.jjmf.coffee.Util.texs
import com.jjmf.coffee.databinding.CalendarioBinding
import com.jjmf.coffee.databinding.FragmentRegistrarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrarFragment : BaseFragment<FragmentRegistrarBinding>(FragmentRegistrarBinding::inflate) {
    private val viewModel : RegistrarViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        events()
    }
    private fun init() {
        binding.include.tvPrincipal.text = "Bienvenido, Nuevo Usuari@"
        limpiadores()
        spn()
    }

    private fun spn() {
        val lista = listOf("Masculino","Femenino", "Otro")
        val array = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, lista)
        binding.spnGenero.setAdapter(array)
    }

    private fun limpiadores() = with(binding){
        edtNombre.clear(tilNombre)
        edtApellido.clear(tilApellido)
        edtFechaNac.clear(tilFechaNac)
        spnGenero.clear(tilGenero)
        edtUsuario.clear(tilUsuario)
        edtPass.clear(tilPass)
    }

    @SuppressLint("SetTextI18n")
    private fun events() {
        binding.btnRegistrar.click {
            registrar()
        }
        binding.tilFechaNac.setEndIconOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val view = layoutInflater.inflate(R.layout.calendario,null,false)
            val bind2 = CalendarioBinding.bind(view)
            val alert = builder.setView(view).create()
            bind2.btnConfirmar.click {
                val year = bind2.calendarView.year
                val month = bind2.calendarView.month
                val day = bind2.calendarView.dayOfMonth
                binding.edtFechaNac.setText("$day/$month/$year")
                alert.dismiss()
            }
            alert.show()
        }
    }

    private fun registrar() = with(binding){
        val nombre = edtNombre.texs()
        val apellido = edtApellido.texs()
        val fechaNac = edtFechaNac.texs()
        val genero = spnGenero.texs()
        val usuario = edtUsuario.texs()
        val clave = edtPass.texs()

        when(true){
            nombre.isEmpty()-> tilNombre.er()
            apellido.isEmpty()-> tilApellido.er()
            fechaNac.isEmpty()-> tilFechaNac.er()
            genero.isEmpty()-> tilGenero.er()
            usuario.isEmpty()-> tilUsuario.er()
            clave.isEmpty()-> tilPass.er()
            else->{
                val user = Usuario(nombre, apellido, fechaNac, genero, usuario, clave)
                viewModel.insertarUsuario(user).observe(viewLifecycleOwner){resultado->
                    when(resultado){
                        EstadosResult.Cargando -> {
                            //TODO Encender Cargando
                        }
                        is EstadosResult.Correcto -> {
                            //TODO Apagar Cargando
                            show(resultado.datos.toString())
                            findNavController().popBackStack()
                        }
                        is EstadosResult.Error -> {
                            //TODO Apagar Cargando
                            show(resultado.mensajeError)
                        }
                    }
                }
            }
        }
    }

}