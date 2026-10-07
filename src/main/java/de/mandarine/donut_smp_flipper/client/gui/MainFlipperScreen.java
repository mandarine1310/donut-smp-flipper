package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.DonutSMPFlipper;
import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MainFlipperScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 25;
    private static final int SPACING = 30;

    public MainFlipperScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;
        int startY = 50;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Auto-Flipper Einstellungen"),
                button -> this.client.setScreen(new AutoFlipperSettingsScreen(Text.literal("Auto-Flipper"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Preishistorie & Statistik"),
                button -> this.client.setScreen(new PriceHistoryScreen(Text.literal("Preishistorie"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Stop-Loss Manager"),
                button -> this.client.setScreen(new StopLossScreen(Text.literal("Stop-Loss"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Marktübersicht"),
                button -> this.client.setScreen(new MarketOverviewScreen(Text.literal("Marktübersicht"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Handelsverlauf"),
                button -> this.client.setScreen(new TradeHistoryScreen(Text.literal("Handelsverlauf"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 4, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Einstellungen"),
                button -> this.client.setScreen(new SettingsScreen(Text.literal("Einstellungen"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 5, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Schließen"),
                button -> this.onClose())
                .dimensions(centerX - BUTTON_WIDTH / 2, this.height - 40, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("≡ Donut SMP Flipper"),
                this.width / 2,
                15,
                0xFFFFFF);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
