package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.R
import com.jjmf.coffee.Ui.ViewModel.LoginViewModel
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.Util.er
import com.jjmf.coffee.Util.texs
import com.jjmf.coffee.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel : LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        events()
        observadores()
    }
    private fun init() {
        binding.include.tvPrincipal.text = "¡Bienvenido de vuelta!"
    }

    private fun events() {
        binding.btnIngresar.click {
            ingresar()
        }
        binding.btnRegistrar.click {
            navigateToAction(R.id.action_loginFragment_to_registrarFragment)
        }
    }

    private fun observadores() {
        viewModel.usuario.observe(viewLifecycleOwner){
            if (it>0){
                navigateToAction(R.id.action_loginFragment_to_menuFragment)
            }else{
                val alert = SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                alert.titleText = "Usuario Invalido"
                alert.contentText = "Los datos ingresados no son correctos"
                alert.setConfirmButton("Confirmar"){
                    alert.dismissWithAnimation()
                }
                alert.show()
            }
        }
    }


    private fun ingresar() = with(binding) {
        val usuario = edtUsuario.texs()
        val clave = edtPass.texs()

        when (true) {
            usuario.isEmpty() -> tilUsuario.er()
            clave.isEmpty() -> tilPass.er()
            else -> {
                viewModel.login(usuario, clave, requireContext())
            }
        }
    }

}