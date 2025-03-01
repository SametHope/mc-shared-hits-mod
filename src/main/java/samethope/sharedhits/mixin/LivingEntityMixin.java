package samethope.sharedhits.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samethope.sharedhits.SharedHits;

/// LivingEntity is preferred over ServerPlayerEntity or PlayerEntity
/// this is to make sure all vanilla condition checks such as shields are done before hit is shared
@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    private static boolean processingSharedHit = false;

    @Inject(at = @At("TAIL"), method = "damage")
    private void damageOtherPlayers(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        // This is ugly
        if (((Object) this) instanceof ServerPlayerEntity player) {}
		else return;
        if (processingSharedHit) return; // Damage cause is someone else sharing their damage
        if (!world.getServer().getGameRules().getBoolean(SharedHits.SHARE_HITS)) return;

        processingSharedHit = true;
        for (var otherPlayer : world.getServer().getPlayerManager().getPlayerList()) {
            if (otherPlayer.equals(player)) continue;
            if (otherPlayer.isCreative() || otherPlayer.isSpectator()) continue;
            otherPlayer.damage(world, source, amount);
        }
        processingSharedHit = false;
    }
}