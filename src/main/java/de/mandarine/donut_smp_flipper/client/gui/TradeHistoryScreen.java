package de.mandarine.donut_smp_flipper.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TradeHistoryScreen extends Screen {
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
                .dimensions(centerX - 100, this.height - 40, 200, 25)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Handelsverlauf"), this.width / 2, 15, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Keine Handelshistorie vorhanden"), this.width / 2, 100, 0xAAAAAA);
    }
}
