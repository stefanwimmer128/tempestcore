package eu.stefanwimmer128.tempestcore

import eu.stefanwimmer128.tempestcore.api.TempestCoreAPI
import net.minecraftforge.fml.common.Mod

@Mod(modid = TempestCoreAPI.MODID, name = TempestCoreAPI.NAME, version = TempestCoreAPI.VERSION, dependencies = "required-after:forgelin@[1.8.2,)", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", updateJSON = "https://raw.githubusercontent.com/stefanwimmer128/tempestcore/master/versions.json")
object TempestCore: TempestCoreAPI {
    @Mod.Instance
    override lateinit var instance: TempestCore
}
