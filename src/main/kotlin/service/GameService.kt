package service

import entity.*

/**
 * Service layer Game that describes different action in and before the Game
 * with help o these functions can the game for example started Ended...
 */
class GameService(private val root: SchwimmenService) : AbstractRefreshingService() {
    /**
     * receives a List of players from the Gui and each player, creates the deck and places 3 Cards
     * on the Table and each player gets 3 Cards
     *
     * @param players is given from Gui
     *
     * @throws IllegalArgumentException if no player has been given
     */
    fun startGame(players: MutableList<Player>) {
        val myDeck = root.playAreaService.createDeck()
        val cardsOnTable = myDeck.draw(3).toMutableList()
        val playerArea = PlayArea(myDeck, cardsOnTable)
        playerSetup(players, myDeck)
        val game = Schwimmen(players, playerArea)
        game.activePlayer = 0
        root.currentGame = game
        onAllRefreshables { refreshAfterGameStart() }
    }

    /**
     * the function gives 3 cards for every layer in the List
     * @param players is given as Input and consists of all player in the Game
     * @param myDeck is the list of Cards that we have
     * @throws IllegalArgumentException if one the parameters null.
     */
    private fun playerSetup(players: MutableList<Player>, myDeck: Deck) {
        for (player in players) {
            player.hand.add(myDeck.draw(1)[0])
            player.hand.add(myDeck.draw(1)[0])
            player.hand.add(myDeck.draw(1)[0])
        }

    }

    /**
     * This function is responsible for handing the turn to the next player, but we
     * must check the cases oif all players passed/or one player Knocked and if all players had their turn.
     */
    fun nextTurn() {
        val game = root.currentGame
        checkNotNull(game)
        val num = game.players.size
        val active = game.activePlayer
        //if the next player knocked the round is done(% to insure the circle)
        if (game.players[(active + 1) % num].knocked) {
            exitGame()
        }
        if (game.players[(active + 1) % num].passed) {
            if (checkIfAllPlayersPassed()) {
                if (game.playArea.deck.listOfCards.size < 3) {
                    exitGame()
                } else {
                    root.playAreaService.renewMiddleCards()
                    resetPass(game.players)
                    onAllRefreshables { refreshAfterAllPassed() }
                }
            }
        }
        else if(!game.players[(active) % num].passed)
        {
            resetPass(game.players)
        }
        onAllRefreshables { refreshAfterTurnChanged() }
        game.activePlayer=(active+1) % num
    }
    private fun resetPass(players: MutableList<Player>){
        for (player in players) {
                player.passed=false
        }
    }
    /**
     * the function checks if all Player has passed and return true of false accordingly
     */
    private fun checkIfAllPlayersPassed(): Boolean {
        val game = root.currentGame
        checkNotNull(game)
        for (player in game.players) {
            if (!player.passed) {
                return false
            }
        }
        return true
    }

    /**
     * exit game is responsible for ending the game and giving wining player back.
     */
    fun exitGame(){
        root.currentGame!!.gameState=GameState.GAME_ENDED
        onAllRefreshables { refreshAfterGameFinished() }
    }

}