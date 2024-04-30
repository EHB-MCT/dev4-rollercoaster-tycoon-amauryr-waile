package be.ehb.rollecoaster.repository

import be.ehb.rollecoaster.model.Onderhoud

import org.springframework.data.jpa.repository.JpaRepository

interface OnderhoudRepository : JpaRepository<Onderhoud, Long> {
    fun findByAttractieId (attractieId: Long): List<Onderhoud>
    //fun findNextByDatum(attractieId: Long): Onderhoud?
}