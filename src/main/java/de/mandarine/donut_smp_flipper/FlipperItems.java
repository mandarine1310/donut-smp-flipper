package de.mandarine.donut_smp_flipper;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class FlipperItems {
    private FlipperItems() {
    }

    public static final Item FLIPPER_TOOL = Registry.register(
            Registries.ITEM,
            Identifier.of(DonutSMPFlipper.MOD_ID, "flipper_tool"),
            new FlipperToolItem(new Item.Settings().maxCount(1))
    );

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
                    Identifier.of(DonutSMPFlipper.MOD_ID, "main_group"))
            .icon(() -> new ItemStack(FLIPPER_TOOL))
            .displayName(net.minecraft.text.Text.translatable("itemGroup.donut_smp_flipper.main_group"))
            .entries((displayContext, entries) -> entries.add(FLIPPER_TOOL))
            .build();

    public static void register() {
        // registration occurs in the static field initialization above.
    }
}
