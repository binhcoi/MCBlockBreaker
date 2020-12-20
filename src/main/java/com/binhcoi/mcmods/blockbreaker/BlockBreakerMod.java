package com.binhcoi.mcmods.blockbreaker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockBreakerMod implements ModInitializer{

    public static final String MOD_ID = "blockbreaker";
    public static final Block BLOCK_BREAKER_BLOCK = new BlockBreakerBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.DISPENSER));
    @Override
    public void onInitialize() {        
        System.out.println("Hello world! BlockBreaker");
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"block_breaker_block"),BLOCK_BREAKER_BLOCK);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"block_breaker_block"),new BlockItem(BLOCK_BREAKER_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
    
}
