package be.ehb.rollecoaster.repository

import Categorie


import org.springframework.data.jpa.repository.JpaRepository

interface CategorieRepository : JpaRepository<Categorie, Long>