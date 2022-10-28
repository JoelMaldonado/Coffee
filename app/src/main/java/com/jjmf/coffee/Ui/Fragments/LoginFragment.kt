package com.jjmf.coffee.Ui.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jjmf.coffee.App.BaseApp.Companion.prefs
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
        detectarIdioma()
        binding.include.tvPrincipal.text = getString(R.string.tvBienvenido)
        binding.include.btnPuntos.isGone = false

        binding.edtUsuario.setText("joel")
        binding.edtPass.setText("12345678")
        desplegable()
    }

//TODO hacer menu para configuracion
    //TODO el radiobutton del idioma no se automatiza segun el idioma del tlf
    //TODO el radiobutton del tema debe seleccionarse automaticamente segun el tema que tenga el tlf
    //TODO en tu trabajo solo lavoran con modo claro, pero Que pasa si el alguien tiene el tlf en modo oscuro? como eliminar ese modo?
    //TODO carpeta para AppDatabase y UsuarioDao
    //TODO modificar en github y mandarlo a mi companero

    private fun detectarIdioma(){
        val locale = Locale(prefs.getLenguaje())
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireActivity().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)
        onAttach(requireContext())
        binding.include.tvPrincipal.text = getString(R.string.tvBienvenido)
        binding.tilUsuario.hint = getString(R.string.edtUsu)
        binding.tilPass.hint = getString(R.string.edtPass)
        binding.btnIngresar.text = getString(R.string.ingresar)
        binding.btnRegistrar.text = getString(R.string.registrar)
        binding.tvIdioma.text = getString(R.string.idioma)
        binding.tvEspa.text = getString(R.string.espa_ol)
        binding.tvEnglish.text = getString(R.string.english)
        if (prefs.getLenguaje() == "es"){
            binding.checkEspa.isVisible = true
            binding.checkEng.isVisible = false
        }else{
            binding.checkEspa.isVisible = false
            binding.checkEng.isVisible = true
        }
    }

    private fun desplegable() {
        binding.linearTitulo.click{
            if (binding.linearContenido.isGone){
                TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                binding.linearContenido.isGone = false
            }else {
                TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                binding.linearContenido.isGone = true
            }
        }
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
        binding.linearEspa.click {
            prefs.saveLenguaje("es")
            detectarIdioma()
        }
        binding.linearEng.click {
            prefs.saveLenguaje("en")
            detectarIdioma()
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