//package com.example.examplemod;
//
////? if fabric
//import net.fabricmc.loader.api.FabricLoader;
//import net.minecraft.client.Minecraft;
////? if forge {
///*import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.common.Mod;
//*///?} if neoforge {
///*import net.neoforged.fml.ModList;
//import net.neoforged.fml.common.Mod;
//*///?}
////? if >=1.21 {
///*import net.minecraft.client.gui.screens.options.OptionsScreen;
//*///?} else
//import net.minecraft.client.gui.screens.OptionsScreen;
////? if >=1.18 {
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
////?} else {
///*import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//*///?}
//import java.util.NoSuchElementException;
//
////? if forge || neoforge
///*@Mod(ExampleMod.MOD_ID)*/
//public class ExampleMod {
//    public static final String MOD_ID = "mpuc_minemark";
//	//? if >=1.18
//    public static final Logger LOGGER = LoggerFactory.getLogger(getDisplayName(MOD_ID));
//	//? if <1.18
//	/*public static final Logger LOGGER = LogManager.getLogger(getDisplayName(MOD_ID));*/
//
//    private static String getDisplayName(String modId) {
//        //? if fabric
//        return FabricLoader.getInstance().getModContainer(modId).orElseThrow(() -> new NoSuchElementException("No value present")).getMetadata().getName();
//        //? if forge
//        /*return ModList.get().getModContainerById(modId).orElseThrow(() -> new NoSuchElementException("No value present")).getModInfo().getDisplayName();*/
//        //? if neoforge
//        /*return ModList.get().getModContainerById(modId).orElseThrow(() -> new NoSuchElementException("No value present")).getModInfo().getDisplayName();*/
//    }
//
//    //? if forge || neoforge {
//    /*public ExampleMod() {
//        onInitialize();
//    }
//    *///?}
//
//    public void onInitialize() {
//        //? if fabric
//        String loader = "Fabric";
//        //? if forge
//        /*String loader = "Forge";*/
//        //? if neoforge
//        /*String loader = "NeoForge";*/
//        LOGGER.info("Hello {} World!", loader);
//        // This is a test to assert that the access widener works.
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(true) {
//					synchronized (Minecraft.getInstance()) {
//						if (Minecraft.getInstance().screen instanceof OptionsScreen) {
//							Minecraft.getInstance().execute(() ->
//							Minecraft.getInstance().setScreen(new MarkdownTestGui()));
//							return;
//						}
//					}
//				}
//			}
//		}).start();
//    }
//}
