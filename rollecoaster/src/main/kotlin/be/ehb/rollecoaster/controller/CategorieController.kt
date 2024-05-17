    package be.ehb.rollecoaster.controller


    import be.ehb.rollecoaster.dto.CategorieRequest
    import be.ehb.rollecoaster.model.Categorie
    import be.ehb.rollecoaster.service.CategorieService
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.http.HttpStatus
    import org.springframework.web.bind.annotation.*


    @RestController
    @CrossOrigin
    @RequestMapping("/categories")
    class CategorieController {

        @Autowired lateinit var categorieService: CategorieService
        @GetMapping("/all")
        fun getAllCategories(): List<Categorie> {
            return categorieService.getAllCategories()
        }

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        fun addCategorie(@RequestBody categorieRequest: CategorieRequest): Categorie {
            val categorie = Categorie(
                naam = categorieRequest.naam,
                attracties = mutableListOf()
            )
            return categorieService.addCategorie(categorie)
        }

        @PutMapping("/{id}")
        fun updateCategorie(@PathVariable id: Long, @RequestBody categorie: Categorie): Categorie? {
            return categorieService.updateCategorie(id, categorie)
        }


        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        fun deleteCategorie(@PathVariable id: Long) {
            categorieService.deleteCategorie(id)
        }

    }