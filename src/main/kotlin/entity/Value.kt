package entity

import java.util.*


/**
 * Enum to distinguish between the 8 possible values in a french-suited card game:
 * 7-10, Jack, Queen, King, and Ace.
 * The values are ordered according to their most common ordering:
 * 7<... < 10 < Jack < Queen < King < Ace
 */
enum class Value {
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE,
    ;

    /**
     * provide a single character to represent this value.
     * Returns one of: 7/8/9/10/J/Q/K/A
     */
    override fun toString():String {
        return when (this) {
            SEVEN -> return "7"
            EIGHT -> return "8"
            NINE -> return "9"
            TEN -> return "10"
            JACK -> return "J"
            QUEEN -> return "Q"
            KING -> return "K"
            ACE -> return "A"
        }
    }
    companion object {

        /**
         * A set of values for a reduced set of 4x8=32 cards (starting with the 7)
         */
        fun shortDeck(): Set<Value> {
            return EnumSet.of(ACE, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING)
        }

    }
}

