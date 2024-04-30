package be.ehb.rollecoaster.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.Date


@Entity
data class Panne(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    val description:String,
    val beginDate: Date,
    val endDate: Date,
    @ManyToMany(mappedBy = "pannes")
    val attractions: List<Attractie> = mutableListOf()

) {
}