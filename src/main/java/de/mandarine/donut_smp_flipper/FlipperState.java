package de.mandarine.donut_smp_flipper;

public final class FlipperState {
    private static final AutoFlipperManager AUTO_FLIPPER_MANAGER = new AutoFlipperManager();

    private FlipperState() {
    }

    public static void initialize() {
        AUTO_FLIPPER_MANAGER.applySampleMarketData();
        AUTO_FLIPPER_MANAGER.setEnabled(true);
        AUTO_FLIPPER_MANAGER.setStopLossPercent(5.0);
        AUTO_FLIPPER_MANAGER.setTargetProfitPercent(12.5);
    }

    public static AutoFlipperManager getAutoFlipperManager() {
        return AUTO_FLIPPER_MANAGER;
    }
}
