package challenges

import javax.naming.LimitExceededException

val bemVindo = """
    ========= Bem Vindo ==========
    """.trimIndent()

val menuPrincipal = """
        1...............ADICIONAR ITEM
        2..................EDITAR ITEM
        3........EXIBIR TODOS OS ITENS
        4......EXIBIR ITENS EM ESTOQUE
        0.............FECHAR O SISTEMA
    """.trimIndent()

const val ADICIONAR_ITEM = 1
const val EDITAR_ITEM = 2
const val EXIBIR_ITENS_EM_ESTOQUE = 3
const val EXIBIR_TODOS_OS_ITENS = 4
const val FECHAR_O_SISTEMA = 0

val deposito = mutableListOf<Peca>()

var id = 1

fun main() {

    println("$bemVindo\n$menuPrincipal")

    do {

        val selecionarOpcao = readln().toInt()

        when (selecionarOpcao) {
            1 -> adicionarItem(selecionarOpcao)
            2 -> editarItem()
            3 -> exibirListaCompleta()
            4 -> exibirListaEmEstoque()
            else -> Unit
        }
    } while (selecionarOpcao != 0)

}


fun adicionarItem(continuar:Int) {
    val qtsP: Int

    println("Qual peça você deseja adicionar?")
    val nomeP = readln()

    println("Qual a quantidade?")
    try {
         qtsP = validacaoDaQuantidade()
    } catch (e: LimitExceededException){
        voltarMenuErro()
        return
    }

    val peca = Peca(id,nomeP,qtsP)
    deposito.add(peca)
    id+=1

    voltarMenuSucesso()
}

fun editarItem(depositoTotal: List<Peca> = deposito){

    exibirListaCompleta()

    println("Qual item você deseja editar?")
    val idParaEditar = readln().toInt()

    for (peca in depositoTotal) {
        if (idParaEditar == peca.id) {
            println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.qts}")

            println("E oque você deseja editar? (nome/quantidade)")
            when (readln().lowercase()) {
                "nome" -> {
                    println("Qual o novo nome? ")
                    peca.nome = readln()
                    println("Nome alterado com sucesso")
                    voltarMenuSucessoEdicao()
                }

                "quantidade" -> {
                    println("Qual a nova quantidade? ")
                    try {
                        peca.qts = validacaoDaQuantidade()
                    }   catch (e:LimitExceededException){
                        voltarMenuErroEdicao()
                        return
                    }
                    voltarMenuSucessoEdicao()
                }
            }
        }
    }
}

fun exibirListaCompleta(pecasExibicao: List<Peca> = deposito) {
    println("ID | Peça | Quantidade")
    for (peca in pecasExibicao) {
        println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.qts}")
    }
}

fun exibirListaEmEstoque(pecasExibicao: List<Peca> = deposito) {
    println("ID | Peça | Quantidade")
    for (peca in pecasExibicao) {
        if (peca.qts > 0)
        println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.qts}")
    }

    println("Voltar ao menu principal? (S/N)")
    if (readln().uppercase()=="S") println(menuPrincipal)

}

fun validacaoDaQuantidade():Int{
    val quantidade = readln().toInt()
    if (quantidade > 999 || quantidade < 0){
        throw LimitExceededException(null)
    }
    return quantidade
}

fun voltarMenuErro(){
    println("Item não adicionado - Quantidade inválida!! \n" +
            "Voltar ao menu principal? (S/N)")
    if (readln().uppercase()=="S") println(menuPrincipal)
}
fun voltarMenuErroEdicao(){
    println("Item não editado - Quantidade inválida!! \n" +
            "Voltar ao menu principal? (S/N)")
    if (readln().uppercase()=="S") println(menuPrincipal)
}

fun voltarMenuSucesso(){
    println("Item Adicionado!! \nVoltar ao menu principal? (S/N)")
    if (readln().uppercase()=="S") println(menuPrincipal)
}
fun voltarMenuSucessoEdicao(){
    println("Item editado!! \nVoltar ao menu principal? (S/N)")
    if (readln().uppercase()=="S") println(menuPrincipal)
}

class Peca(val id: Int, var nome:String, var qts: Int){}

