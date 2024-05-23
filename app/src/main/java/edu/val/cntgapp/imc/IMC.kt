package edu.`val`.cntgapp.imc

class IMC (val peso:Float, val altura:Float) {

    fun calcularImc ():Float
    {
        var imcNumerico:Float = 0f

            imcNumerico = this.peso / (this.altura*this.altura)

        return imcNumerico
    }


}