    package be.ehb.rollecoaster.model



    import com.fasterxml.jackson.annotation.JsonManagedReference
    import jakarta.persistence.*


    @Table(name = "attractie")
    @Entity
    data class Attractie(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = -1,
        var naam: String,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "categorie_id")
        @JsonManagedReference
        var categorie: Categorie?,
        var capaciteit: Int,
        var bouwjaar: Int,
        var afbeeldingUrl: String,
        var onrideVideoUrl: String?,
        var lengte: Double,
        var tijdsduur: Double,
        var maximumHoogte: Double,
        var maximumSnelheid: Double,
        @OneToMany(mappedBy = "attractie", cascade = [CascadeType.ALL], orphanRemoval = true)
        open var onderhoudsbeurten: MutableList<Onderhoud> = mutableListOf(),

        @OneToMany(mappedBy = "attractie", cascade = [CascadeType.ALL], orphanRemoval = true)
        var pannes: MutableList<Panne> = mutableListOf()
    ){}