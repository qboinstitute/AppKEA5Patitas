package com.qbo.appkea5patitas.utilitarios

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea5patitas.R

object AppMensaje {

    fun enviarMensaje(vista: View, mensaje: String, tipo: TipoMensaje){
        val snackbar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        val snackbarview: View = snackbar.view
        if(tipo == TipoMensaje.ERROR){
            snackbarview.setBackgroundColor(
                ContextCompat.getColor(MiApp.instancia,
                        R.color.snackbarerror)
            )
        }else if(tipo == TipoMensaje.EXITO){
            snackbarview.setBackgroundColor(
                ContextCompat.getColor(MiApp.instancia,
                    R.color.snackbarexito)
            )
        }else{
            snackbarview.setBackgroundColor(
                ContextCompat.getColor(MiApp.instancia,
                    R.color.snackbaradver)
            )
        }
        snackbar.show()
    }

}