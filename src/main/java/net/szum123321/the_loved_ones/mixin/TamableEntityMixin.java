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

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TameableEntity.class)
public abstract class TamableEntityMixin extends AnimalEntity {

    protected TamableEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Environment(EnvType.SERVER)
    @Override
    public boolean damage(DamageSource source, float amount){
        if(!this.world.isClient){
            TameableEntity it = (TameableEntity)(Object)this;

            if(it.getOwner() != null){
                if(source.getAttacker() instanceof PlayerEntity){
                    if(source.getAttacker() == it.getOwner()){
                        return false;
                    }
                }
            }
        }

        return super.damage(source, amount);
    }
}
