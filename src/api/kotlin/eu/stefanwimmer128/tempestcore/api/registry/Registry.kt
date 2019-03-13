package eu.stefanwimmer128.tempestcore.api.registry

import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

/**
 * Registry to manage blocks / items / etc.
 * @param T Type of entries (must be subtype of [net.minecraftforge.registries.IForgeRegistryEntry]
 * @param resourceDomain Resource domain to use for model registration (typically modid)
 * @param registry Pass another Map (Registry) to prepopulate this registry
 */
open class Registry<T: IForgeRegistryEntry<T>> constructor(val resourceDomain: String, registry: MutableMap<String, T> = mutableMapOf()): MutableMap<String, T> by registry {
    /**
     * List of listeners for entry add (called by [add])
     */
    val onAddListeners: MutableList<(entry: T, key: String) -> Unit> = mutableListOf()
    
    /**
     * Adds entry to the registry
     * @param R Type of entity (must be subtype of [T])
     * @param entry Entry to add
     * @returns Returns param `entry`
     */
    open fun <R: T> add(entry: R, key: String = entry.registryName!!.resourcePath): R {
        this[key] = entry
        onAddListeners.forEach {
            it(entry, key)
        }
        return entry
    }
    
    /**
     * Pass Forge Registry to register entries
     * @param registry Forge Registry (retrieved through the [net.minecraftforge.event.RegistryEvent])
     */
    open fun registerEntries(registry: IForgeRegistry<T>) =
        values.forEach {
            @Suppress("UNCHECKED_CAST")
            if ((it as? ICustomEntryRegistration<T>)?.registerEntry(registry) == null)
                registry.register(it)
        }
    
    /**
     * Register Models for entries
     */
    open fun registerModels() {
        fun setCustomModelResourceLocation(item: Item, meta: Int, resourcePath: String, variant: String) {
            ModelLoader.setCustomModelResourceLocation(item, meta, ModelResourceLocation(ResourceLocation(resourceDomain, resourcePath), variant))
        }
    
        this.values.forEach {
            when (it) {
                is ICustomModelRegistration ->
                    it.registerModel(::setCustomModelResourceLocation, resourceDomain)
                is Item ->
                    setCustomModelResourceLocation(it, 0, it.registryName!!.resourcePath, "inventory")
                is Block ->
                    setCustomModelResourceLocation(Item.getItemFromBlock(it), 0, it.registryName!!.resourcePath, "inventory")
            }
        }
    }
    
    /**
     * Iterate all entries
     * @param receiver Function called with each entry
     */
    fun all(receiver: T.(key: String) -> Unit) =
        apply {
            forEach {
                it.value.receiver(it.key)
            }
        }
}
