package entity

import kotlin.random.Random

class Deck(private val random: Random = Random) {
    private val cards: ArrayDeque<Card> = ArrayDeque(32)
    val size: Int get() = cards.size
    val empty: Boolean get() = cards.isEmpty()
    fun shuffle() {
        cards.shuffle(random)
    }
    fun draw(amount: Int = 1): List<Card> {
        require (amount in 1..cards.size) { "can't draw $amount cards from $cards" }
        return List(amount) { cards.removeFirst() }
    }
}