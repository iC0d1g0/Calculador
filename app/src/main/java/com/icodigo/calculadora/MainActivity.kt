package com.icodigo.calculadora

import android.net.wifi.p2p.WifiP2pManager.ActionListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity(){
    private lateinit var uno_: Button
    private lateinit var vista:TextView
    private var num1:Double= 0.0
    private var num2:Double=0.0
    private var operation:String=""
    private  var tienepunto=false
    private   var vis="0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vista=findViewById(R.id.textView)


    }
    fun numeros(view: View){
        try {
            vista.text.toString().toDouble()
        }catch (e:Exception){
            vista.text= "0"
        }
        var numero_=if(vista.text.toString() != "0" && !vista.text.toString().equals("Infinity") ) vista.text.toString() else ""
        if(numero_.length<15) {
            when (view.id) {
                R.id.punto->{
                    if(!numero_.equals("")){

                        if(!tienepunto){
                            vista.text=numero_+"."
                            tienepunto=true
                        }

                    }else{
                        vista.text="0."
                    }


                }

                R.id.cero -> vista.text = numero_ + "0"
                R.id.uno -> vista.text = numero_ + "1"
                R.id.dos -> vista.text = numero_ + "2"
                R.id.tres -> vista.text = numero_ + "3"
                R.id.cuatro -> vista.text = numero_ + "4"
                R.id.cinco -> vista.text = numero_ + "5"
                R.id.seis -> vista.text = numero_ + "6"
                R.id.siete -> vista.text = numero_ + "7"
                R.id.ocho -> vista.text = numero_ + "8"
                R.id.nueve -> vista.text = numero_ + "9"
                R.id.punto -> vista.text = numero_ + "."

            }
            if(numero_.length>10){
                vista.textSize=30.0f
            }
        }

    }
    fun operar(view: View){
          tienepunto=false
          try{
              vista.text.toString().toDouble()
              vis = "0"
              if (vista.text.toString().equals("Infinity"))
                  vista.text = "0"
          }
          catch(e :Exception) {
              vista.text = "0"
              vis = "0"
          }

        when(view.id){

            R.id.mas_menos ->{
                var vista1=vista.text.toString()
                var res=0-vista1.toDouble()
                vista.text=res.toString()
            }
            R.id.limpiar-> vista.text ="0"
            R.id.suma ->
            {
                num1 = (vista.text.toString()).toDouble()
                operation="+"
                vista.text="0"
            }
            R.id.restar ->
            {
                num1 = (vista.text.toString()).toDouble()
                operation="-"
                vista.text="0"
            }
            R.id.divicion ->
            {
                num1 = (vista.text.toString()).toDouble()
                operation="/"
                vista.text="0"
            }
            R.id.multiplicar ->
            {
                num1 = (vista.text.toString()).toDouble()
                operation="x"
                vista.text="0"
            }
            R.id.resultado-> {



                num2=(vista.text.toString()).toDouble()
                if(num1 != 0.0){
                    var ope=Operacion(num1, num2)

                    num1=0.0
                    num2=0.0
                    when(operation){
                        "+" -> {
                            vis=ope.sumar().toString()
                            tienepunto=false
                        }
                        "-" -> {
                            vis=ope.restar().toString()
                            tienepunto=false
                        }
                        "x" -> {
                            vis=ope.multiplicar().toString()
                            tienepunto=false
                        }
                        "/" -> {
                            vis=ope.dividir().toString()
                            tienepunto=false
                        }

                    }
                    if(vis.length>10){
                        vista.textSize=30.0f
                        if(vis.length<15) {
                            vista.text=vis
                        }else{

                            vista.textSize=35.0f
                            vista.text=vis.substring(0,11)+"+${vis.substring(13,).length}E"
                        }
                    }else{
                        vista.text=vis
                    }


                 }

            }
        }

    }
}






class Operacion(a: Double, b: Double){
     private val a:Double =a
    private val b: Double=b


    fun multiplicar(): Double =a*b

    fun sumar(): Double= a+b

    fun restar(): Double=a-b

    fun dividir(): Double=a/b


}