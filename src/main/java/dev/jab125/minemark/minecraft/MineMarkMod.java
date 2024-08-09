package dev.jab125.minemark.minecraft;

//? if neoforge
/*import net.neoforged.fml.common.Mod;*/
//? if forge
/*import net.minecraftforge.fml.common.Mod;*/
import org.jetbrains.annotations.ApiStatus;

// Forge and NeoForge want a mod class
@ApiStatus.Internal
//? if forge || neoforge
/*@Mod(MineMarkMod.MOD_ID)*/
public class MineMarkMod {
	public static final String MOD_ID = "mpuc_minemark";
	public MineMarkMod() {
	}
}
