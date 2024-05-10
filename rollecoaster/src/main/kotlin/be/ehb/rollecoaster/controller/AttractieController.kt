    package be.ehb.rollecoaster.controller



    import be.ehb.rollecoaster.dto.AttractieRequest
    import be.ehb.rollecoaster.model.Attractie
    import be.ehb.rollecoaster.service.AttractieService
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.http.HttpStatus
    import org.springframework.web.bind.annotation.*

    @RestController
    @CrossOrigin
    @RequestMapping("/attracties")
    class AttractieController {

        @Autowired lateinit var attractieService: AttractieService

        @GetMapping("/all")
        fun getAllAttracties(): List<Attractie> {
            return attractieService.getAllAttracties()
        }


        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        fun addAttractie(@RequestBody attractie: AttractieRequest): AttractieRequest {
            return attractieService.addAttractie(attractie)
        }

        @PutMapping("/{id}")
        fun updateAttractie(@PathVariable id: Long, @RequestBody attractie: Attractie): Attractie? {
            return attractieService.updateAttractie(id, attractie)
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun deleteAttractie(@PathVariable id: Long): String {
           if( attractieService.deleteAttractie(id)){
               return "Attractie has been succefully deleted"
           } else {
               return "Error deleting attractie"
           }




            /**   val categorie = categorieService.getCategorieById(id)
            val attractionsConnected = categorie?.attractions?.size ?: 0
            if (attractionsConnected > 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "This category is still associated with attractions and cannot be deleted.")
            } else {
            categorieService.deleteCategorie(id)
            }*/
        }

        @GetMapping("/{id}/pannes/count")
        fun getNumberOfPannesForAttractie(@PathVariable id: Long): Long {
            return attractieService.getNumberOfPannesForAttractie(id)
        }



    }
