package mx.edu.ittepic.tpdm_u2_practica3_15400998

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.OutputStreamWriter
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var n: EditText? = null
    var m: EditText? = null
    var boton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        n = findViewById(R.id.numN)
        m = findViewById(R.id.numM)
        boton = findViewById(R.id.ejecutar)

        ejecutar?.setOnClickListener{
            Generar(
                n?.text.toString(),
                m?.text.toString(),
                applicationContext
            ).execute()
        }
    }

    class Generar(i: String, f: String, context: Context) : AsyncTask<Void, Void, List<Int>>() {
        var i = i.toInt()
        var f = f.toInt()

        override fun doInBackground(vararg p0: Void?): List<Int> {
            val num = List(2000) {
                Random.nextInt(i,f)
            }
            return num
        }

        override fun onPostExecute(result: List<Int>?) {
            super.onPostExecute(result)
            var conteo = 0
            var et = ""
            (0..1999).forEach {
                conteo = 0
                et = result?.get(it).toString()
                (1..et.toInt()).forEach {
                    if (et.toInt() % it == 0) {
                        conteo++
                    }
                }
            }
        }
    }
    /*fun guardar(dato: Int){
        val guardarArchivo = OutputStreamWriter(openFileOutput("datos.txt", Activity.MODE_PRIVATE))
        guardarArchivo.write(conteo.toString())
        guardarArchivo.flush()
        guardarArchivo.close()

        val alerta = AlertDialog.Builder(this)
        alerta.setTitle("Atención").setMessage("Se guardo en archivo").setPositiveButton("Aceptar"){dialogInterface, i ->
        }.show()
    }*/
}
