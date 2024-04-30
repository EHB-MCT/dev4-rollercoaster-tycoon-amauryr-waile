package be.ehb.rollecoaster.model

import jakarta.persistence.*

//import java.time.LocalDate


@Entity
data class Onderhoud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val datum: String,
    var opgelost: Boolean,
    @ManyToOne
    @JoinColumn(name="attractieId")
    val attractie: Attractie
)
