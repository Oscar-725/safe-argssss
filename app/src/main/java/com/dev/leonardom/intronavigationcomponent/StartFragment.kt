package com.dev.leonardom.intronavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.leonardom.intronavigationcomponent.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    //...la dejamos vacia (empty) y nos vamos al dialogo en donde el usuario va seleccionar una
    //opcion
    private var mOpcionSeleccionada = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGameMode.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToGameModeDialog()
            findNavController().navigate(action)
        }


        binding.btnLogin.setOnClickListener {
            iniciarSesion()
        }

        //Creamos funcion observar para recibir lo que el usuario eligio en el modo de juego
        observarSuscriptores()
    }

    private fun iniciarSesion() {
        //Creamos objeto de tipo Login, para esto necesitamos las variables que requiere la clase Login
        val usuario = binding.textInputEditTextUsuario.text.toString()
        val contra = binding.textInputEditTextContrasena.text.toString()
        //Para obtener modo de juego , creamos variable global... e implemetamos lo necesario
        //cuando mOpcionSeleccionada tiene un valor (result) se la agregamos a modoJuego
        val modoJuego = mOpcionSeleccionada

        val login = Login(modoJuego, usuario, contra)

        val action = StartFragmentDirections.actionStartFragmentToGameFragment(login)
        //Cuando navegemos al siguiente fragment le vamos a pasar la informacion que tiene el login
        //Nos vamos a la actividad de GameFragment para recibir datos
        findNavController().navigate(action)
    }

    //Implementamos funcion de observar, para que solo este observando en el periodo de vida de
    //este fragment
    private fun observarSuscriptores() {
        findNavController()
                .currentBackStackEntry
                //Investigar
                ?.savedStateHandle
                ?.getLiveData("gameModeKey", "")
                //Creamos landa y tenemos acceso al resultado que elegio el usuario
                ?.observe(viewLifecycleOwner){ result ->
                    mOpcionSeleccionada = result
                }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//Para pasar datos a otro fragment
//1 creamos funcion iniciarSesion(), en esta funcion usaremos algunas funciones de kotlin para
//interactuar con el dialog (selecciona juego)