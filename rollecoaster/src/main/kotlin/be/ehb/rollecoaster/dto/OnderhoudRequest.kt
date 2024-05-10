    package be.ehb.rollecoaster.dto

    import jakarta.persistence.Id
    import java.time.LocalDate

    data class OnderhoudRequest(
        var datum: LocalDate,
        var opgelost: Boolean,
        var attractieId: Long
    )
