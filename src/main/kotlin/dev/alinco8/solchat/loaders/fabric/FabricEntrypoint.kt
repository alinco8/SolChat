//? if fabric {
package dev.alinco8.solchat.loaders.fabric

import dev.alinco8.solchat.SolChat
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.message.v1.ServerMessageDecoratorEvent

class FabricEntrypoint : ModInitializer {
    override fun onInitialize() {
        SolChat.initialize()

        ServerMessageDecoratorEvent.EVENT.register { _, component ->
            SolChat.onChatMessage(component)
        }
    }
}
//?}
