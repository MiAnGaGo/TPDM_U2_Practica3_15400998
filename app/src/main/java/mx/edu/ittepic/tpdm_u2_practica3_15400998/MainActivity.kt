package mx.edu.ittepic.tpdm_u2_practica3_15400998

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        var con = context

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
            var np = "Resultado de los numeros primos generados en el rango dado: "
            (0..1999).forEach {
                conteo = 0
                et = result?.get(it).toString()
                (1..et.toInt()).forEach {
                    if (et.toInt() % it == 0) {
                        conteo++
                    }
                }
                if (conteo <= 2 && et.toInt()>1) {
                    np=np+et+", "
                }
            }
            guardarTXT(np)
        }
        fun guardarTXT(numerosPrimos:String){
            val guardarArchivo = OutputStreamWriter(con.openFileOutput("primos.txt", Activity.MODE_PRIVATE))
            guardarArchivo.write(numerosPrimos)
            guardarArchivo.flush()
            guardarArchivo.close()
            Toast.makeText(con,"Números primos guardados!",Toast.LENGTH_SHORT).show()
        }
    }

}
