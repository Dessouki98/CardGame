package entity

import kotlin.random.Random

/**
 * Data structure that holds [Deck] objects and provides stack-like
 * access to them (with e.g[draw])
 *
 * @param [random] can be provided to ensure deterministic behavior of [shuffle]
 */
class Deck(private val random: Random = Random) {
    public val cards: ArrayDeque<Card> = ArrayDeque(32)

    /**
     * Shuffles the cards in this stack
     */
    fun shuffle() {
        cards.shuffle(random)
    }

    /**
     * Draws [amount] cards from this stack.
     *
     * @param amount the number of cards to draw; defaults to 1 if omitted.
     *
     * @throws IllegalArgumentException if not enough cards on stack to draw the desired amount.
     */
    fun draw(amount: Int = 1): List<Card> {
        require(amount in 1..cards.size) { "can't draw $amount cards from $cards" }
        return List(amount) { cards.removeFirst() }
    }
}