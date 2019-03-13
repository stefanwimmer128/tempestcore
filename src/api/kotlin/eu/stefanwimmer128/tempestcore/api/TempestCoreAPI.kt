package eu.stefanwimmer128.tempestcore.api

interface TempestCoreAPI {
    companion object {
        const val MODID = "tempestcore"
        const val NAME = "TempestCore"
        const val VERSION = "#{VERSION}"
    }
    
    val instance: TempestCoreAPI
}
