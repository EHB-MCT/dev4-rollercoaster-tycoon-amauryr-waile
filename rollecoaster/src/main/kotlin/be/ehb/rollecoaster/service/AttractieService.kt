package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.dto.AttractieRequest
import be.ehb.rollecoaster.model.Attractie
import be.ehb.rollecoaster.repository.AttractieRepository
import be.ehb.rollecoaster.repository.PanneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service

class AttractieService {

    @Autowired
    lateinit var panneRepository: PanneRepository



    @Autowired
    lateinit var attractieRepository: AttractieRepository



    fun getAllAttracties(): List<Attractie> = attractieRepository.findAll()


    fun addAttractie(attractie: AttractieRequest): AttractieRequest = attractieRepository.save(attractie)

    fun updateAttractie(id: Long, updatedAttractie: Attractie): Attractie? {
        val existingAttractie = attractieRepository.findById(id)
        if (existingAttractie.isPresent) {
            val attractie = existingAttractie.get()
            attractie.naam = updatedAttractie.naam
            attractie.categorie= updatedAttractie.categorie
            return attractieRepository.save(attractie)
        }
        return null
    }

    fun deleteAttractie(id: Long): Boolean {
        return try {
            attractieRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getNumberOfPannesForAttractie(attractieId: Long): Long {
        return panneRepository.findByAttractieId(attractieId).size.toLong()
    }

}
