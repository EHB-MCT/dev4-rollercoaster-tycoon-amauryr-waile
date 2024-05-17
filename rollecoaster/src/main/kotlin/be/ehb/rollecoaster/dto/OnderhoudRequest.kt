    package be.ehb.rollecoaster.dto


    import java.time.LocalDate

    data class OnderhoudRequest(
        var datum: LocalDate,
        var opgelost: Boolean,
        var attractieId: Long
    )
