package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.model.Onderhoudsbeurt
import be.ehb.rollecoaster.repository.OnderhoudsbeurtRepository



import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OnderhoudsbeurtService(private val onderhoudsbeurtRepository: OnderhoudsbeurtRepository) {
    fun addOnderhoudsbeurt(onderhoudsbeurt: Onderhoudsbeurt): Onderhoudsbeurt = onderhoudsbeurtRepository.save(onderhoudsbeurt)

    fun markOnderhoudsbeurtAsResolved(id: Long) {
        val onderhoudsbeurt = onderhoudsbeurtRepository.findById(id)
        onderhoudsbeurt.ifPresent {
            it.opgelost = true
            onderhoudsbeurtRepository.save(it)
        }
    }

    fun getAllOnderhoudsbeurten(): List<Onderhoudsbeurt> = onderhoudsbeurtRepository.findAll()

    fun getOnderhoudsbeurtenForAttractie(attractieId: Long): List<Onderhoudsbeurt> =
        onderhoudsbeurtRepository.findByAttractieId(attractieId)

    fun getOnderhoudsbeurtenForAttractieInPanne(attractieId: Long): List<Onderhoudsbeurt> =
        onderhoudsbeurtRepository.findByAttractieIdAndOpgelostFalse(attractieId)

    fun getNumberOfPannesForAttractie(attractieId: Long): Long =
        onderhoudsbeurtRepository.countByAttractieIdAndOpgelostFalse(attractieId)

    fun getNextMaintenanceDateForAttractie(attractieId: Long): LocalDate? =
        onderhoudsbeurtRepository.findFirstByAttractieIdAndOpgelostOrderByDatumAsc(attractieId, false)?.datum
}
