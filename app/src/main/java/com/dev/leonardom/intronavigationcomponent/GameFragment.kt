package com.dev.leonardom.intronavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dev.leonardom.intronavigationcomponent.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    //Creamos variable, de tipo argumentos de GameFragment y accedemos a navArgs()
    private val args: GameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Asignamos al textViwe los datos que estan en la variable y para definir como mostrarlos
        //Sobreescribimos el metodo toString en la clase login
        binding.tvDatosUsuario.text = args.login.toString()

        binding.btnSalir.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToWelcomeFragment()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}