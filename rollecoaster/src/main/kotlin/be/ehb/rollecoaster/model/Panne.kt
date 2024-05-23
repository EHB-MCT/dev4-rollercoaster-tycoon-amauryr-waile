package be.ehb.rollecoaster.model


import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*


@Entity
data class Panne(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    val id: Long?,
    var description:String,
    var resolved: Boolean,
    var attractieId: Long?,
    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "attractieId", insertable = false, updatable = false)
    var attractie: Attractie?


)
