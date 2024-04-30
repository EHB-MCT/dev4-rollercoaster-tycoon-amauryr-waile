import be.ehb.rollecoaster.model.Attractie
import jakarta.persistence.*


@Entity
data class Categorie(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long?,
    var naam: String,
    @OneToMany(mappedBy = "categorie") val attracties: List<Attractie> = mutableListOf()
)