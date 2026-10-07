package de.mandarine.donut_smp_flipper.client.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FlipperKeybind {
    public static final KeyBinding OPEN_FLIPPER = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.donut_smp_flipper.open_menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_P,
            "category.donut_smp_flipper"
    ));

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_FLIPPER.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new de.mandarine.donut_smp_flipper.client.gui.MainFlipperScreen(net.minecraft.text.Text.literal("Donut SMP Flipper")));
                }
            }
        });
    }
}
