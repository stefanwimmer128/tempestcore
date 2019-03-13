package eu.stefanwimmer128.tempestcore.api.registry

import net.minecraft.item.Item

/**
 * Implement this to set custom Model registration procedure
 * @see Registry
 */
interface ICustomModelRegistration {
    /**
     * Implement your custom Model registration procedure here
     * @param setCustomModelResourceLocation Model registration function (preset with resourceDomain ([Registry.resourceDomain]]))
     */
    fun registerModel(setCustomModelResourceLocation: (item: Item, meta: Int, resourcePath: String, variant: String) -> Unit, resourceDomain: String)
}
