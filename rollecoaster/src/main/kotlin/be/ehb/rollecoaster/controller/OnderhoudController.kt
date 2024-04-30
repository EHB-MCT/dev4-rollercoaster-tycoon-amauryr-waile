package be.ehb.rollecoaster.controller

import be.ehb.rollecoaster.model.Onderhoud
import be.ehb.rollecoaster.service.OnderhoudService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/onderhoud")
class OnderhoudController(private val onderhoudService: OnderhoudService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addOnderhoud(@RequestBody onderhoud: Onderhoud): Onderhoud {
        return onderhoudService.addOnderhoud(onderhoud)
    }

    @PutMapping("/{id}/resolve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun markOnderhoudAsResolved(@PathVariable id: Long) {
        onderhoudService.markOnderhoudAsResolved(id)
    }

    @GetMapping
    fun getAllOnderhouds(): List<Onderhoud> {
        return onderhoudService.getAllOnderhouds()
    }

    @GetMapping("/attractie/{attractieId}")
    fun getOnderhoudsForAttractie(@PathVariable attractieId: Long): List<Onderhoud> {
        return onderhoudService.getOnderhoudsForAttractie(attractieId)
    }

   /* @GetMapping("/attractie/{attractieId}/panne")
    fun getOnderhoudsForAttractieInPanne(@PathVariable attractieId: Long): List<Onderhoud> {
        return onderhoudService.getOnderhoudsForAttractieInPanne(attractieId)
    }

    @GetMapping("/attractie/{attractieId}/panne/count")
    fun getNumberOfPannesForAttractie(@PathVariable attractieId: Long): Long {
        return onderhoudService.getNumberOfPannesForAttractie(attractieId)
    }

    @GetMapping("/attractie/{attractieId}/next-maintenance")
    fun getNextMaintenanceDateForAttractie(@PathVariable attractieId: Long): String? {
        val nextMaintenanceDate = onderhoudService.getNextMaintenanceDateForAttractie(attractieId)
        return nextMaintenanceDate?.toString()*/
    //}
}