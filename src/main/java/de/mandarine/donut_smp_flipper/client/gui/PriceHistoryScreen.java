package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PriceHistoryScreen extends Screen {
    public PriceHistoryScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("← Zurück"),
                button -> this.client.setScreen(new MainFlipperScreen(Text.literal("Donut SMP Flipper"))))
                .dimensions(centerX - 100, this.height - 40, 200, 25)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Preishistorie & Statistik"), this.width / 2, 15, 0xFFFFFF);

        int y = 50;
        context.drawTextWithShadow(this.textRenderer, Text.literal("Diamond: " + FlipperState.getAutoFlipperManager().getMarketOverview()), 20, y, 0xAAAAAA);
        y += 20;
        context.drawTextWithShadow(this.textRenderer, Text.literal("Durchschnittspreis: " + String.format("%.2f", FlipperState.getAutoFlipperManager().getAveragePrice("diamond"))), 20, y, 0xFFFFFF);
        y += 20;
        context.drawTextWithShadow(this.textRenderer, Text.literal("Min: " + String.format("%.2f", FlipperState.getAutoFlipperManager().getLowestPrice("diamond")) + " | Max: " + String.format("%.2f", FlipperState.getAutoFlipperManager().getHighestPrice("diamond"))), 20, y, 0xFFFFFF);
    }
}
