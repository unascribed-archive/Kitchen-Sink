package com.gameminers.kitchensink;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = "KitchenSink", name = "Kitchen Sink", version = "1.1.2", useMetadata = true)
public class KitchenSinkMod {
	@Instance("KitchenSink") public static KitchenSinkMod	inst;
	public static final Logger								LOG	= LogManager.getLogger("KitchenSink");
}
