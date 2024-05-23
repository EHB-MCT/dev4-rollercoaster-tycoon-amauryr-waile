package be.ehb.rollecoaster.model


import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "categorie")
data class Categorie(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,
    var naam: String,
    @OneToMany(mappedBy = "categorie", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonBackReference
    var attracties: MutableList<Attractie> = mutableListOf()
)
