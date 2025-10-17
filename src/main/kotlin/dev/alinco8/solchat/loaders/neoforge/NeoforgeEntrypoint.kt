//? if neoforge {
/*package dev.alinco8.solchat.loaders.neoforge

import dev.alinco8.solchat.SolChat
import dev.alinco8.solchat.SolChat.LOGGER
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.ServerChatEvent

@Mod(SolChat.MOD_ID)
class NeoforgeEntrypoint(bus: IEventBus) {
    init {
        NeoForge.EVENT_BUS.register(this)

        SolChat.initialize()
    }

    @SubscribeEvent
    fun onChatMessage(ev: ServerChatEvent) {
        LOGGER.debug("Chat message event: {}", ev.message)
        ev.message = SolChat.onChatMessage(ev.message)
    }
}
*///?}
