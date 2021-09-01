package entity

import kotlin.random.Random

/**
 * Data structure that holds [Deck] objects and provides stack-like
 * access to them (with e.g[draw])
 *
 * @param [random] can be provided to ensure deterministic behavior of [shuffle]
 */
class Deck(val listOfCards: MutableList<Card>){
    /**
     * Draws [amount] cards from this stack.
     *
     * @param amount the number of cards to draw; defaults to 1 if omitted.
     *
     * @throws IllegalArgumentException if not enough cards on stack to draw the desired amount.
     */
    fun draw(amount: Int = 1): List<Card> {
        require(amount in 1..listOfCards.size) { "can't draw $amount cards from $listOfCards" }
        return List(amount) { listOfCards.removeFirst() }
    }
    /**
     * provides a view of the full stack contents without changing it. Use [draw]
     * for actually drawing cards from this stack.
     */
    fun peekAll(): List<Card> = listOfCards.toList()
}