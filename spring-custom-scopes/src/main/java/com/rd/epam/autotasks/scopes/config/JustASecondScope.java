package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class JustASecondScope implements Scope {

    private Map<String, Object> beans = new HashMap<>();
    private Map<String, Instant> expirationTimes = new HashMap<>();

    @Nullable
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        synchronized (this.beans) {
            if (!beans.containsKey(name) || Instant.now().isAfter(expirationTimes.get(name))) {
                beans.put(name, objectFactory.getObject());
                expirationTimes.put(name, Instant.now().plusSeconds(1));
            }
            return beans.get(name);
        }
    }

    @Nullable
    @Override
    public Object remove(String name) {
        synchronized (this.beans) {
            expirationTimes.remove(name);
            return beans.remove(name);
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // no-op, as beans expire automatically after one second
    }

    @Nullable
    @Override
    public Object resolveContextualObject(String key) {
        // no-op
        return null;
    }

    @Nullable
    @Override
    public String getConversationId() {
        // no-op, as the scope is not conversation-based
        return null;
    }
}
