package hero.bane.herobot.mod.client.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiGraphics.class)
public interface GuiRenderStateAccessor {
    @Accessor("guiRenderState")
    GuiRenderState herobot$guiRenderState();
}
