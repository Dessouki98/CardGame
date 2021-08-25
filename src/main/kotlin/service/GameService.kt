package service

import entity.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class GameService(private val root:SchwimmenService) {

    fun startGame(players:MutableList<Player>)
    {

    }
    fun playerSetup(players:MutableList<Player>)
    {
        val myDeck=root.playAreaService.createDeck()
        for(player in players)
        {
            player.hand.add(myDeck.draw(1).get(0))
            player.hand.add(myDeck.draw(1).get(0))
            player.hand.add(myDeck.draw(1).get(0))
        }
        val CardOnTable1 = myDeck.draw(1).get(0)
        val CardOnTable2 = myDeck.draw(1).get(0)
        val CardOnTable3 = myDeck.draw(1).get(0)
        val CardsOnTable= mutableListOf<Card>(CardOnTable1,CardOnTable2,CardOnTable3)
        val playerArea=PlayArea(myDeck,CardsOnTable)
        val game=Schwimmen(players,playerArea)
        game.activePlayer=1
        root.currentGame=game
    }


}