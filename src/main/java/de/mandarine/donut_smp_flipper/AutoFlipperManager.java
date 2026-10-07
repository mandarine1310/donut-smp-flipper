package de.mandarine.donut_smp_flipper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AutoFlipperManager {
    private final Map<String, List<Double>> priceHistory = new LinkedHashMap<>();
    private boolean enabled;
    private double stopLossPercent = 5.0;
    private double targetProfitPercent = 12.5;

    public void recordPrice(String itemId, double price) {
        priceHistory.computeIfAbsent(itemId, key -> new ArrayList<>()).add(price);

        if (priceHistory.get(itemId).size() > 48) {
            List<Double> values = priceHistory.get(itemId);
            values.subList(0, values.size() - 48).clear();
        }
    }

    public double getAveragePrice(String itemId) {
        List<Double> prices = priceHistory.getOrDefault(itemId, List.of());
        if (prices.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (double value : prices) {
            total += value;
        }
        return total / prices.size();
    }

    public double getLowestPrice(String itemId) {
        List<Double> prices = priceHistory.getOrDefault(itemId, List.of());
        if (prices.isEmpty()) {
            return 0.0;
        }

        return prices.stream().min(Double::compareTo).orElse(0.0);
    }

    public double getHighestPrice(String itemId) {
        List<Double> prices = priceHistory.getOrDefault(itemId, List.of());
        if (prices.isEmpty()) {
            return 0.0;
        }

        return prices.stream().max(Double::compareTo).orElse(0.0);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getStopLossPercent() {
        return stopLossPercent;
    }

    public void setStopLossPercent(double stopLossPercent) {
        this.stopLossPercent = Math.max(0.5, stopLossPercent);
    }

    public void simulateBestBuy() {
        recordPrice("diamond", 1800.0);
        recordPrice("iron_ingot", 140.0);
        recordPrice("gold_ingot", 250.0);
    }

    public void simulateBestSell() {
        recordPrice("diamond", 2100.0);
        recordPrice("iron_ingot", 175.0);
        recordPrice("gold_ingot", 320.0);
    }

    public String getStatsSummary() {
        return "Enabled: " + enabled +
                " | Stop-Loss: " + stopLossPercent + "%" +
                " | Diamond avg: " + String.format("%.2f", getAveragePrice("diamond")) +
                " | Iron avg: " + String.format("%.2f", getAveragePrice("iron_ingot")) +
                " | Gold avg: " + String.format("%.2f", getAveragePrice("gold_ingot"));
    }
}
