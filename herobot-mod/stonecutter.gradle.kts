plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "1.21.11" /* [SC] DO NOT EDIT */

stonecutter {
    parameters {
        replacements {
            string(eval(current.version, ">=26.1")) {
                replace("ClickType", "ContainerInput")
                replace("PayloadTypeRegistry.playS2C()", "PayloadTypeRegistry.clientboundPlay()")
                replace("PayloadTypeRegistry.playC2S()", "PayloadTypeRegistry.serverboundPlay()")
                replace("ServerWorldEvents", "ServerLevelEvents")
                replace("\"item\").getItem()", "\"item\").item().value()")

                replace("GuiGraphics", "GuiGraphicsExtractor")
                replace("g.drawString(", "g.text(")
                replace("graphics.drawStringWithBackdrop(", "graphics.textWithBackdrop(")
                replace("net.minecraft.client.gui.render.state", "net.minecraft.client.renderer.state.gui")
                replace("ClientCommandManager", "ClientCommands")
                replace("client.keybinding.v1.KeyBindingHelper", "client.keymapping.v1.KeyMappingHelper")
                replace("KeyBindingHelper.registerKeyBinding(", "KeyMappingHelper.registerKeyMapping(")
                replace("KeyBindingHelper.getBoundKeyOf(", "KeyMappingHelper.getBoundKeyOf(")
                replace("RenderTypes.itemEntityTranslucentCull(", "RenderTypes.entityTranslucentCullItemTarget(")
                replace("p.displayClientMessage(Component.literal(text), true)", "p.sendOverlayMessage(Component.literal(text))")
                replace("cart.getTags()", "cart.entityTags()")
                replace("net/minecraft/client/renderer/state/CameraRenderState", "net/minecraft/client/renderer/state/level/CameraRenderState")
                replace("handleInventoryMouseClick", "handleContainerInput")
                replace("box.render(g, ", "box.extractRenderState(g, ")
                replace("searchBox.render(g, ", "searchBox.extractRenderState(g, ")
                replace(".herobot\$guiRenderState().submitGuiElement(", ".herobot\$guiRenderState().addGuiElement(")
            }

            string(eval(current.version, ">=26.2")) {
                replace("net.minecraft.advancements.criterion.MinMaxBounds", "net.minecraft.advancements.predicates.MinMaxBounds")
                replace("net.minecraft.advancements.CriteriaTriggers", "net.minecraft.advancements.triggers.CriteriaTriggers")
                replace("EntityType.PLAYER", "net.minecraft.world.entity.EntityTypes.PLAYER")
                replace("EntityType.PIG", "net.minecraft.world.entity.EntityTypes.PIG")
                replace("knockback(DDD)V", "knockback(DDDLnet/minecraft/world/damagesource/DamageSource;FZ)V")

                replace("client.screen instanceof", "client.gui.screen() instanceof")
                replace("client.screen == null", "client.gui.screen() == null")
                replace("mc.screen ", "mc.gui.screen() ")
                replace("client.setScreen(", "client.gui.setScreen(")
                replace("mc.setScreen(", "mc.gui.setScreen(")
                replace("getInstance().setScreen(", "getInstance().gui.setScreen(")
                replace("mc.getOverlay()", "mc.gui.overlay()")
            }

            string(eval(current.version, ">=26.3")) {
                replace("hurtMarked", "syncVelocity")
                replace("PushReaction.IGNORE;", "PushReaction.IGNORE_ENTITY;")
                replace(".invulnerableTime = HeroBotSettings.damageInvulnerableTicks();", ".setInvulnerableTime(HeroBotSettings.damageInvulnerableTicks());")
                replace("this.invulnerableTime = 0;", "this.setInvulnerableTime(0);")
                replace("(float) this.invulnerableTime", "(float) this.getInvulnerableTime()")
                replace("target.invulnerableTime", "target.getInvulnerableTime()")

                replace("player.swing(InteractionHand.MAIN_HAND)", "player.swing(InteractionHand.MAIN_HAND, player.getMainHandItem().getAttackAnimation(), false)")
                replace("player.swing(InteractionHand.MAIN_HAND, true)", "player.swing(InteractionHand.MAIN_HAND, player.getMainHandItem().getAttackAnimation(), true)")
                replace("player.swing(hand, false)", "player.swing(hand, player.getItemInHand(hand).getAttackAnimation(), false)")
                replace("player.swing(hand)", "player.swing(hand, player.getItemInHand(hand).getInteractAnimation(), false)")
                replace("p.swing(InteractionHand.MAIN_HAND)", "p.swing(InteractionHand.MAIN_HAND, p.getMainHandItem().getAttackAnimation(), false)")

                replace("InputConstants.Type.KEYSYM", "InputConstants.Type.KEYBOARD")
                replace("InputConstants.isKeyDown(window, ", "InputConstants.isKeyDown(")
                replace("com.mojang.blaze3d.pipeline.RenderPipeline", "com.mojang.renderpearl.api.pipeline.RenderPipeline")
            }
        }
    }
}
