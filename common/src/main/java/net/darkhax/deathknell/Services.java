package net.darkhax.deathknell;

import java.util.ServiceLoader;

public class Services {

    public static IHelper HELPER = load(IHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        return loadedService;
    }
}