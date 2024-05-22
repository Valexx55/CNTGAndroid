package edu.`val`.cntgapp.imc

//SI EL IMC ES MENOR QUE 16 DESNUTRIDA
//SI EL IMC ES MAYOR O IGUAL  16 Y MENOR QUE 18 DELGADO
//SI EL IMC ES MAYOR O IGUAL  18 Y MENOR QUE 25 IDEAL
//SI EL IMC ES MAYOR O IGUAL  25 Y MENOR QUE 31 SOBREPESO
//SI EL IMC ES MAYOR QUE 31 OBESO


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.`val`.cntgapp.R

class ResultadoImcActivity : AppCompatActivity() {

    lateinit var textoResultado: TextView // lateinit me permite declarar sin inicializar la variable
    lateinit var imagenResultado: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)//cargar el XML del layout
        this.textoResultado = findViewById<TextView>(R.id.textNombreImc) //this es la pantalla
        this.imagenResultado = findViewById<ImageView>(R.id.imageView) //this es la pantalla
        //Mostrar el resultado
        //intent es el precursor, el Intent que lanzó esta actividad
        val resultadoImc = intent.getFloatExtra("IMC_RESULTADO", 0f)

        when
        {
            resultadoImc < 16 -> {
                this.mostarResultado(R.drawable.imc_desnutrido, "DESNUTRIDO")
            }
            resultadoImc >= 16 && resultadoImc < 18  -> {
                this.mostarResultado(R.drawable.imc_delgado, "DELGADO")
            }
            resultadoImc >= 18 && resultadoImc < 25 -> {
                this.mostarResultado(R.drawable.imc_ideal, "IDEAL")

            }
            resultadoImc >= 25 && resultadoImc < 31 -> {
                this.mostarResultado(R.drawable.imc_sobrepeso, "SOBREPESO")
            }
            resultadoImc > 31 -> {
                this.mostarResultado(R.drawable.imc_obeso, "OBESO")

            }
        }


    }

    fun mostarResultado (imagen:Int, texto:String):Unit
    {
        this.imagenResultado.setImageResource(imagen)
        this.textoResultado.text = texto
    }
}