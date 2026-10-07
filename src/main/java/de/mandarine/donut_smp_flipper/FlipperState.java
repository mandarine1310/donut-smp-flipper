package de.mandarine.donut_smp_flipper;

public final class FlipperState {
    private static final AutoFlipperManager AUTO_FLIPPER_MANAGER = new AutoFlipperManager();

    private FlipperState() {
    }

    public static void initialize() {
        FlipperItems.register();
        AUTO_FLIPPER_MANAGER.recordPrice("diamond", 1800.0);
        AUTO_FLIPPER_MANAGER.recordPrice("diamond", 1900.0);
        AUTO_FLIPPER_MANAGER.recordPrice("diamond", 2000.0);
        AUTO_FLIPPER_MANAGER.recordPrice("iron_ingot", 120.0);
        AUTO_FLIPPER_MANAGER.recordPrice("iron_ingot", 135.0);
        AUTO_FLIPPER_MANAGER.recordPrice("iron_ingot", 150.0);
        AUTO_FLIPPER_MANAGER.recordPrice("gold_ingot", 220.0);
        AUTO_FLIPPER_MANAGER.recordPrice("gold_ingot", 260.0);
        AUTO_FLIPPER_MANAGER.recordPrice("gold_ingot", 280.0);
    }

    public static AutoFlipperManager getAutoFlipperManager() {
        return AUTO_FLIPPER_MANAGER;
    }
}
