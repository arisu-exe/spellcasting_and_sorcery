package io.github.fallOut015.spellcasting_and_sorcery.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class BloodFlameBladeItem extends BloodBladeItem {
    public BloodFlameBladeItem(Properties builder) {
        super(builder);
    }

    @Override
    public void bonusEffect(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setSecondsOnFire(5);
    }
}