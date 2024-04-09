package be.ehb.rollecoaster.service

import be.ehb.rollecoaster.model.Categorie
import be.ehb.rollecoaster.repository.CategorieRepository


import org.springframework.stereotype.Service

@Service
class CategorieService(private val categorieRepository: CategorieRepository) {
    fun getAllCategories(): List<Categorie> = categorieRepository.findAll()

    fun addCategorie(categorie: Categorie): Categorie = categorieRepository.save(categorie)

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
    }
}
