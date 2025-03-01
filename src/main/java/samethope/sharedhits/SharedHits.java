package samethope.sharedhits;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedHits implements ModInitializer {
	public static final String MOD_ID = "shared-hits";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static GameRules.Key<GameRules.BooleanRule> SHARE_HITS;

	@Override
	public void onInitialize() {
		SHARE_HITS = GameRuleRegistry.register("shareHits", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
//		LOGGER.info("Shared hits initialized!");
	}
}