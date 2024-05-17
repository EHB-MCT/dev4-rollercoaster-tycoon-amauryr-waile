package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.dto.OnderhoudRequest
import be.ehb.rollecoaster.model.Onderhoud
import be.ehb.rollecoaster.repository.OnderhoudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OnderhoudService {

    @Autowired lateinit var onderhoudRepository: OnderhoudRepository
    fun addOnderhoud(onderhoud: OnderhoudRequest): Onderhoud {
        val nieuweOnderhoud = Onderhoud(
            id = null,
            datum = onderhoud.datum,
            opgelost = onderhoud.opgelost,
            attractieId = onderhoud.attractieId
        )
        return onderhoudRepository.save(nieuweOnderhoud)
    }


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

    fun getNextOnderhoudForAttractie (attractieId: LocalDate): LocalDate? =
        onderhoudRepository.findNextByDatum(attractieId)?.datum
}
