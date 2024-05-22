package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.dto.AttractieRequest
import be.ehb.rollecoaster.model.Attractie
import be.ehb.rollecoaster.model.Categorie
import be.ehb.rollecoaster.model.Onderhoud
import be.ehb.rollecoaster.model.Panne
import be.ehb.rollecoaster.repository.AttractieRepository
import be.ehb.rollecoaster.repository.CategorieRepository
import be.ehb.rollecoaster.repository.PanneRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AttractieService {

    private val logger: Logger = LoggerFactory.getLogger(AttractieService::class.java)

    @Autowired
    lateinit var panneRepository: PanneRepository

    @Autowired
    lateinit var attractieRepository: AttractieRepository

    @Autowired
    lateinit var categorieRepository: CategorieRepository

    fun getAllAttracties(): List<Attractie> {
        val attracties = attractieRepository.findAll()
        attracties.forEach { attractie ->
            attractie.resolvedPannesCount = panneRepository.findByAttractieIdAndResolvedTrue(attractie.id!!).size
        }
        return attracties
    }
    fun getAttractieById(id: Long): Attractie? {
        return attractieRepository.findById(id).orElse(null)?.apply {
            resolvedPannesCount = panneRepository.findByAttractieIdAndResolvedTrue(id).size
        }
    }

    fun addAttractie(attractieRequest: AttractieRequest): Attractie {
        logger.info("Adding new attraction with request: $attractieRequest")
        val categorieNaam = attractieRequest.categorie.naam
        val categorie: Categorie? = categorieRepository.findByNaam(categorieNaam)
        val existingCategorie = categorie ?: Categorie(naam = categorieNaam)
        val savedCategorie = categorieRepository.save(existingCategorie)
        logger.info("Saved category: $savedCategorie")

        val newAttractie = Attractie(
            naam = attractieRequest.naam,
            categorie = savedCategorie,
            capaciteit = attractieRequest.capaciteit,
            bouwjaar = attractieRequest.bouwjaar,
            afbeeldingUrl = attractieRequest.afbeeldingUrl,
            onrideVideoUrl = attractieRequest.onrideVideoUrl,
            lengte = attractieRequest.lengte,
            tijdsduur = attractieRequest.tijdsduur,
            maximumHoogte = attractieRequest.maximumHoogte,
            maximumSnelheid = attractieRequest.maximumSnelheid,
            onderhoudsbeurten = attractieRequest.onderhouds.map {
                Onderhoud(
                    id = null,
                    datum = it.datum,
                    opgelost = it.opgelost,
                    attractieId = null
                )
            }.toMutableList(),
            pannes = attractieRequest.pannes.map {
                Panne(
                    id = null,
                    description = it.description,
                    resolved = it.resolved,
                    attractieId = null,
                    attractie = null
                )
            }.toMutableList()
        )

        val savedAttractie = attractieRepository.save(newAttractie)
        savedAttractie.onderhoudsbeurten.forEach { it.attractieId = savedAttractie.id }
        savedAttractie.pannes.forEach { it.attractieId = savedAttractie.id }
        attractieRepository.save(savedAttractie)
        logger.info("Saved new attraction: $savedAttractie")
        return savedAttractie
    }

    fun updateAttractie(id: Long, attractieRequest: AttractieRequest): Attractie? {
        logger.info("Updated new attraction with request: $attractieRequest")
        val existingAttractie = attractieRepository.findById(id)
        if (existingAttractie.isPresent) {
            val attractie = existingAttractie.get()
            attractie.naam = attractieRequest.naam
            attractie.capaciteit = attractieRequest.capaciteit
            attractie.bouwjaar = attractieRequest.bouwjaar
            attractie.afbeeldingUrl = attractieRequest.afbeeldingUrl
            attractie.onrideVideoUrl = attractieRequest.onrideVideoUrl
            attractie.lengte = attractieRequest.lengte
            attractie.tijdsduur = attractieRequest.tijdsduur
            attractie.maximumHoogte = attractieRequest.maximumHoogte
            attractie.maximumSnelheid = attractieRequest.maximumSnelheid

            val categorieNaam = attractieRequest.categorie.naam
            val categorie: Categorie? = categorieRepository.findByNaam(categorieNaam)
            val existingCategorie = categorie ?: Categorie(naam = categorieNaam)
            val savedCategorie = categorieRepository.save(existingCategorie)
            attractie.categorie = savedCategorie

            attractie.onderhoudsbeurten.clear()
            attractie.onderhoudsbeurten.addAll(attractieRequest.onderhouds.map {
                Onderhoud(
                    id = null,
                    datum = it.datum,
                    opgelost = it.opgelost,
                    attractieId = attractie.id
                )
            })

            attractie.pannes.clear()
            attractie.pannes.addAll(attractieRequest.pannes.map {
                Panne(
                    id = null,
                    description = it.description,
                    resolved = it.resolved,
                    attractieId = attractie.id,
                    attractie = attractie
                )
            })

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
