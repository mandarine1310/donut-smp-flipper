package de.mandarine.donut_smp_flipper.client.gui;

import de.mandarine.donut_smp_flipper.DonutSMPFlipper;
import de.mandarine.donut_smp_flipper.FlipperState;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MainFlipperScreen extends Screen {
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 30;
    private static final int SPACING = 38;
    private static final int PRIMARY_COLOR = 0xFF6B35;
    private static final int SECONDARY_COLOR = 0xFF8C42;
    private static final int HOVER_COLOR = 0xFFA857;
    private static final int BG_COLOR = 0x1A1A2E;
    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int ACCENT_COLOR = 0x00D9FF;

    public MainFlipperScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        clearChildren();

        int centerX = this.width / 2;
        int startY = 80;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("⚙ Auto-Flipper"),
                button -> this.client.setScreen(new AutoFlipperSettingsScreen(Text.literal("Auto-Flipper"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("📊 Preishistorie"),
                button -> this.client.setScreen(new PriceHistoryScreen(Text.literal("Preishistorie"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("🛡 Stop-Loss"),
                button -> this.client.setScreen(new StopLossScreen(Text.literal("Stop-Loss"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 2, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("🏪 Marktübersicht"),
                button -> this.client.setScreen(new MarketOverviewScreen(Text.literal("Marktübersicht"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 3, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("📈 Handelsverlauf"),
                button -> this.client.setScreen(new TradeHistoryScreen(Text.literal("Handelsverlauf"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 4, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("⚡ Einstellungen"),
                button -> this.client.setScreen(new SettingsScreen(Text.literal("Einstellungen"))))
                .dimensions(centerX - BUTTON_WIDTH / 2, startY + SPACING * 5, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("✕ Schließen"),
                button -> this.onClose())
                .dimensions(centerX - BUTTON_WIDTH / 2, this.height - 50, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Dark background
        context.fill(0, 0, this.width, this.height, BG_COLOR);

        // Header background
        context.fill(0, 0, this.width, 65, 0x0D0D1B);
        context.fill(0, 65, this.width, 66, ACCENT_COLOR);

        // Title
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("⬤ DONUT SMP FLIPPER ⬤"),
                this.width / 2,
                20,
                ACCENT_COLOR);

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal("Auto-Trading Menu"),
                this.width / 2,
                35,
                0xAAAAAA);

        // Status line
        String status = "Enabled: " + (FlipperState.getAutoFlipperManager().isEnabled() ? "✓" : "✗") +
                " | Stop-Loss: " + String.format("%.1f", FlipperState.getAutoFlipperManager().getStopLossPercent()) + "%";
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.literal(status),
                this.width / 2,
                this.height - 70,
                ACCENT_COLOR);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
