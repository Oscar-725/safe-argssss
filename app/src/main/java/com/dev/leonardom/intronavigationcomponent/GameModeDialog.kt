package com.dev.leonardom.intronavigationcomponent

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class GameModeDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val gameModeOptions = arrayOf("Modo 1", "Modo 2", "Modo 3", "Modo 4")
        //2.-Creamos variable global para asignarle el valor que seleccione el usuario
        var optionSelected = gameModeOptions[0]

        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle("Dialogo de Prueba")

                    //Establecer elemento de unica seleccion
                .setSingleChoiceItems(gameModeOptions, 0){ dialog, posicion ->
                    //Asignamos el valor que seleccione el usuario , a la variable opcion seleccionada
                    optionSelected = gameModeOptions[posicion]
                }
                .setPositiveButton("ACEPTAR") { dialog, id ->
                    //1.-Accedemos a nuestro controlador y colocamos variables, que hacen exactamente?
                    //colocamos .set(llave , valor), con esto tenemos que recibir respuesta en startfragment
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("gameModeKey", optionSelected)

                    //Para desaparecer dialogo una vez que le de aceptar el usuario
                    dialog.dismiss()
                }
                .setNegativeButton("CANCELAR"){ dialog, id ->
                    dialog.dismiss()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}

