package be.ehb.rollecoaster.repository

import be.ehb.rollecoaster.model.Onderhoudsbeurt

import org.springframework.data.jpa.repository.JpaRepository

interface OnderhoudsbeurtRepository : JpaRepository<Onderhoudsbeurt, Long> {
    fun findByAttractieIdAndOpgelost(attractieId: Long, opgelost: Boolean): List<Onderhoudsbeurt>

    fun findByAttractieId (attractieId: Long): List<Onderhoudsbeurt>
    fun findByAttractieIdAndOpgelostFalse(attractieId: Long): List<Onderhoudsbeurt>
    fun countByAttractieIdAndOpgelostFalse(attractieId: Long): Long
    fun findFirstByAttractieIdAndOpgelostOrderByDatumAsc(attractieId: Long, opgelost: Boolean): Onderhoudsbeurt?
}