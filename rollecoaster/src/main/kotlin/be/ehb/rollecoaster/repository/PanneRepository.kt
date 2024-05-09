package be.ehb.rollecoaster.repository

import be.ehb.rollecoaster.model.Panne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PanneRepository : JpaRepository<Panne, Long> {
    fun findByAttractieId(attractieId: Long): List<Panne>
    fun findByResolvedFalse(): List<Panne>

}
