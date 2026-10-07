package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MarketOverviewScreen extends Screen {
    public MarketOverviewScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Preise aktualisieren"),
                button -> FlipperState.getAutoFlipperManager().applySampleMarketData())
                .dimensions(centerX - 100, 50, 200, 25)
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

        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Marktübersicht"), this.width / 2, 15, 0xFFFFFF);

        String overview = FlipperState.getAutoFlipperManager().getMarketOverview();
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(overview), this.width / 2, 100, 0x00FF00);
    }
}
