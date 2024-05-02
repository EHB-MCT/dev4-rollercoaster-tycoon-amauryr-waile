package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.model.Onderhoud
import be.ehb.rollecoaster.repository.OnderhoudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OnderhoudService {

    @Autowired lateinit var onderhoudRepository: OnderhoudRepository
    fun addOnderhoud(onderhoud: Onderhoud): Onderhoud = onderhoudRepository.save(onderhoud)

    fun markOnderhoudAsResolved(id: Long) {
        val onderhoud = onderhoudRepository.findById(id)
        onderhoud.ifPresent {
            it.opgelost = true
            onderhoudRepository.save(it)
        }
    }

    fun getAllOnderhouds(): List<Onderhoud> = onderhoudRepository.findAll()

    fun getOnderhoudsForAttractie(attractieId: Long): List<Onderhoud> =
        onderhoudRepository.findByAttractieId(attractieId)

    /*fun getOnderhoudsForAttractieInPanne(attractieId: Long): List<Onderhoud> =
        onderhoudRepository.findByAttractieIdAndOpgelostFalse(attractieId)*/

    /*fun getNumberOfPannesForAttractie(attractieId: Long): Long =
        onderhoudRepository.countByAttractieIdAndOpgelostFalse(attractieId)*/

   /* fun getNextMaintenanceDateForAttractie(attractieId: Long): LocalDate? =
        onderhoudRepository.findNextByDatum(attractieId: Long)?.datum*/
}
