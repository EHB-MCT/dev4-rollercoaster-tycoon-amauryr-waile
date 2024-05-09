package be.ehb.rollecoaster.controller



import be.ehb.rollecoaster.model.Panne
import be.ehb.rollecoaster.service.PanneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/panne")
class Panne {

    @RestController
    @RequestMapping("/panne")
    class PanneController {

        @Autowired
        lateinit var panneService: PanneService

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        fun addPanne(@RequestBody panne: Panne): Panne {
            return panneService.addPanne(panne)
        }

        @PutMapping("/{id}/resolve")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun markPanneAsResolved(@PathVariable id: Long) {
            panneService.markPanneAsResolved(id)
        }

        @GetMapping
        fun getAllActivePannes(): List<Panne> {
            return panneService.getAllActivePannes()
        }

        @GetMapping("/attractie/{attractieId}")
        fun getPannesForAttractie(@PathVariable attractieId: Long): List<Panne> {
            return panneService.getPannesForAttractie(attractieId)
        }

        @GetMapping("/overzicht")
        fun getAllAttractionPannes(): Map<Long, Int> {
            return panneService.getAllAttractionPannes()
        }
    }


}
