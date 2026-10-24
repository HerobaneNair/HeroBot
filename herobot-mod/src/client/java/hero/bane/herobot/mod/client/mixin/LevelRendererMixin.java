package hero.bane.herobot.mod.client.mixin;

import hero.bane.herobot.common.rule.HeroBotSettings;
import hero.bane.herobot.mod.client.HeroBotClient;
import net.minecraft.client.player.LocalPlayer;
//? if >=26.2 {
/*import net.minecraft.client.Camera;
*///?} else
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//? if >=26.2 {
/*@Mixin(Camera.class)
*///?} else
@Mixin(LevelRenderer.class)
public class LevelRendererMixin
{
    //? if >=26.2 {
    /*@Redirect(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"))
    *///?} elif >=26.1 {
    /*@Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"))
    *///?} else
    @Redirect(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z"))
    private boolean canSeeWorld(LocalPlayer clientPlayerEntity)
    {
        return clientPlayerEntity.isSpectator() || (HeroBotSettings.creativeNoClip && clientPlayerEntity.isCreative() && HeroBotClient.isHeroBotLoaded());
    }
}
