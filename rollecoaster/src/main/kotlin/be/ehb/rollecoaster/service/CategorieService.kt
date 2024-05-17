package be.ehb.rollecoaster.service


import be.ehb.rollecoaster.model.Categorie
import be.ehb.rollecoaster.repository.AttractieRepository
import be.ehb.rollecoaster.repository.CategorieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.LoggerFactory



import org.springframework.stereotype.Service

@Service
class CategorieService {



    @Autowired
    lateinit var categorieRepository: CategorieRepository

    private val logger = LoggerFactory.getLogger(CategorieService::class.java)

    fun getAllCategories(): List<Categorie> = categorieRepository.findAll()

    fun addCategorie(categorie: Categorie): Categorie {
        val newCategorie = Categorie(
            id = categorie.id,
            naam = categorie.naam,
            attracties = mutableListOf()
        )
        return categorieRepository.save(newCategorie)
    }

    fun getCategorieById(id: Long): Categorie? = categorieRepository.findById(id).orElse(null)

    fun updateCategorie(id: Long, updatedCategorie: Categorie): Categorie? {
        logger.info("Updating category with ID: $id")
        val existingCategorie = getCategorieById(id)
        return if (existingCategorie != null) {
            existingCategorie.naam = updatedCategorie.naam
            // Update other properties as needed
            val savedCategorie = categorieRepository.save(existingCategorie)
            logger.info("Updated category: $savedCategorie")
            savedCategorie
        } else {
            logger.error("Category with ID: $id not found")
            null
        }
    }

    fun deleteCategorie(id: Long) {
        val categorie = categorieRepository.findById(id).orElse(null)
        if (categorie != null) {
            if (categorie.attracties.isNotEmpty()) {
                throw IllegalArgumentException("Cannot delete categorie with attractions associated")
            }
            categorieRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Categorie with id $id not found")
        }
    }



}
