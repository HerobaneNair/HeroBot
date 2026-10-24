plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "1.21.11" /* [SC] DO NOT EDIT */

stonecutter {
    parameters {
        replacements {
            string(eval(current.version, ">=26.1")) {
                replace("ClickType", "ContainerInput")
                replace("\"item\").getItem()", "\"item\").item().value()")
            }

            string(eval(current.version, ">=26.2")) {
                replace("net.minecraft.advancements.criterion.MinMaxBounds", "net.minecraft.advancements.predicates.MinMaxBounds")
            }

            string(eval(current.version, ">=26.3")) {
                // swing(hand[, self]) gained the SwingAnimation to play.
                replace("player.swing(InteractionHand.MAIN_HAND)", "player.swing(InteractionHand.MAIN_HAND, player.getMainHandItem().getAttackAnimation(), false)")
                replace("player.swing(InteractionHand.MAIN_HAND, true)", "player.swing(InteractionHand.MAIN_HAND, player.getMainHandItem().getAttackAnimation(), true)")
                replace("player.swing(hand, false)", "player.swing(hand, player.getItemInHand(hand).getAttackAnimation(), false)")
                replace("player.swing(hand)", "player.swing(hand, player.getItemInHand(hand).getInteractAnimation(), false)")
            }
        }
    }
}
