package com.qbo.appkea5patitas.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.qbo.appkea5patitas.R
import com.qbo.appkea5patitas.databinding.FragmentVoluntarioBinding
import com.qbo.appkea5patitas.utilitarios.AppMensaje
import com.qbo.appkea5patitas.utilitarios.TipoMensaje
import com.qbo.appkea5patitas.viewmodel.MascotaViewModel


class VoluntarioFragment : Fragment() {

    private var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoluntarioBinding.inflate(inflater, container,
            false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)

        binding.btnregvoluntario.setOnClickListener {
            if(binding.cbaceptar.isChecked){
                binding.btnregvoluntario.isEnabled = false
                //mascotaViewModel.registrarVoluntario()
            }else{
                AppMensaje.enviarMensaje(binding.root,
                "Acepte los términos y condiciones para ser voluntario",
                TipoMensaje.ADVERTENCIA)
            }
        }

        return binding.root
    }

}