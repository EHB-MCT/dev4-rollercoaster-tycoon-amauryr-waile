package be.ehb.rollecoaster.model


import jakarta.persistence.*

@Entity
@Table(name = "Categorie")
data class Categorie(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,
    var naam: String,
    @OneToMany(mappedBy = "categorie")
    var attracties: List<Attractie> = mutableListOf()
){
}