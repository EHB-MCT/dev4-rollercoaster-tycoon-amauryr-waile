package be.ehb.rollecoaster.model

import jakarta.persistence.*
import java.time.LocalDate

//import java.time.LocalDate


@Entity
data class Onderhoud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var datum: LocalDate,
    var opgelost: Boolean,
    var attractieId: Long? = null

){
    @ManyToOne
    @JoinColumn(name="attractieId")
    lateinit var attractie: Attractie
}
