package io.github.fallOut015.spellcasting_and_sorcery.server;

import io.github.fallOut015.spellcasting_and_sorcery.MainSpellcastingAndSorcery;
import io.github.fallOut015.spellcasting_and_sorcery.item.DoubleJumpBootsItem;
import io.github.fallOut015.spellcasting_and_sorcery.item.ItemsSpellcastingAndSorcery;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class JumpPacketHandler extends PacketHandler {
    public static final int JUMP_ID = PacketHandler.getNewID();

    public JumpPacketHandler() {
        super(JUMP_ID);
    }

    public static void encoder(JumpPacketHandler msg, PacketBuffer buffer) {
        MainSpellcastingAndSorcery.LOGGER.debug("Encoding {} to {}", msg, buffer);
    }
    public static JumpPacketHandler decoder(PacketBuffer buffer) {
        MainSpellcastingAndSorcery.LOGGER.debug("Decoding {}", buffer);
        return new JumpPacketHandler();
    }
    public static void handle(JumpPacketHandler msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            MainSpellcastingAndSorcery.LOGGER.debug("Recieved jump packet on server");

            ServerPlayerEntity sender = ctx.get().getSender();
            if(sender != null) {
                if(!sender.isOnGround() && !sender.isInWater() && sender.getItemBySlot(EquipmentSlotType.FEET).getItem() == ItemsSpellcastingAndSorcery.DOUBLE_JUMP_BOOTS.get()) {
                    ItemStack bootstack = sender.getItemBySlot(EquipmentSlotType.FEET);
                    CompoundNBT tag = bootstack.getOrCreateTag();

                    if(!tag.contains("jumps")) {
                        tag.putInt("jumps", 0);
                    }
                    if(tag.contains("jumps") && tag.getInt("jumps") < DoubleJumpBootsItem.getJumpLimit(bootstack)) {
                        tag.putInt("jumps", tag.getInt("jumps") + 1);

                        sender.setDeltaMovement(sender.getDeltaMovement().x(), 0, sender.getDeltaMovement().z());
                        sender.jumpFromGround();
                        sender.hurtMarked = true;

                        bootstack.hurtAndBreak(1, sender, playerEntity -> playerEntity.broadcastBreakEvent(EquipmentSlotType.FEET));

                        for(int i = 0; i < 8; ++i) {
                            double xmod = Math.cos(((360d / 8d) * i) * (180d / Math.PI));
                            double zmod = Math.sin(((360d / 8d) * i) * (180d / Math.PI));
                            sender.level.addParticle(ParticleTypes.CLOUD, sender.getX() + xmod, sender.getY(), sender.getZ() + zmod, zmod, 0.1, xmod);
                        }

                        // set bootstack tag to tag?

                        // let the player know how many uses they have left (xp bar?) and let the player know when the last jump is used
                        // maybe give the boots a passive glow or particle effect or something that goes away when all of the extra jumps are expended

                        // power down sound effect when the last use is expended
                        // power up sound effect when you regain uses
                        // have a glow around the boots when there are uses
                        // do something to show how many uses there are
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}