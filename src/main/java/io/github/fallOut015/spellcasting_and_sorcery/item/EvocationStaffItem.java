package io.github.fallOut015.spellcasting_and_sorcery.item;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class EvocationStaffItem extends Item implements IVanishable {
    public EvocationStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getCooldownTracker().setCooldown(this, 40);
        RayTraceResult result = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.ANY);

        double d0 = Math.min(result.getHitVec().getY(), playerIn.getPosY());
        double d1 = Math.max(result.getHitVec().getY(), playerIn.getPosY()) + 1.0D;
        float f = (float) MathHelper.atan2(result.getHitVec().getZ() - playerIn.getPosZ(), result.getHitVec().getX() - playerIn.getPosX());

        int snappingLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentsTwo.SNAPPING.get(), playerIn.getHeldItem(handIn));

        if (playerIn.getDistanceSq(result.getHitVec()) < 9.0D) {
            for(int i = 0; i < 5; ++i) {
                float f1 = f + (float)i * (float)Math.PI * 0.4F;
                this.spawnFangs(playerIn.getPosX() + (double)MathHelper.cos(f1) * 1.5D, playerIn.getPosZ() + (double)MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0, playerIn, snappingLevel);
            }

            for(int k = 0; k < 8; ++k) {
                float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                this.spawnFangs(playerIn.getPosX() + (double)MathHelper.cos(f2) * 2.5D, playerIn.getPosZ() + (double)MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3, playerIn, snappingLevel);
            }
        } else {
            for(int l = 0; l < 16; ++l) {
                double d2 = 1.25D * (double)(l + 1);
                int j = 1 * l;
                this.spawnFangs(playerIn.getPosX() + (double)MathHelper.cos(f) * d2, playerIn.getPosZ() + (double)MathHelper.sin(f) * d2, d0, d1, f, j, playerIn, snappingLevel);
            }
        }

        playerIn.getHeldItem(handIn).damageItem(10, playerIn, playerEntity -> playerEntity.sendBreakAnimation(handIn == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND));

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    private void spawnFangs(double d1, double d3, double d5, double d7, float f9, int i10, PlayerEntity playerIn, int snappingLevel) {
        // Almost a direct copy of EvokerEntity::spawnFangs, or at least, it was.

        BlockPos blockpos = new BlockPos(d1, d7, d3);
        boolean flag = false;
        double d0 = 0.0D;

        while(true) {
            BlockPos blockpos1 = blockpos.down();
            BlockState blockstate = playerIn.world.getBlockState(blockpos1);
            if (blockstate.isSolidSide(playerIn.world, blockpos1, Direction.UP)) {
                if (!playerIn.world.isAirBlock(blockpos)) {
                    BlockState blockstate1 = playerIn.world.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(playerIn.world, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.getEnd(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.down();
            if (blockpos.getY() < MathHelper.floor(d5) - 1) {
                break;
            }
        }

        if (flag) {
            playerIn.world.addEntity(new EvocationFangsEntity(playerIn.world, d1, (double) blockpos.getY() + d0, d3, f9, i10, playerIn, snappingLevel));
        }
    }
}