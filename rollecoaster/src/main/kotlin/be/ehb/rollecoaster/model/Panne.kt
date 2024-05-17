package be.ehb.rollecoaster.model


import jakarta.persistence.*
import java.util.Date


@Entity
data class Panne(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    val id: Long?,
    var description:String,
    var beginDate: Date,
    var endDate: Date,
    var resolved: Boolean,
    val attractieId: Long,
    @ManyToOne()
    @JoinColumn(name = "attractieId", insertable = false, updatable = false)
    var attractie: Attractie?


)
{}