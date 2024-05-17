package be.ehb.rollecoaster.repository

import be.ehb.rollecoaster.model.Onderhoud

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface OnderhoudRepository : JpaRepository<Onderhoud, Long> {
    fun findByAttractieId (attractieId: Long): List<Onderhoud>
    fun findNextByDatum(datum: LocalDate): Onderhoud?

}