package be.ehb.rollecoaster.repository


import be.ehb.rollecoaster.model.Attractie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttractieRepository : JpaRepository<Attractie, Long> {



}
