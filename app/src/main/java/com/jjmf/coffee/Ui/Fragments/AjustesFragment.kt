package com.jjmf.coffee.Ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            cambiarLenguaje()
        }
        btnEngli.click {
            prefs.saveLenguaje("en")
            cambiarLenguaje()
        }

        btnClaro.click {
            actualizaTem(false)
        }
        btnOscuro.click {
            actualizaTem(true)
        }
    }

    private fun actualizaTem(booldlkjlkjlkj: Boolean) {
        if (booldlkjlkjlkj){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun cambiarLenguaje() {
        val idioma = prefs.getLenguaje()
        val displayMetrics = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(Locale(idioma))
        resources.updateConfiguration(config,displayMetrics)
        config.locale = Locale(idioma)
        resources.updateConfiguration(config,displayMetrics)

        binding.tvIdioma.text = getString(R.string.idioma)
        binding.btnEspa.text = getString(R.string.espa_ol)
        binding.btnEngli.text = getString(R.string.english)
    }
}