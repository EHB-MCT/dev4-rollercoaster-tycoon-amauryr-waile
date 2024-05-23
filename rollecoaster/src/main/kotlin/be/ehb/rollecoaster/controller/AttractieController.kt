    package be.ehb.rollecoaster.controller



    import be.ehb.rollecoaster.dto.AttractieRequest
    import be.ehb.rollecoaster.model.Attractie
    import be.ehb.rollecoaster.service.AttractieService
    import org.slf4j.Logger
    import org.slf4j.LoggerFactory
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.http.HttpStatus
    import org.springframework.web.bind.annotation.*

    @RestController
    @CrossOrigin
    @RequestMapping("/attracties")
    class AttractieController {

        private val logger: Logger = LoggerFactory.getLogger(AttractieController::class.java)


        @Autowired
        lateinit var attractieService: AttractieService

        @GetMapping("/all")
        fun getAllAttracties(): List<Attractie> {
            return attractieService.getAllAttracties()
        }

        @GetMapping("/{id}")
        fun getAttractieById(@PathVariable id: Long): Attractie? {
            return attractieService.getAttractieById(id)
        }



        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        fun addAttractie(@RequestBody attractieRequest: AttractieRequest): Attractie {
            val newAttractie = attractieService.addAttractie(attractieRequest)
            logger.info("Created new attraction: $newAttractie")
            return newAttractie
        }


        @PutMapping("/{id}")
        fun updateAttractie(@PathVariable id: Long, @RequestBody attractieRequest: AttractieRequest): Attractie? {
            return attractieService.updateAttractie(id, attractieRequest)
        }


        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun deleteAttractie(@PathVariable id: Long): String {
           if( attractieService.deleteAttractie(id)){
               return "Attractie has been succefully deleted"
           } else {
               return "Error deleting attractie"
           }
        }

        @GetMapping("/{id}/pannes/count")
        fun getNumberOfPannesForAttractie(@PathVariable id: Long): Long {
            return attractieService.getNumberOfPannesForAttractie(id)
        }
    }
