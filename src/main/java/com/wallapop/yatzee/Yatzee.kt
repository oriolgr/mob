package com.wallapop.yatzee


class Yatzee {
    fun tirada(dado1: Die, dado2: Die, dado3: Die, dado4: Die, dado5: Die, categoria: Category): Int {
        val listaDados = listOf(dado1, dado2, dado3, dado4, dado5)
        if (categoria.value == "pair") {
            val firstPair = listaDados
                    .sortedByDescending { it.value }
                    .groupBy { it.value }.values
                    .firstOrNull { it.size >= 2 }
            return if (firstPair == null) 0 else firstPair.first().value * 2
        }
        if (categoria.value == "three of a kind") {
            val firstTreeOfAKind = listaDados
                    .sortedByDescending { it.value }
                    .groupBy { it.value }.values
                    .firstOrNull { it.size >= 3 }
            return if (firstTreeOfAKind == null) 0 else firstTreeOfAKind.first().value * 3
        }
        if (categoria.value == "four of a kind") {
            return listaDados
                    .sortedByDescending { it.value }
                    .groupBy { it.value }.values
                    .first { it.size == 4 }
                    .sumBy { it.value }
        }
        return countByCategory(listaDados, categoria) * categoria.value.toInt()
    }

    private fun countByCategory(listaDados: List<Die>, categoria: Category) =
            listaDados.count { v -> v.value == categoria.value.toInt() }

}

inline class Die(val value: Int)
inline class Category(val value: String)
