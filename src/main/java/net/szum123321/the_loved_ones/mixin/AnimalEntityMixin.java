/*
    A friendly fire for Fabric
    Copyright (C) 2020 Szum123321

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package net.szum123321.the_loved_ones.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.szum123321.the_loved_ones.TheLovedOnes;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PassiveEntity {
    protected AnimalEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> ci) {
        if(!this.world.isClient() && (Object)this instanceof TameableEntity) {
            TameableEntity it = (TameableEntity)(Object)this;

            if(it.isTamed()) {
                if(source.getAttacker() instanceof PlayerEntity) {
                    if(getServer().isSinglePlayer()) {
                        //Check if attacking source is owner or pets damage on LAN is disabled
                        if (source.getAttacker().getUuid() == it.getOwnerUuid() || !TheLovedOnes.config.petsDamageOnLAN)
                            ci.setReturnValue(false);
                    } else {
                        //Check if attacking source is owner
                        if (source.getAttacker().getUuid() == it.getOwnerUuid())
                            ci.setReturnValue(false);

                        // Check if PVP is enabled
                        if(getServer().isPvpEnabled()) {
                            if (!TheLovedOnes.config.petsDamagePVP)
                                ci.setReturnValue(false);
                        } else {
                            if (!TheLovedOnes.config.petsDamageNoPVP)
                                ci.setReturnValue(false);
                        }
                    }
                }
            }
        }
    }
}
