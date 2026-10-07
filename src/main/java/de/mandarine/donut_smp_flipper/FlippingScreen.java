package de.mandarine.donut_smp_flipper;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class FlippingScreen extends Screen {
    private final AutoFlipperManager manager = FlipperState.getAutoFlipperManager();

    public FlippingScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        int centerX = this.width / 2;
        int startY = 36;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal(manager.isEnabled() ? "Disable Auto-Flipping" : "Enable Auto-Flipping"),
                button -> {
                    manager.setEnabled(!manager.isEnabled());
                    button.setMessage(Text.literal(manager.isEnabled() ? "Disable Auto-Flipping" : "Enable Auto-Flipping"));
                })
                .dimensions(centerX - 110, startY, 220, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Stop-Loss: " + manager.getStopLossPercent() + "%"),
                button -> {
                    double next = manager.getStopLossPercent() + 1.0;
                    if (next > 25.0) {
                        next = 2.0;
                    }
                    manager.setStopLossPercent(next);
                    button.setMessage(Text.literal("Stop-Loss: " + manager.getStopLossPercent() + "%"));
                })
                .dimensions(centerX - 110, startY + 28, 220, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Add Sample Prices"),
                button -> manager.applySampleMarketData())
                .dimensions(centerX - 110, startY + 56, 220, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Auto-Check Trade"),
                button -> manager.checkTradeOpportunity())
                .dimensions(centerX - 110, startY + 84, 220, 20)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Donut SMP Flipper"), this.width / 2, 10, 0xFFFFFF);

        String summary = manager.getStatsSummary();
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(summary), this.width / 2, this.height - 150, 0xA9F5A9);

        String market = manager.getMarketOverview();
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(market), this.width / 2, this.height - 120, 0xFFFFFF);
    }
}
