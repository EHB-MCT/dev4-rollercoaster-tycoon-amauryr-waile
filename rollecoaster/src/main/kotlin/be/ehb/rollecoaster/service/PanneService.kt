package be.ehb.rollecoaster.service


import be.ehb.rollecoaster.model.Panne
import be.ehb.rollecoaster.repository.PanneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PanneService {

    @Autowired
    lateinit var panneRepository: PanneRepository

    fun addPanne(panne: Panne): Panne {
        return panneRepository.save(panne)
    }

    fun markPanneAsResolved(id: Long) {
        val panne = panneRepository.findById(id)
        if (panne.isPresent) {
            val existingPanne = panne.get()
            existingPanne.resolved= true
            panneRepository.save(existingPanne)
        } else {
            throw NoSuchElementException("Panne with id $id not found")
        }
    }

    fun getAllActivePannes(): List<Panne> {
        return panneRepository.findByResolvedFalse()
    }

    fun getPannesForAttractie(attractieId: Long): List<Panne> {
        return panneRepository.findByAttractieId(attractieId)
    }

    fun getAllAttractionPannes(): Map<Long, Int> {
        return panneRepository.findAll()
            .filter { !it.resolved }
            .groupBy { it.attractieId }
            .mapValues { it.value.size }
    }
}
