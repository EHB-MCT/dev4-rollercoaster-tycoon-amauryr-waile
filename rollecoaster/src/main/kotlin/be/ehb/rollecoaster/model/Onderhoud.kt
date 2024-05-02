package be.ehb.rollecoaster.model

import jakarta.persistence.*

//import java.time.LocalDate


@Entity
data class Onderhoud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var datum: String,
    var opgelost: Boolean,

){
    @ManyToOne
    @JoinColumn(name="attractieId")
    lateinit var attractie: Attractie
}
