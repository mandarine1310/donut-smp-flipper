package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class AutoFlipperSettingsScreen extends Screen {
    private final Screen parent;

    public AutoFlipperSettingsScreen(Text title) {
        super(title);
        this.parent = null;
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;
        int startY = 50;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal(FlipperState.getAutoFlipperManager().isEnabled() ? "✓ Auto-Flipper AKTIV" : "✗ Auto-Flipper INAKTIV"),
                button -> {
                    boolean newState = !FlipperState.getAutoFlipperManager().isEnabled();
                    FlipperState.getAutoFlipperManager().setEnabled(newState);
                    button.setMessage(Text.literal(newState ? "✓ Auto-Flipper AKTIV" : "✗ Auto-Flipper INAKTIV"));
                })
                .dimensions(centerX - 100, startY, 200, 25)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Zielgewinn erhöhen (aktuell: " + String.format("%.1f", FlipperState.getAutoFlipperManager().getTargetProfitPercent()) + "%)"),
                button -> {
                    double current = FlipperState.getAutoFlipperManager().getTargetProfitPercent();
                    double next = current + 1.0;
                    if (next > 50.0) next = 5.0;
                    FlipperState.getAutoFlipperManager().setTargetProfitPercent(next);
                    button.setMessage(Text.literal("Zielgewinn erhöhen (aktuell: " + String.format("%.1f", next) + "%)"));
                })
                .dimensions(centerX - 100, startY + 35, 200, 25)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Maximale Investition pro Item"),
                button -> {})
                .dimensions(centerX - 100, startY + 70, 200, 25)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Auto-Sell aktivieren"),
                button -> {})
                .dimensions(centerX - 100, startY + 105, 200, 25)
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

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Auto-Flipper Einstellungen"), this.width / 2, 15, 0xFFFFFF);
    }
}
