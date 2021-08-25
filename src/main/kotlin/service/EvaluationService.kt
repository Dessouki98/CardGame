package service

import entity.Card
import entity.Player


class EvaluationService(private val root:SchwimmenService) {
fun calculateWinner():Map<Player,Double>
{
    val hashMap : HashMap<Player, Double> = HashMap<Player, Double> ()
    val game=root.currentGame
    checkNotNull(game)
    for(player:Player in game.players)
    {

        hashMap.set(player,calculateScore(player) as (Double) )
    }
    val resultMap = hashMap.entries.sortedBy { it.value }.associate { it.toPair() }
    return resultMap
}


    fun calculateScore(player: Player):Double?
    {
        val myCard:MutableList<Double> = mutableListOf<Double>(0.0,0.0,0.0,0.0,0.0)
        for(card:Card in player.hand)
        {
            when(card.cardsuit.toString())
            {
                "♣" -> myCard[0] = myCard[0] +card.getPoints()
                "♠" -> myCard[1] = myCard[1] +card.getPoints()
                "♥" -> myCard[2] = myCard[2] +card.getPoints()
                "♦" -> myCard[3] = myCard[3] +card.getPoints()
            }
        }
        val card1Su= player.hand[0].cardsuit.toString()
        val card1Va= player.hand[0].cardvalue.toString()
        val card2Su= player.hand[1].cardsuit.toString()
        val card2Va= player.hand[1].cardvalue.toString()
        val card3Su= player.hand[2].cardsuit.toString()
        val card3Va= player.hand[2].cardvalue.toString()
        if( (card1Su.equals(card2Su) && card2Su.equals(card3Su) ) ||(card1Va.equals(card2Va) && card2Va.equals(card3Va)) )
        {
            myCard[4] = 30.5
        }
        return myCard.maxOrNull()
    }



}
