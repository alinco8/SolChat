package dev.alinco8.solchat

import dev.alinco8.solchat.japanize.RomajiConverter
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SolChat {
    const val MOD_ID = "solchat"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    fun initialize() {}

    fun onChatMessage(component: Component): Component {
        if (!shouldProcess(component.string)) return component
        LOGGER.debug("Processing message: {}", component.string)

        return component.copy()
            .append("  →  ")
            .append(Component.literal(RomajiConverter.convert(component.string)).withStyle(Style.EMPTY.withColor(0xFFFF00)))
    }

    fun shouldProcess(message: String): Boolean {
        return message.length in 2..100
    }
}