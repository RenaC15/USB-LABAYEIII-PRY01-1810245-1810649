import java.util.Arrays
import java.io.File
import libGrafoR.*


fun main(args: Array<String>) {
// Halla la ruta menos costosa del autobús dados los datos
// en un archivo externo que se espera recibir
    val archivo = File("grafos/rutaOptUSB.txt")
    val lineas = archivo.readLines()
    // La primera línea es el número de vértices
    val vertices = lineas[0].toInt()
    var grafond = GrafoNoDirigidoCosto (vertices+1)
    // la segunda línea es el número de aristas
    //val aristas = lineas[1].toInt()
    // Las siguientes líneas son las aristas

    // map con los valores donde la clave es el string con
    // el nombre de la parada y su valor asociado es el
    // elemento con el que se podra trabajar con el grafo no
    // dirigido, dado que este solo recibe vertices de tipo Int
    var paradas = mutableMapOf< String, Int>(
        "USB"       to 0
    )
    var count = 1
    var index = vertices
    for ( i in 2 until 2 + vertices){
        var (parada, km) = lineas[i].split(" ")
        paradas[parada] = count
        count = count + 1
    }

    for (i in 2 + vertices until lineas.size) {
        var (bus, inicio, fin, costoStr) = lineas[i].split(" ")
        var u = paradas[inicio] ?: throw IllegalArgumentException("Parada no encontrada: $inicio")
        // excepcón que indica que el punto de partida debe ser ser la usb
        if ( i == 2 && u != 0){ throw Exception("El punto de partida debe ser la USB") }
        var v = paradas[fin] ?: throw IllegalArgumentException("Parada no encontrada: $fin")
        try{
            var costo = costoStr.toDouble()
            grafond.agregarAristaCosto( AristaCosto(u, v, costo))
        } catch ( e: Exception ){
            throw Exception("El costo no tiene el formato adecuado")
        }
    }
    
    var lados = grafond.iterator()
    var lista_aristas = mutableListOf<AristaCosto>()
    for ( arista in lados ){
        lista_aristas.add(arista)
    }
    // TENER CUIDADO CON EL NUMERO DE VERTICES
    var mst = kruskal(lista_aristas, grafond.obtenerNumeroDeVertices())
    for (edge in mst) {
        var parada_i = String()
        var parada_f = String()

        for ( (clave,valor) in paradas){
            if ( edge.x == valor ){
                parada_i = clave
            }
            else if ( edge.y == valor){
                parada_f = clave
            }
        } 
        println("$parada_i --(${edge.costo} Lt)-- $parada_f")
    }
}

