package be.ehb.rollecoaster.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.Date


//@Entity
data class Panne(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    var description:String,
    var beginDate: Date,
    var endDate: Date,
    //@ManyToMany(mappedBy = "pannes")
    //var attractions: List<Attractie> = mutableListOf()


)
{}