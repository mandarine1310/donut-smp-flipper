package de.mandarine.donut_smp_flipper;

import net.fabricmc.api.ClientModInitializer;
import de.mandarine.donut_smp_flipper.client.keybind.FlipperKeybind;

public class DonutSMPFlipperClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FlipperKeybind.register();
    }
}
