package com.jjmf.coffee.Ui.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.jjmf.coffee.App.BaseApp.Companion.prefs
import com.jjmf.coffee.Core.BaseFragment
import com.jjmf.coffee.R
import com.jjmf.coffee.Util.click
import com.jjmf.coffee.databinding.FragmentAjustesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AjustesFragment : BaseFragment<FragmentAjustesBinding>(FragmentAjustesBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() = with(binding){
        when(prefs.getLenguaje()){
            "es"-> btnEspa.isChecked = true
            "en"-> btnEngli.isChecked = true

        }
        btnEspa.click {
            prefs.saveLenguaje("es")
            cambiarIdioma()
        }
        btnEngli.click {
            prefs.saveLenguaje("en")
            cambiarIdioma()
        }
        btnClaro.click {
            actualizaTem(false)
        }
        btnOscuro.click {
            actualizaTem(true)
        }
    }
    private fun actualizaTem(boold: Boolean) {
        if (boold){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun cambiarIdioma(){
        val locale = Locale(prefs.getLenguaje())
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireActivity().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)
        onAttach(requireContext())
        binding.tvIdioma.text = getString(R.string.idioma)
        binding.btnEspa.text = getString(R.string.espa_ol)
        binding.btnEngli.text = getString(R.string.english)
    }
}