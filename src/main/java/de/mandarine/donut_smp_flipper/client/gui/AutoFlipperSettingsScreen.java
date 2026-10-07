package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class AutoFlipperSettingsScreen extends Screen {
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 30;
    private static final int SPACING = 38;
    private static final int BG_COLOR = 0x1A1A2E;
    private static final int ACCENT_COLOR = 0x00D9FF;

    public AutoFlipperSettingsScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;
        int startY = 80;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal(FlipperState.getAutoFlipperManager().isEnabled() ? "✓ AKTIV" : "✗ INAKTIV"),
                button -> {
                    boolean newState = !FlipperState.getAutoFlipperManager().isEnabled();
                    FlipperState.getAutoFlipperManager().setEnabled(newState);
                    button.setMessage(Text.literal(newState ? "✓ AKTIV" : "✗ INAKTIV"));
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Zielgewinn: " + String.format("%.1f", FlipperState.getAutoFlipperManager().getTargetProfitPercent()) + "%"),
                button -> {
                    double current = FlipperState.getAutoFlipperManager().getTargetProfitPercent();
                    double next = current + 1.0;
                    if (next > 50.0) next = 5.0;
                    FlipperState.getAutoFlipperManager().setTargetProfitPercent(next);
                    button.setMessage(Text.literal("Zielgewinn: " + String.format("%.1f", next) + "%"));
                })
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("+ Maximale Investition"),
                button -> {})
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("+ Auto-Sell aktivieren"),
                button -> {})
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("← Zurück"),
                button -> this.client.setScreen(new MainFlipperScreen(Text.literal("Donut SMP Flipper"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, this.height - 50, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, BG_COLOR);
        context.fill(0, 0, this.width, 65, 0x0D0D1B);
        context.fill(0, 65, this.width, 66, ACCENT_COLOR);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("⚙ AUTO-FLIPPER EINSTELLUNGEN"), this.width / 2, 20, ACCENT_COLOR);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Passen Sie die Auto-Flipper-Parameter an"), this.width / 2, 40, 0xAAAAAA);

        super.render(context, mouseX, mouseY, delta);
    }
}
