package dev.alinco8.solchat.mixin;

import dev.alinco8.solchat.SolChat;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.commands.MsgCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MsgCommand.class)
public class DirectMessageMixin {
    @Redirect(
        method = "sendMessage",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/network/chat/OutgoingChatMessage;create(Lnet/minecraft/network/chat/PlayerChatMessage;)Lnet/minecraft/network/chat/OutgoingChatMessage;"
        )
    )
    private static OutgoingChatMessage sendDM(PlayerChatMessage p_249173_
    ) {
        return OutgoingChatMessage.create(p_249173_.withUnsignedContent(
            SolChat.INSTANCE.onChatMessage(p_249173_.decoratedContent())
        ));
    }
}
