package samethope.sharedhits.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import samethope.sharedhits.SharedHits;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
	@Unique
	private static boolean processing = false;

	@Inject(at = @At("TAIL"), method = "damage")
	private void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (processing) return; // Damage cause is someone else sharing their damage
		var server = world.getServer();
		if (!server.getGameRules().getBoolean(SharedHits.SHARE_HITS)) return;

		var player = (ServerPlayerEntity) (Object) this;
		processing = true;
		for (var otherPlayer : server.getPlayerManager().getPlayerList()) {
			if (otherPlayer.equals(player)) continue;
			otherPlayer.damage(world, source, amount);
		}
		processing = false;
	}
}