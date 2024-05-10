package be.ehb.rollecoaster.dto

import java.util.Date

data class PanneRequest(val attractieId:Long,val description:String,val beginDate: Date, val endDate: Date)
