package com.happy.lib_base.autoservice;

import java.util.ServiceLoader;

public final class HappyServiceLoader {
    private HappyServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
