package challenges

private const val PAES = 1
private const val SALGADOS = 2
private const val DOCES = 3
private const val SUCOS = 4
private const val CAFES = 5

const val produtoPaoFrances = "Pão frânces"
const val produtoPaoDeLeite = "Pão de leite"
const val produtoPaoDeMilho = "Pão de milho"

const val produtoCoxinha = "Coxinha"
const val produtoPastel = "Pastel"
const val produtoEmpada = "Empada"

const val produtoBrigadeiro = "Brigadeiro"
const val produtoSonho = "Sonho"
const val produtoDoceDeLeite = "Doce de leite"

const val produtoGoiaba = "Suco de goiaba"
const val produtoLaranja = "Suco de laranja"
const val produtoUva = "Suco de uva"

const val produtoExpresso = "Café expresso"
const val produtoCapuccino = "Capuccino"
const val produtoLatte = "Latte"

const val valorPaoFrances = 0.60
const val valorPaoDeLeite = 0.40
const val valorPaoDeMilho = 0.50

const val valorCoxinha = 2.00
const val valorPastel = 2.50
const val valorEmpada = 1.50

const val valorBrigadeiro = 1.00
const val valorSonho = 2.00
const val valorDoceDeLeite = 1.50

const val valorGoiaba = 3.50
const val valorLaranja = 4.00
const val valorUva = 3.00

const val valorExpresso = 3.50
const val valorCapuccino = 4.00
const val valorLatte = 4.50

val paes: List<Pair<String,Double>> = listOf(
    Pair(produtoPaoFrances, valorPaoFrances), //index 0
    Pair(produtoPaoDeLeite, valorPaoDeLeite), //index 1
    Pair(produtoPaoDeMilho, valorPaoDeMilho), //index 2
)

val comanda : MutableList<String> = mutableListOf()
var valorTotal = 0.0

//Menu inicial padaria E-padoca
val menu = """
        Menu padaria E-padoca:
        1..................Pães
        2..............Salgados
        3.................Doces
        4.................Sucos
        5.................Cafés
        0......Finalizar compra
        """.trimIndent()

//Menus de cada categoria
val menuDePaes = """
            Pão Francês............R$ $valorPaoFrances
            Pão de leite...........R$ $valorPaoDeLeite
            Pão de milho...........R$ $valorPaoDeMilho
            0.......................Voltar
            """.trimIndent()

val menuDeSalgados = """
            Coxinha ..........R$ $valorCoxinha
            Pastel............R$ $valorPastel
            Empada............R$ $valorEmpada
            0..................Voltar
            """.trimIndent()

val menuDeDoces = """
            Brigadeiro...........R$ $valorBrigadeiro
            Sonho................R$ $valorSonho
            Doce de Leite........R$ $valorDoceDeLeite
            0.....................Voltar
            """.trimIndent()

val menuDeSucos = """
            Goiaba............R$ $valorGoiaba
            Laranja...........R$ $valorLaranja
            Uva...............R$ $valorUva
            0..................Voltar
            """.trimIndent()

val menuDeCafes = """
            "Expresso............R$ $valorExpresso
            "Capuccino...........R$ $valorCapuccino
            "Latte...............R$ $valorLatte
            "0....................Voltar
            """.trimIndent()
fun main() {

    ePadoca()

    if (comanda.isEmpty()){
        do {
            println("Deseja mesmo cancelar a compra? (S/N)")
            val cancelarCompra = readln().uppercase()
            if(cancelarCompra == "N"){
                ePadoca()
            }
        }while(cancelarCompra != "S" && cancelarCompra != "N")
    } else{
        comanda.forEach{linhaItem ->
            println(linhaItem)
        }
        println("Valor total: $valorTotal")
    }
}

fun ePadoca(){
    do {
        println(menu)
        val categoria = readln().toInt()

        when(categoria){
            PAES -> {
                selecionaProduto(menuSelecionado = menuDePaes, produtos = paes)
            }
            SALGADOS -> { println(menuDeSalgados)
//                comanda.add(i,readln())
            }
            DOCES -> { println(menuDeDoces)
//                comanda.add(i,readln())
            }
            SUCOS -> { println(menuDeSucos)
//                comanda.add(i,readln())
            }
            CAFES -> { println(menuDeCafes)
//                comanda.add(i,readln())
            }
        }
    }while (categoria != 0)
}
fun selecionaQuantidadeDoProdutoECalculoDoValor(
    produto: Pair<String,Double>
){
    println("Quantidade: ")
    val quantidadeDoTipoDoPao = readln().toInt()
    val precoTotalItem = produto.second * quantidadeDoTipoDoPao
    val linhaItem = linhaComanda( produto = produto.first,  qtsProduto = quantidadeDoTipoDoPao, valorProduto = produto.second, precoTotalDoProduto = precoTotalItem)

    comanda.add(linhaItem) 
    valorTotal += precoTotalItem
}

fun linhaComanda(
    produto:String,
    qtsProduto:Int,
    valorProduto:Double,
    precoTotalDoProduto: Double
): String = "${comanda.size.inc()}......$produto......$qtsProduto......R$$valorProduto......R$$precoTotalDoProduto"

fun selecionaProduto(
    menuSelecionado: String,
    produtos: List<Pair<String, Double>>
){
    println(menuSelecionado)

    do {
        val selecaoDoTipoDoProduto = readln().toInt()

            for ((i,produto) in produtos.withIndex()){
            if (i.inc() == selecaoDoTipoDoProduto){
                selecionaQuantidadeDoProdutoECalculoDoValor(produto)
            break
            }
        }

        println(menuSelecionado)

    } while (selecaoDoTipoDoProduto != 0)
}