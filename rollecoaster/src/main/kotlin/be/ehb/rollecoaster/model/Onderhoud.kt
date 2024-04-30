package be.ehb.rollecoaster.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
//import java.time.LocalDate


@Entity
data class Onderhoud(
    @Id
    @GeneratedValue
    val id: Long?,
    val datum: String,
    var opgelost: Boolean,
    @ManyToOne
    @JoinColumn(name="attractieId")
    val attractie: Attractie
)
