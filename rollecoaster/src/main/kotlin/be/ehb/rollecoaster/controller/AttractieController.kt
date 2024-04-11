package be.ehb.rollecoaster.controller



import be.ehb.rollecoaster.model.Attractie
import be.ehb.rollecoaster.service.AttractieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/attracties")
class AttractieController(private val attractieService: AttractieService) {

    @GetMapping
    fun getAllAttracties(): List<Attractie> {
        return attractieService.getAllAttracties()
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addAttractie(@RequestBody attractie: Attractie): Attractie {
        return attractieService.addAttractie(attractie)
    }

    @PutMapping("/{id}")
    fun updateAttractie(@PathVariable id: Long, @RequestBody attractie: Attractie): Attractie? {
        return attractieService.updateAttractie(id, attractie)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAttractie(@PathVariable id: Long) {
        attractieService.deleteAttractie(id)
    }



}
