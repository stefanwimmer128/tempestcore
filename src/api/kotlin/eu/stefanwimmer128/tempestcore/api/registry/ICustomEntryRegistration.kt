package eu.stefanwimmer128.tempestcore.api.registry

import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

/**
 * Implement this to set custom Registration procedure
 * @see Registry
 */
interface ICustomEntryRegistration<T: IForgeRegistryEntry<T>> {
    /**
     * Implement your custom Registration procedure here
     * @param registy Forge Registry (retrieved through [net.minecraftforge.event.RegistryEvent])
     */
    fun registerEntry(registry: IForgeRegistry<T>)
}
