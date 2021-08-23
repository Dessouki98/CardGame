package entity
enum class Suit {
    CLUBS,
    SPADES,
    HEARTS,
    DIAMONDS,
    ;

    /**
     * provide a single character to represent this suit.
     * Returns one of: ♣/♠/♥/♦
     */
    override fun toString() = when(this) {
        CLUBS -> "♣"
        SPADES -> "♠"
        HEARTS -> "♥"
        DIAMONDS -> "♦"
    }

}