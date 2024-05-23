package be.ehb.rollecoaster.dto




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
    val maximumSnelheid: Double,
    val onderhouds: List<OnderhoudRequest> = emptyList(),
    val pannes: List<PanneRequest> = emptyList(),
    val resolvedPannesCount: Int
)

