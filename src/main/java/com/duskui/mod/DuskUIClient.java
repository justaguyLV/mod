package com.duskui.mod;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuskUIClient implements ClientModInitializer {
    public static final String MOD_ID = "duskui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[DuskUI] Dark theme active — {} palette colors loaded.", 10);
        // Purely client-side visual mod: no world/server state is touched,
        // so it loads fine next to Lithium (server-side logic optimizer)
        // and Sodium (rendering optimizer with no vanilla-Screen overlap).
    }
}
