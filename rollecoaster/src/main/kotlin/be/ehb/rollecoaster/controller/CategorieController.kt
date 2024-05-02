package be.ehb.rollecoaster.controller


import be.ehb.rollecoaster.model.Categorie
import be.ehb.rollecoaster.service.CategorieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/categories")
class CategorieController {

    @Autowired lateinit var categorieService: CategorieService
    @GetMapping
    fun getAllCategories(): List<Categorie> {
        return categorieService.getAllCategories()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCategorie(@RequestBody categorie: Categorie): Categorie {
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