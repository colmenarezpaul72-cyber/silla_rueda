package com.example.silladeruedas;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;

public class SillaDeRuedasMod implements ModInitializer {
    public static final String MOD_ID = "silladeruedas";
    public static EntityType<SillaEntity> SILLA;
    
    @Override 
    public void onInitialize() {
        SILLA = Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, "silla"),
            FabricEntityTypeBuilder.<SillaEntity>create(SpawnGroup.MISC, SillaEntity::new)
                .dimensions(EntityDimensions.fixed(0.9f, 1.0f)).trackRangeBlocks(8).trackedUpdateRate(3).build());
    }
    
    public static class SillaEntity extends Entity {
        public SillaEntity(EntityType<?> type, World world) { 
            super(type, world); 
        }
        
        @Override 
        public ActionResult interact(PlayerEntity player, Hand hand) {
            if (!getWorld().isClient && !hasPassenger()) {
                player.startRiding(this);
            }
            return ActionResult.SUCCESS;
        }
        
        // Método corregido con nombre válido en inglés
        private boolean hasPassenger() { 
            return getFirstPassenger() != null; 
        }
        
        @Override 
        protected void initDataTracker() {}
        
        @Override 
        protected void readCustomDataFromNbt(net.minecraft.nbt.NbtCompound nbt) {}
        
        @Override 
        protected void writeCustomDataToNbt(net.minecraft.nbt.NbtCompound nbt) {}
        
        @Override 
        public boolean damage(ServerWorld world, DamageSource source, float amount) {
            if (!getWorld().isClient) { 
                discard(); 
            }
            return true;
        }
        
        @Override 
        public void tick() { 
            super.tick(); 
            if (!hasPassengers() && !getWorld().isClient && age > 1200) {
                discard(); 
            }
        }
        
        @Override 
        protected void addPassenger(Entity passenger) { 
            super.addPassenger(passenger); 
        }
    }
}
