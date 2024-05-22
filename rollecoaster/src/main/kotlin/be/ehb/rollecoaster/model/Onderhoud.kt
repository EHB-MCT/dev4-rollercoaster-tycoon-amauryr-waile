package be.ehb.rollecoaster.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDate



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
    @JoinColumn(name="attractieId", insertable=false, updatable=false)
    @JsonBackReference
    lateinit var attractie: Attractie
}
