package be.ehb.rollecoaster.repository


import be.ehb.rollecoaster.model.Categorie


import org.springframework.data.jpa.repository.JpaRepository

interface CategorieRepository : JpaRepository<Categorie, Long>