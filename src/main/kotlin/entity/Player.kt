package entity

class Player(
    val name:String?=null,
    val place:Int?=null,
    var knocked:Boolean=false,
    var passed:Boolean=false,
    var hand: List<Card> = emptyList()

)