package be.ehb.rollecoaster.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate


@Entity
data class Onderhoudsbeurt(
    @Id
    @GeneratedValue
    val id: Long?,
    val datum: LocalDate,
    var opgelost: Boolean,
    @ManyToOne
    @JoinColumn(name="attractie_id")
    val attractie: Attractie
)
