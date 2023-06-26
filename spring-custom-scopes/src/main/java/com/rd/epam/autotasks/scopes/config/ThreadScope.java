package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadScope implements Scope {

    private final ThreadLocal<Map<String, Object>> threadLocal =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    @Nullable
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> scopedObjects = threadLocal.get();
        Object object = scopedObjects.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            scopedObjects.put(name, object);
        }
        return object;
    }

    @Nullable
    @Override
    public Object remove(String name) {
        Map<String, Object> scopedObjects = threadLocal.get();
        return scopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // no-op, as the destruction of scoped objects is handled by the thread
    }

    @Nullable
    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Nullable
    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
