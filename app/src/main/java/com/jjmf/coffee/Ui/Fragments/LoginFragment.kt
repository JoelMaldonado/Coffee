package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.coffee.App.BaseApp
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.LoginViewModel
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.Util.er
import com.jjmf.coffee.Util.texs
import com.jjmf.coffee.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel : LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        events()
    }
    private fun init() {
        idioma()
        binding.include.tvPrincipal.text = "¡Bienvenido de vuelta!"
        binding.include.btnPuntos.isGone = false
    }
//TODO hacer menu para configuracion
    //TODO el radiobutton del idioma no se automatiza segun el idioma del tlf
    //TODO el radiobutton del tema debe seleccionarse automaticamente segun el tema que tenga el tlf
    //TODO en tu trabajo solo lavoran con modo claro, pero Que pasa si el alguien tiene el tlf en modo oscuro? como eliminar ese modo?
    private fun idioma() {
        val idioma = BaseApp.prefs.getLenguaje()
        val displayMetrics = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(Locale(idioma))
        resources.updateConfiguration(config,displayMetrics)
        config.locale = Locale(idioma)
        resources.updateConfiguration(config,displayMetrics)

        binding.tilUsuario.hint = getString(R.string.edtUsu)
        binding.tilPass.hint = getString(R.string.edtPass)
        binding.btnIngresar.hint = getString(R.string.ingresar)
        binding.btnRegistrar.hint = getString(R.string.registrar)
    }

    private fun events() {
        binding.btnIngresar.click {
            ingresar()
        }
        binding.btnRegistrar.click {
            navigateToAction(R.id.action_loginFragment_to_registrarFragment)
        }
        binding.include.btnPuntos.click {
            navigateToAction(R.id.action_loginFragment_to_ajustesFragment)
        }
    }


    private fun ingresar() = with(binding) {
        val usuario = edtUsuario.texs()
        val clave = edtPass.texs()

        when (true) {
            usuario.isEmpty() -> tilUsuario.er()
            clave.isEmpty() -> tilPass.er()
            else -> {
                viewModel.loginNuevo(usuario,clave).observe(viewLifecycleOwner) { estadoUsuario ->
                    when (estadoUsuario){
                        EstadosResult.Cargando -> {
                            //TODO Encender Cargando
                        }
                        is EstadosResult.Correcto -> {
                            //TODO Apagar Cargando
                            val usuario = estadoUsuario.datos!!
                            show("Hola "+usuario.usuario)
                            navigateToAction(R.id.action_loginFragment_to_menuFragment)
                        }
                        is EstadosResult.Error -> {
                            //TODO Apagar Cargando
                            show(estadoUsuario.mensajeError)
                        }
                    }

                }
            }
        }
    }

}