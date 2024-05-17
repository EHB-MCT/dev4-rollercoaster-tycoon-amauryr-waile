package be.ehb.rollecoaster.dto

import java.util.*


data class AttractieRequest(
    val naam: String,
    val categorie: CategorieRequest,
    val capaciteit: Int,
    val bouwjaar: Int,
    val afbeeldingUrl: String,
    val onrideVideoUrl: String?,
    val lengte: Double,
    val tijdsduur: Double,
    val maximumHoogte: Double,
    val maximumSnelheid: Double
)

