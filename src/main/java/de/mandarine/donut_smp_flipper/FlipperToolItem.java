package de.mandarine.donut_smp_flipper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlipperToolItem extends Item {
    public FlipperToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient()) {
            MinecraftClient.getInstance().setScreen(new FlippingScreen(Text.literal("Donut SMP Flipper")));
        }

        return TypedActionResult.consume(stack);
    }
}
