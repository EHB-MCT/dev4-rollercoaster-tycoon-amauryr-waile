package be.ehb.rollecoaster.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany


@Entity
data class Categorie(

    @Id
    @GeneratedValue
    val id: Long?,
    var naam: String,
    @OneToMany(mappedBy = "categorie")
    val attracties: List<Attractie>

)
