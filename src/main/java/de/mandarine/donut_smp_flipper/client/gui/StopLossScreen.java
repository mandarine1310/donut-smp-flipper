package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class StopLossScreen extends Screen {
    public StopLossScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;
        int startY = 50;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Stop-Loss: " + String.format("%.1f", FlipperState.getAutoFlipperManager().getStopLossPercent()) + "%"),
                button -> {
                    double current = FlipperState.getAutoFlipperManager().getStopLossPercent();
                    double next = current + 1.0;
                    if (next > 25.0) next = 1.0;
                    FlipperState.getAutoFlipperManager().setStopLossPercent(next);
                    button.setMessage(Text.literal("Stop-Loss: " + String.format("%.1f", next) + "%"));
                })
                .dimensions(centerX - 100, startY, 200, 25)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Stop-Loss aktivieren"),
                button -> {})
                .dimensions(centerX - 100, startY + 35, 200, 25)
                .build());

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

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Stop-Loss Manager"), this.width / 2, 15, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Wenn der Preis um " + String.format("%.1f", FlipperState.getAutoFlipperManager().getStopLossPercent()) + "% fällt, wird verkauft."), this.width / 2, 100, 0xAAAA00);
    }
}
