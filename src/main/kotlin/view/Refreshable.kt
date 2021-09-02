package view

import service.AbstractRefreshingService


/**
 * This interface provides a mechanism for the service layer classes to communicate
 * (usually to the view classes) that certain changes have been made to the entity
 * layer, so that the user interface can be updated accordingly.
 *
 * Default (empty) implementations are provided for all methods, so that implementing
 * UI classes only need to react to events relevant to them.
 *
 * @see AbstractRefreshingService
 *
 */
interface Refreshable {

    /**
     * perform refreshes that are necessary after a new game started.
     */
    fun refreshAfterGameStart() {}

    /**
     * perform refreshes that are necessary after a game has ended.
     */
    fun refreshAfterGameFinished() {}

    /**
     * perform refreshes that are necessary after a card has been drawn.
     */
    fun refreshAfterAllPassed() {}

    /**
     * perform refreshes that are necessary after a Care has been swapped.
     */
    fun refreshAfterCardsSwap() {}

    /**
     * perform refreshes that are necessary after player has knocked.
     */
    fun refreshAfterKncoked() {}

    /**
     *  perform refreshes that are necessary after a player has passed.
     */
    fun refreshAfterPassed() {}

    /**
     * perform refreshes that are necessary after turn Changes
     */
    fun refreshAfterTurnChanged() {}
}