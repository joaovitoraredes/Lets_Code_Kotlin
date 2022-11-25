package challenges

val menuPrincipal = """
        ========= Bem Vindo ==========
        1...............ADICIONAR ITEM
        2..................EDITAR ITEM
        3......EXIBIR ITENS EM ESTOQUE
        4........EXIBIR TODOS OS ITENS
        0.............FECHAR O SISTEMA
    """.trimIndent()

const val ADICIONAR_ITEM = 1
const val EDITAR_ITEM = 2
const val EXIBIR_ITENS_EM_ESTOQUE = 3
const val EXIBIR_TODOS_OS_ITENS = 4
const val FECHAR_O_SISTEMA = 0

val deposito: MutableList<String> = mutableListOf()


fun main() {
    println(menuPrincipal)

    do {

        val selecionarOpcao = readln().toInt()

        when (selecionarOpcao) {
            1 -> adicionarItem()
//            2 -> editarItem()
        }

    } while (selecionarOpcao != 0)

    mostrarComanda()
}

fun linhaComanda(
    id: Int,
    peca: String,
    qts:Int
): String = "#$id | $peca | $qts"

fun adicionarItem() {
    println("Qual peça você deseja adicionar?")
    val pecaEntradaUsuario = readln()

    println("Qual a quantidade?")
    val qtsPecasEntradaUsuario = readln().toInt()

    val linhaItem = linhaComanda(id = deposito.size.inc(),  peca = pecaEntradaUsuario, qts = qtsPecasEntradaUsuario )
    deposito.add(linhaItem)
}

fun mostrarComanda(){
    deposito.forEach { linhaItem ->
        println(linhaItem)
    }
}












//val comandaFinalFormato = """
//        ID | PEÇA | QUANTIDADE
//        """.trimIndent()