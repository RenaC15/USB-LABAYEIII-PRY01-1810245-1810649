import java.util.Arrays
import java.io.File
import libGrafoR.*

val paradas = mutableMapOf<String, Int>(
    "USB" to 0,
    "McDonald’s_Trinidad" to 1,
    "Chacaíto" to 2
)

fun leer_ruta(ruta: String): GrafoNoDirigidoCosto {
/* Función para leer un archivo de texto con entradas que pueden ser de tipo string,
   el cual retornara un grafo no dirigido ponderado con la informacion proporcionada
   en el archivo.txt
*/
    val archivo = File(ruta)
    val lineas = archivo.readLines()

    if (lineas.isEmpty()) throw IllegalArgumentException("El archivo está vacío")

    // La primera línea es el número de vértices
    val vertices = lineas[0].toInt()
    if ( vertices != 2 && vertices != 3){
        throw Exception("El número de paradas no coincide")
    }
    var grafond = GrafoNoDirigidoCosto(vertices)

    // la segunda línea es el número de aristas, pero ya que aparece un error
    // a la hora de ejecutar porque este no se usa, se ha colocado como un comentario:
    // val aristas = lineas[1].toInt()

    // Las siguientes líneas consisten en la creacion de las aristas ponderadas
    for (i in 2 until lineas.size) {
        var (inicio, fin, costoStr) = lineas[i].split(" ")
        var u = paradas[inicio] ?: throw IllegalArgumentException("Parada no encontrada: $inicio")
        var v = paradas[fin] ?: throw IllegalArgumentException("Parada no encontrada: $fin")
        try{
            var costo = costoStr.toDouble()
            grafond.agregarAristaCosto(AristaCosto(u, v, costo))
        } catch (e: Exception){
            throw Exception("El costo no tiene el formato adecuado")}
    }
    return grafond
}


fun main(args: Array<String>) {
/* Funcion que se encargara de calcular la ruta menos costosa del autobús */

    val grafond = leer_ruta("grafos/rutaOptUSB.txt")

    var aristas = grafond.iterator()

    var lista_aristas = mutableListOf<AristaCosto>()

    for (arista in aristas){
        lista_aristas.add(arista)
    }

    // TENER CUIDADO CON EL NUMERO DE VERTICES
    // TIENE QUE SER SIEMPRE 3

    var mst = kruskal(lista_aristas, 3)

    for (edge in mst) {
        var parada_i = String()
        var parada_f = String()

        for ((clave,valor) in paradas){
            if (edge.x == valor){
                parada_i = clave
            }
            else if (edge.y == valor){
                parada_f = clave
            }
        }
    println("$parada_i --(${edge.costo} Lt)-- $parada_f")
    }
}
