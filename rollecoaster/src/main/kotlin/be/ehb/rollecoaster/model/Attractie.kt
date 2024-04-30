package be.ehb.rollecoaster.model

import Categorie
import jakarta.persistence.*


@Entity
data class Attractie(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var naam: String,
    @ManyToOne @JoinColumn(name = "categories_id") var categorie: Categorie,
    val capaciteit: Int,
    val bouwjaar: Int,
    val afbeeldingUrl: String,
    val onrideVideoUrl: String?,
    val lengte: Double,
    val tijdsduur: Double,
    val maximumHoogte: Double,
    val maximumSnelheid: Double,
    @OneToMany(mappedBy = "attractie") val onderhoudsbeurten: List<Onderhoud> = mutableListOf()
)