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
        int startY = 40;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal(manager.isEnabled() ? "Disable Auto-Flipping" : "Enable Auto-Flipping"),
                button -> {
                    manager.setEnabled(!manager.isEnabled());
                    button.setMessage(Text.literal(manager.isEnabled() ? "Disable Auto-Flipping" : "Enable Auto-Flipping"));
                })
                .dimensions(centerX - 100, startY, 200, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Set Stop-Loss: " + manager.getStopLossPercent() + "%"),
                button -> {
                    manager.setStopLossPercent(manager.getStopLossPercent() + 1.0);
                    if (manager.getStopLossPercent() > 30.0) {
                        manager.setStopLossPercent(0.5);
                    }
                    button.setMessage(Text.literal("Set Stop-Loss: " + manager.getStopLossPercent() + "%"));
                })
                .dimensions(centerX - 100, startY + 30, 200, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Simulate Buy Best"),
                button -> manager.simulateBestBuy())
                .dimensions(centerX - 100, startY + 60, 200, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Simulate Sell Best"),
                button -> manager.simulateBestSell())
                .dimensions(centerX - 100, startY + 90, 200, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Refresh Stats"),
                button -> {
                    // no-op, stats update happens on render
                })
                .dimensions(centerX - 100, startY + 120, 200, 20)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal("Donut SMP Flipper"),
                this.width / 2,
                10,
                0xFFFFFF);

        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal(manager.getStatsSummary()),
                this.width / 2,
                this.height - 80,
                0xAAFFAA);
    }
}
