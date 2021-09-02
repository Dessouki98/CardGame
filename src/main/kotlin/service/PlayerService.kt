package service

import entity.Card

/**
 * the Player Service gives access to all actions which can be performed from Player during the game
 */
class PlayerService(private val root:SchwimmenService): AbstractRefreshingService() {
    /**
     *sets the knock variable to true,Afterwards is the goes the turn to the next player using nextTurn.
     *
     * @throws IllegalStateException if no game has started yet
     */
    fun knock()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        game.players[active].knocked= true
        root.gameService.nextTurn()//root y3rf el services we al entities
        onAllRefreshables { refreshAfterKncoked() }
    }
    /**
     *sets the pass variable to true,Afterwards is the goes the turn to the next player using nextTurn.
     *
     * @throws IllegalStateException if no game has started yet
     */
    fun pass()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        game.players[active].passed= true
        root.gameService.nextTurn()
        onAllRefreshables { refreshAfterPassed() }

    }
    /**
     *swaps one Card of player hand with one on the table
     *
     * @param [handCard] the Card in hand which the active player wants to replace with the [tableCard]
     * @param [tableCard] the card on the table which the player wants to take and replace
     *
     * @throws IllegalStateException if no game has started yet
     * @throws IllegalStateException if one or both parameters null
     */
    fun swapOneCard(handCard:Card,tableCard: Card)
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        val playArea=game.playArea
        val hand = game.players[active].hand
        val table= playArea.cards
        //Hand cards are replaced by table's card
        hand.remove(handCard)
        hand.add(tableCard)
        //Hand card replaces card on the table
        table.remove(tableCard)
        table.add(handCard)
        //Pass it on to the next player
        root.gameService.nextTurn()
        onAllRefreshables { refreshAfterCardsSwap() }
    }
    /**
     *swaps all Card of player's hand with the ones on the table
     *
     * @throws IllegalStateException if no game has started yet
     * @throws IllegalStateException if active player has no Card or no cards are on table
     */
    fun swapThreeCards()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        val playArea=game.playArea
        val hand = game.players[active].hand
        val table= playArea.cards
        val bufferList = mutableListOf<Card>()

        //save card on table
        for(card in table )
        {
          bufferList.add(card)
        }
        // Put hand cards on the table
        table.clear()
        for(card in hand )
        {
            table.add(card)
        }
        hand.clear()
        //Take table's Card in hand
        for(card in bufferList )
        {
            hand.add(card)
        }
        root.gameService.nextTurn()
        //Pass it on to the next player
        onAllRefreshables { refreshAfterCardsSwap() }
    }
}