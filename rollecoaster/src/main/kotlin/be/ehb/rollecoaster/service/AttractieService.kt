package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.model.Attractie
import be.ehb.rollecoaster.repository.AttractieRepository
import org.springframework.stereotype.Service

@Service
class AttractieService(private val attractieRepository: AttractieRepository) {
    fun getAllAttracties(): List<Attractie> = attractieRepository.findAll()

    fun addAttractie(attractie: Attractie): Attractie = attractieRepository.save(attractie)

    // andere methoden zoals updateAttractie, deleteAttractie, etc.
}
