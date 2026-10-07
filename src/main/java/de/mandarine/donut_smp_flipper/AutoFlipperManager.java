package de.mandarine.donut_smp_flipper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoFlipperManager {
    private static final int MAX_HISTORY = 24;

    private final Map<String, Deque<Double>> priceHistory = new HashMap<>();
    private final Map<String, Double> lastBuyPrice = new HashMap<>();

    private boolean enabled;
    private double stopLossPercent = 5.0;
    private double targetProfitPercent = 12.5;

    public void recordPrice(String itemId, double price) {
        Deque<Double> history = priceHistory.computeIfAbsent(itemId, key -> new ArrayDeque<>());
        history.addLast(price);

        while (history.size() > MAX_HISTORY) {
            history.removeFirst();
        }
    }

    public List<Double> getPrices(String itemId) {
        Deque<Double> history = priceHistory.getOrDefault(itemId, new ArrayDeque<>());
        return new ArrayList<>(history);
    }

    public double getAveragePrice(String itemId) {
        List<Double> prices = getPrices(itemId);
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
        List<Double> prices = getPrices(itemId);
        if (prices.isEmpty()) {
            return 0.0;
        }

        double min = prices.get(0);
        for (double value : prices) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public double getHighestPrice(String itemId) {
        List<Double> prices = getPrices(itemId);
        if (prices.isEmpty()) {
            return 0.0;
        }

        double max = prices.get(0);
        for (double value : prices) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public double getSpread(String itemId) {
        double low = getLowestPrice(itemId);
        double high = getHighestPrice(itemId);
        if (low <= 0.0 || high <= 0.0) {
            return 0.0;
        }
        return ((high - low) / low) * 100.0;
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
        this.stopLossPercent = Math.max(1.0, Math.min(stopLossPercent, 25.0));
    }

    public void setTargetProfitPercent(double targetProfitPercent) {
        this.targetProfitPercent = Math.max(1.0, targetProfitPercent);
    }

    public double getTargetProfitPercent() {
        return targetProfitPercent;
    }

    public void applySampleMarketData() {
        recordPrice("diamond", 1800.0);
        recordPrice("diamond", 1880.0);
        recordPrice("diamond", 2000.0);
        recordPrice("diamond", 2100.0);

        recordPrice("gold_ingot", 220.0);
        recordPrice("gold_ingot", 240.0);
        recordPrice("gold_ingot", 260.0);

        recordPrice("iron_ingot", 110.0);
        recordPrice("iron_ingot", 130.0);
        recordPrice("iron_ingot", 150.0);

        recordPrice("netherite_ingot", 2600.0);
        recordPrice("netherite_ingot", 2850.0);
    }

    public boolean shouldStopLoss(String itemId, double currentPrice) {
        Double buyPrice = lastBuyPrice.get(itemId);
        if (buyPrice == null || buyPrice <= 0.0) {
            return false;
        }

        double threshold = buyPrice * (1.0 - (stopLossPercent / 100.0));
        return currentPrice <= threshold;
    }

    public void checkTradeOpportunity() {
        if (!enabled) {
            return;
        }

        for (String itemId : priceHistory.keySet()) {
            double avg = getAveragePrice(itemId);
            double low = getLowestPrice(itemId);
            double high = getHighestPrice(itemId);

            if (avg <= 0.0 || low <= 0.0 || high <= 0.0) {
                continue;
            }

            double profit = ((high - low) / low) * 100.0;

            if (profit >= targetProfitPercent) {
                lastBuyPrice.put(itemId, low);
            }
        }
    }

    public String getStatsSummary() {
        return "Enabled=" + enabled +
                " | StopLoss=" + String.format("%.1f", stopLossPercent) + "%" +
                " | TargetProfit=" + String.format("%.1f", targetProfitPercent) + "%" +
                " | DiamondAvg=" + String.format("%.2f", getAveragePrice("diamond"));
    }

    public String getMarketOverview() {
        return "Diamond: buy " + String.format("%.2f", getLowestPrice("diamond")) +
                " / sell " + String.format("%.2f", getHighestPrice("diamond")) +
                " | Gold: buy " + String.format("%.2f", getLowestPrice("gold_ingot")) +
                " / sell " + String.format("%.2f", getHighestPrice("gold_ingot")) +
                " | Iron: buy " + String.format("%.2f", getLowestPrice("iron_ingot")) +
                " / sell " + String.format("%.2f", getHighestPrice("iron_ingot"));
    }
}
