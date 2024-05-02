    package be.ehb.rollecoaster.model


    import be.ehb.rollecoaster.model.Categorie
    import jakarta.persistence.*


    @Table(name = "attractie")
    @Entity
    data class Attractie(
        @Id @GeneratedValue
        var id: Long = -1,
        var naam: String,

        var capaciteit: Int,
        var bouwjaar: Int,
        var afbeeldingUrl: String,
        var onrideVideoUrl: String?,
        var lengte: Double,
        var tijdsduur: Double,
        var maximumHoogte: Double,
        var maximumSnelheid: Double,
        @OneToMany(mappedBy = "attractie")
        open var onderhoudsbeurten: List<Onderhoud> = mutableListOf()
    ){

        @ManyToOne
        lateinit var categorie: Categorie



    }