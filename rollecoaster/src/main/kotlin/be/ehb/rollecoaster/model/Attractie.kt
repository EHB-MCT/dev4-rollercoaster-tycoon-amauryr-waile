package be.ehb.rollecoaster.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
data class Attractie(
    @Id
    @GeneratedValue
    val id: Int?,
    var naam: String,
    var categorie: String,
    val capaciteit: Int,
    val bouwjaar: Int,
    val afbeeldingUrl: String,
    val onrideVideoUrl: String?,
    val lengte: Double,
    val tijdsduur: Double,
    val maximumHoogte : Double,
    val MaximumSnelheid : Double
    //@OneToMany(mappedBy = "attractie", cascade = [CascadeType.ALL])
    //val onderhoudsbeurten: List<Onderhoudsbeurt>
)