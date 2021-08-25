package service

import entity.Card

class PlayerService(private val root:SchwimmenService) {
    fun knock()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        game.players[active].knocked= true
        //GameService.nextTurn()
    }
    fun pass()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        game.players[active].passed= true
        //GameService.nextTurn()
    }
    fun swapOneCard(handCard:Card,tableCard: Card)
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        val playArea=game.playArea
        val hand = game.players[active].hand
        val table= playArea.cards
        //Hier wird die Handkarte zwischengespeichert
        val CardHolder = handCard
        //Handkarte wird durch Tischkarte ersetzt
        hand.remove(CardHolder)
        hand.add(tableCard)
        //Handkarte ersetzt Karte auf dem Tisch
        table.remove(tableCard)
        table.add(CardHolder)
        //Gebe an den nächsten Spieler weiter
        //GameService.nextTurn()
    }
    fun swapThreeCards()
    {
        val game=root.currentGame
        checkNotNull(game)
        val active=game.activePlayer
        val playArea=game.playArea
        val hand = game.players[active].hand
        val table= playArea.cards
        val bufferList = mutableListOf<Card>()

        //Tischkarten zwischenspeichern
        for(card in table )
        {
          bufferList.add(card)
        }
        // Handkarten auf den Tisch legen
        table.clear()
        for(card in hand )
        {
            table.add(card)
        }
        hand.clear()
        //Tischkarten auf die Hand nehmen
        for(card in bufferList )
        {
            hand.add(card)
        }

        //Gebe an den nächsten Spieler weiter
        //GameService.nextTurn()
    }

}