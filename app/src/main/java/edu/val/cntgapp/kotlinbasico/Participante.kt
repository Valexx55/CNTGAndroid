package edu.`val`.cntgapp.kotlinbasico

class Participante (var nombre : String, var urlGitHub: String, var urlLinkedin:String){
    override fun toString(): String {
        return "Nombre = ${this.nombre} "
    }
}