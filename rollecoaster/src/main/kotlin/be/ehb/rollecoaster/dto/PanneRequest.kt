package be.ehb.rollecoaster.dto

import java.util.Date

data class PanneRequest(val attractieId:Int,val description:String,val beginDate: Date, val endDate: Date)
