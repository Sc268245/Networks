package io.github.sefiraat.networks.network.barrel;


import net.guizhanss.infinityexpansion2.implementation.items.storage.StorageCache;
import io.github.sefiraat.networks.network.stackcaches.BarrelIdentity;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class InfinityBarrel extends BarrelIdentity {

    @Nonnull
    private final StorageCache cache;

    @ParametersAreNonnullByDefault
    public InfinityBarrel(Location location, ItemStack itemStack, int amount, StorageCache cache) {
        super(location, itemStack, amount, BarrelType.INFINITY);
        this.cache = cache;
    }

    @Nullable
    @Override
    public ItemStack requestItem(@Nonnull ItemRequest itemRequest) {
        BlockMenu blockMenu = BlockStorage.getInventory(this.getLocation());
        return blockMenu == null ? null : blockMenu.getItemInSlot(this.getOutputSlot());
    }

    @Override
    public void depositItemStack(ItemStack[] itemsToDeposit) {
        for (ItemStack item : itemsToDeposit) {
            if (this.cache.matches(item)) {
                final int leftover = this.cache.increaseAmount$InfinityExpansion2(item.getAmount());
                item.setAmount(leftover);
            }
        }
    }


    @Override
    public int getInputSlot() {
        return 10;
    }

    @Override
    public int getOutputSlot() {
        return 16;
    }
}
