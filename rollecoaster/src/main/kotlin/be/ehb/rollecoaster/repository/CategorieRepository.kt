package be.ehb.rollecoaster.repository


import be.ehb.rollecoaster.model.Categorie


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategorieRepository : JpaRepository<Categorie, Long> {
    fun findByNaam(naam: String): Categorie?
}