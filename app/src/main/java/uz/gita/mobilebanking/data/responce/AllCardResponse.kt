package uz.gita.mobilebanking.data.responce


data class AllCardResponse(
    val data: CardList
)

data class CardList(
    val data: List<CardData>
)

data class CardData(
    val pan: String,
    val exp: String,
    val owner: String,
    val cardName: String,
    val balance: Long,
    val status: Int
)