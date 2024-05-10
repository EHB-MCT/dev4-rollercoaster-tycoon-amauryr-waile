package be.ehb.rollecoaster.controller

import be.ehb.rollecoaster.dto.AttractieRequest
import be.ehb.rollecoaster.dto.OnderhoudRequest
import be.ehb.rollecoaster.model.Onderhoud
import be.ehb.rollecoaster.service.OnderhoudService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("onderhoud")
class OnderhoudController{

    @Autowired lateinit var onderhoudService: OnderhoudService
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun addOnderhoud(@RequestBody onderhoud: OnderhoudRequest): Onderhoud {
        return onderhoudService.addOnderhoud(onderhoud)
    }


    @PutMapping("/{id}/resolve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun markOnderhoudAsResolved(@PathVariable id: Long) {
        onderhoudService.markOnderhoudAsResolved(id)
    }

    @GetMapping("/all")
    fun getAllOnderhouds(): List<Onderhoud> {
        return onderhoudService.getAllOnderhouds()
    }


    @GetMapping("/attractie/{attractieId}")
    fun getOnderhoudsForAttractie(@PathVariable attractieId: Long): List<Onderhoud> {
        return onderhoudService.getOnderhoudsForAttractie(attractieId)
    }
    @GetMapping("/attractie/{attractieId}/next_onderhoud")
    fun getNextOnderhoudForAttractie(@PathVariable attractieId: Long): String? {
        val nextOnderhoudDate = onderhoudService.getNextOnderhoudForAttractie(attractieId)
        return nextOnderhoudDate?.toString()
    }
}