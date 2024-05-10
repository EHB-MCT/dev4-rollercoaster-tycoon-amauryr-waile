package be.ehb.rollecoaster.service


import be.ehb.rollecoaster.model.Categorie
import be.ehb.rollecoaster.repository.AttractieRepository
import be.ehb.rollecoaster.repository.CategorieRepository
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.stereotype.Service

@Service
class CategorieService {

    @Autowired
    lateinit var attractieRepository: AttractieRepository

    @Autowired
    lateinit var categorieRepository: CategorieRepository
    fun getAllCategories(): List<Categorie> = categorieRepository.findAll()

    fun addCategorie(categorieRequest: Categorie): Categorie {
        val newCategorie = Categorie(
            id = null,
            naam = categorieRequest.naam,
            attracties = mutableListOf() // La liste d'attractions sera vide initialement
        )
        return categorieRepository.save(newCategorie)
    }

    fun getCategorieById(id: Long): Categorie? = categorieRepository.findById(id).orElse(null)

    fun updateCategorie(id: Long, updatedCategorie: Categorie): Categorie? {
        val existingCategorie = getCategorieById(id)
        if (existingCategorie != null) {
            existingCategorie.naam = updatedCategorie.naam
            // Update other properties as needed
            return categorieRepository.save(existingCategorie)
        }
        return null
    }

    fun deleteCategorie(id: Long) {
        categorieRepository.deleteById(id)
        val categorie = categorieRepository.findById(id).orElse(null)
        if (categorie != null) {
            // Vérifiez s'il y a des attractions associées à cette catégorie
            if (categorie.attracties.isNotEmpty()) {
                throw IllegalArgumentException("Cannot delete categorie with attractions associated")
            }
            // Supprimer la catégorie uniquement si aucune attraction n'y est associée
            categorieRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Categorie with id $id not found")
        }
    }



}
