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
const val EXIBIR_TODOS_OS_ITENS = 3
const val EXIBIR_ITENS_EM_ESTOQUE = 4
const val FINALIZAR_O_PROGRAMA = 0

val deposito = mutableListOf <Item>()

var id = 1

fun main() {
    println("$bemVindo\n$menuPrincipal")
    eDeposito()
}

fun eDeposito(){
    do {

        val selecionarOpcao = readln().toInt()

        when (selecionarOpcao) {
            ADICIONAR_ITEM -> adicionarItem()
            EDITAR_ITEM -> editarItem()
            EXIBIR_TODOS_OS_ITENS -> exibirListaCompleta()
            EXIBIR_ITENS_EM_ESTOQUE -> exibirListaEmEstoque()
            FINALIZAR_O_PROGRAMA -> println("Finalizando programa...")
            else -> println("Entrada inválida, entre com um dos valores abaixo:\n$menuPrincipal")
        }
    } while (selecionarOpcao != 0)
}

fun adicionarItem() {
    val qtsP: Int

    println("Qual peça você deseja adicionar?")
    val nomeP = readln()

    println("Qual a quantidade?")
    try {
         qtsP = validacaoDaQuantidade()
    } catch (e: LimitExceededException){
        mensagemErroAdiconar()
        voltarMenuErro()
        return
    }

    val peca = Item(id,nomeP,qtsP)
    deposito.add(peca)
    id+=1

    mensagemSucessoAdiconar()
    voltarMenuSucesso()
}
fun editarItem(depositoTotal: List <Item> = deposito){

    apenasMostrarLista()

    println("Qual item você deseja editar?")
    val idParaEditar = readln().toInt()

    for (peca in depositoTotal) {

        if (idParaEditar == peca.id) {
            println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.quantitadeP}")

            println("E oque você deseja editar? (nome/quantidade)")
            when (readln().lowercase()) {
                "nome" -> {
                    println("Qual o novo nome? ")
                    peca.nome = readln()
                    println("Nome alterado com sucesso")
                    mensagemSucessoEditar()
                    voltarMenuSucessoEdicao()
                }

                "quantidade" -> {
                    println("Qual a nova quantidade? ")
                    try {
                        peca.quantitadeP = validacaoDaQuantidade()
                    }   catch (e:LimitExceededException){
                        mensagemErroEditar()
                        voltarMenuErroEdicao()
                        return
                    }
                    mensagemSucessoEditar()
                    voltarMenuSucessoEdicao()
                }
            }
        }
    }
}
fun exibirListaCompleta(pecasExibicao: List <Item> = deposito) {
    println("ID | Peça | Quantidade")
    for (peca in pecasExibicao) {
        println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.quantitadeP}")
    }
    voltarMenuListaC()
}
fun exibirListaEmEstoque(pecasExibicao: List <Item> = deposito) {
    println("ID | Peça | Quantidade")
    for (peca in pecasExibicao) {
        if (peca.quantitadeP > 0)
        println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.quantitadeP}")
    }
    voltarMenuListaE()
}
fun validacaoDaQuantidade():Int{
    val quantidade = try {
        readln().toInt()
    } catch (e: NumberFormatException)
    { null }
    if (quantidade == null || quantidade > 999 || quantidade < 0){
        throw LimitExceededException(null)
    }
    return quantidade
}
fun apenasMostrarLista(pecasExibicao: List <Item> = deposito) {
    println("ID | Peça | Quantidade")
    for (peca in pecasExibicao) {
        println("#%04d".format(peca.id) + " | ${peca.nome} | ${peca.quantitadeP}")
    }
}
//Mensagens de retorno
fun mensagemErroAdiconar(){
    println("Item não adicionado - Quantidade inválida!! \n" +
            "Voltar ao menu principal? (S/N)")
}
fun mensagemErroEditar(){
    println("Item não editado - Quantidade inválida!! \n" +
            "Voltar ao menu principal? (S/N)")
}
fun mensagemSucessoAdiconar(){
    println("Item adicionado!! \n" + "Voltar ao menu principal? (S/N)")
}
fun mensagemSucessoEditar(){
    println("Item editado!! \n" + "Voltar ao menu principal? (S/N)")
}
fun voltarMenuListaC(){
    println("Voltar ao menu principal? (S/N)")
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> exibirListaCompleta()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuListaC()
        }
    }
}
fun voltarMenuListaE(){
    println("Voltar ao menu principal? (S/N)")
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> exibirListaEmEstoque()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuListaE()
        }
    }
}
fun voltarMenuErro(){
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> adicionarItem()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuErro()
        }
    }
}
fun voltarMenuErroEdicao(){
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> editarItem()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuErroEdicao()
        }
    }
}
fun voltarMenuSucesso(){
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> adicionarItem()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuErro()
        }
    }
}
fun voltarMenuSucessoEdicao(){
    when(readln().uppercase()){
        "S" -> println(menuPrincipal)
        "N" -> editarItem()
        else -> {
            println("Entrada inválida!! \nVoltar ao menu principal? (S/N)")
            voltarMenuErro()
        }
    }}

class Item(val id: Int, var nome:String, var quantitadeP: Int){}

