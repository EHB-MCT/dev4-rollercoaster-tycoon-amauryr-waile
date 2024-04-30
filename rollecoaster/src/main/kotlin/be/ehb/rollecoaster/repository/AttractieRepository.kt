package be.ehb.rollecoaster.repository

import be.ehb.rollecoaster.dto.AttractieRequest
import be.ehb.rollecoaster.model.Attractie
import org.springframework.data.jpa.repository.JpaRepository

interface AttractieRepository : JpaRepository<Attractie, Long> {
    fun save(attractie: AttractieRequest): AttractieRequest

}
