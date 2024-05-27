package edu.`val`.cntgapp.kotlinbasico


fun main() {
    print("Hola mundo Kt")
    var profe:Participante = Participante("Vale", "https://github.com/Valexx55", "https://www.linkedin.com/in/valerianomoreno/" )
    var carlos = Participante("Carlos", "https://github.com/alfmanda", "https://es.linkedin.com/in/carlos-otero-bouzas")
    var pablo:Participante = Participante("Pablo", "https://github.com/PabloSanchez87", "www.linkedin.com/in/pablosancheztorres")
    var jose:Participante = Participante("Jose", "https://github.com/joseEstevezRey","" )
    var daniel:Participante = Participante("Daniel", "", "")
    var alejandro:Participante = Participante("Alejandro", "", "")
    var joaquin:Participante = Participante("Joaquin", "", "")
    var xoel:Participante = Participante("Xoel", "https://github.com/xoelin25", "https://www.linkedin.com/in/xoel-%C3%A1lvarez-alonso-3b052a138/")
    var luisPrada:Participante = Participante(nombre= "Luis Prada", "https://github.com/gitluisprada", "https://www.linkedin.com/in")
    var pedro:Participante = Participante("Pedro Rodríguez Ansede", "", "https://www.linkedin.com/in/pedroroan/")
    var marta:Participante = Participante("Marta de Guzmán", "https://github.com/MdGdC", "" )
    var marcos: Participante = Participante("Marcos", "https://github.com/MarksCore", "https://www.linkedin.com/in/marcos-c-p/")
    var alfonsoalvar = Participante("Alfonso Alvar", "https://github.com/alfonsoalvar", "https://www.linkedin.com/in/alfonsoalvar")
    var enmanuel:Participante = Participante("Enmanuel", "https://github.com/EnmaLlDev", "https://www.linkedin.com/in/enmanuel-ll-406a93240/" )
    var alejandror:Participante = Participante("Alejandro", "https://github.com/AlejoRub","https://www.linkedin.com/in/alejandro-rubial-quelle-24bb87164/")

    var listaParticipantes = listOf(profe, carlos, pablo, jose, daniel, alejandro, joaquin, xoel, luisPrada, pedro, marta, marcos, alfonsoalvar, enmanuel, alejandror)

    listaParticipantes.forEach({p-> println(p.nombre) })
    listaParticipantes.forEach{p-> println(p.nombre) } //cuando el último parámetro es una función, puedo ponerle llaves directamente
    listaParticipantes.forEach{p-> println(p.toString()) } //cuando el último parámetro es una función, puedo ponerle llaves directamente

    var usuario = Usuario ("Ana", 23)
    println("USUARIO " +  usuario.toString())
}