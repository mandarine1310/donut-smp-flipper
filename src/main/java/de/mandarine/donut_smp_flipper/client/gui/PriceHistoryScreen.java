package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PriceHistoryScreen extends Screen {
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BG_COLOR = 0x1A1A2E;
    private static final int ACCENT_COLOR = 0x00D9FF;

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
                .dimensions(centerX - BUTTON_WIDTH / 2, this.height - 50, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, BG_COLOR);
        context.fill(0, 0, this.width, 65, 0x0D0D1B);
        context.fill(0, 65, this.width, 66, ACCENT_COLOR);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("📊 PREISHISTORIE & STATISTIK"), this.width / 2, 20, ACCENT_COLOR);

        int y = 100;
        String[] items = {"diamond", "gold_ingot", "iron_ingot", "netherite_ingot"};
        String[] icons = {"💎", "🟡", "⚪", "⬛"};

        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            String icon = icons[i];

            context.drawTextWithShadow(this.textRenderer, Text.literal(icon + " " + item.replace("_", " ").toUpperCase()), 30, y, ACCENT_COLOR);
            context.drawTextWithShadow(this.textRenderer,
                    Text.literal("   Ø " + String.format("%.2f", FlipperState.getAutoFlipperManager().getAveragePrice(item)) +
                            " | Min: " + String.format("%.2f", FlipperState.getAutoFlipperManager().getLowestPrice(item)) +
                            " | Max: " + String.format("%.2f", FlipperState.getAutoFlipperManager().getHighestPrice(item))),
                    30, y + 15, 0xFFFFFF);
            y += 40;
        }

        super.render(context, mouseX, mouseY, delta);
    }
}
