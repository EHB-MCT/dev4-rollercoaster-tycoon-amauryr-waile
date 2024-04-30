package be.ehb.rollecoaster.dto

data class CategorieRequest(
    val naam: String,
    val attractieId : Long,
    //val attracties: List<Long> // Liste des IDs des attractions appartenant à cette catégorie
)
