package com.gameminers.kitchensink;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;


@Mod(modid = "KitchenSink", name = "Kitchen Sink", version = "0.2", useMetadata = true)
public class KitchenSinkMod {
	@Instance("KitchenSink") public static KitchenSinkMod	inst;
	public static final Logger								LOG	= LogManager.getLogger("KitchenSink");
}
