package ru.sonya.week3.model

class CatsRepository {

    private val count = 100
    private val catsTitles =
        listOf("Смешной кот", "Хихитбл кот", "Жесть смешной кот", "Котик хихи", "Забавный кот")
    private val catsSubtitles = listOf("хихихи", "хахахха", "АХАХ", "вахзпхв", "апхзвыхща")
    private val catsImages = listOf(
        "https://i.pinimg.com/564x/3c/e6/83/3ce6837f24627680bdd499bc8a2fb891.jpg",
        "https://i.pinimg.com/originals/af/ac/e8/aface8ae6a0c7f3d7c23f81e2ba16059.jpg",
        "https://i.pinimg.com/564x/e4/df/ca/e4dfca50c2dd6b4b1e59028676cc3cc6.jpg",
        "https://i.pinimg.com/564x/36/88/a8/3688a852aad9a868dc7aa422b5e90bee.jpg",
        "https://i.pinimg.com/564x/a2/5e/b2/a25eb22ecd9b58d8dc7326f6f93b36e4.jpg"
    )

    private fun catGenerator(
        count: Int,
        catsTitles: List<String>,
        catsSubtitles: List<String>,
        catsImages: List<String>
    ): MutableList<FunCat> {
        val generatedList = mutableListOf<FunCat>()
        for (i in 1..count) {
            generatedList.add(
                FunCat(
                    catsImages[(0..<catsImages.size).random()],
                    catsTitles[(0..<catsTitles.size).random()],
                    catsSubtitles[(0..<catsSubtitles.size).random()]
                )
            )
        }
        return generatedList
    }

    fun getCats() = catGenerator(count, catsTitles, catsSubtitles, catsImages)
}
