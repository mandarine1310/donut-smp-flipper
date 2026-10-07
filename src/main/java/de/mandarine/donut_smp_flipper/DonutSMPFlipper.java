package de.mandarine.donut_smp_flipper;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DonutSMPFlipper implements ModInitializer {
    public static final String MOD_ID = "donut_smp_flipper";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        FlipperItems.register();
        FlipperState.initialize();
        LOGGER.info("Donut SMP Flipper initialized");
    }
}
