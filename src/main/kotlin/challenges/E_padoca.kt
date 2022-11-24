package challenges

private const val PAES = 1
private const val SALGADOS = 2
private const val DOCES = 3
private const val SUCOS = 4
private const val CAFES = 5

fun main() {

    val valorPaoFrances = 0.60
    val valorPaoDeLeite = 0.40
    val valorPaoDeMilho = 0.50

    val valorCoxinha = 2.00
    val valorPastel = 2.50
    val valorEmpada = 1.50

    val valorBrigadeiro = 1.00
    val valorSonho = 2.00
    val valorDoceDeLeite = 1.50

    val valorGoiaba = 3.50
    val valorLaranja = 4.00
    val valorUva = 3.00

    val valorExpresso = 3.50
    val valorCapuccino = 4.00
    val valorLatte = 4.50

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

    do {
        println(menu)
        val categoria = readln().toInt()

        var i = 0
        when(categoria){
            PAES -> { println(menuDePaes)

                do {
                    //Selecão de qual do tipo de pão
                    println("Qual pão você deseja?")
                    val selecaoDoTipoDoPao = readln().toInt()

                    //Adiciona o selecionado na comanda
                    when(selecaoDoTipoDoPao){

                        1 -> {

                            println("Quantidade: ")
                            val quantidadeDoTipoDoPao = readln().toInt()
                            val precoTotalPaoFrances = valorPaoFrances * quantidadeDoTipoDoPao
                            valorTotal = (valorTotal + precoTotalPaoFrances)

                            comanda.add(i, "Pão Francês")
                        }

                        2 -> {

                            println("Quantidade: ")
                            val quantidadeDoTipoDoPao = readln().toInt()

                            comanda.add(i,"Pão de leite")
                        }

                        3 -> {

                            println("Quantidade: ")
                            val quantidadeDoTipoDoPao = readln().toInt()

                            comanda.add(i,"Pão de milho")
                        }
                    }
                    println(menuDePaes)

                } while (selecaoDoTipoDoPao != 0)

            }
            SALGADOS -> { println(menuDeSalgados)
                comanda.add(i,readln())
            }
            DOCES -> { println(menuDeDoces)
                comanda.add(i,readln())
                }
            SUCOS -> { println(menuDeSucos)
                comanda.add(i,readln())
                }
            CAFES -> { println(menuDeCafes)
                comanda.add(i,readln())
                }
        }
        i+=1
    }while (categoria != 0)



}
