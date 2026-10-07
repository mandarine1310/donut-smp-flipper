package de.mandarine.donut_smp_flipper.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TradeHistoryScreen extends Screen {
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BG_COLOR = 0x1A1A2E;
    private static final int ACCENT_COLOR = 0x00D9FF;

    public TradeHistoryScreen(Text title) {
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

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("📈 HANDELSVERLAUF"), this.width / 2, 20, ACCENT_COLOR);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Noch keine Handelshistorie vorhanden"), this.width / 2, 150, 0xAAAAAA);

        super.render(context, mouseX, mouseY, delta);
    }
}
