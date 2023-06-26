package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ThreeTimesScope implements Scope {


    private final Map<String, Object> beans = new HashMap<>();
    private final Map<String, Integer> countMap = new HashMap<>();

    @Override
    public Object get(@NonNull String name, @NonNull ObjectFactory<?> objectFactory) {
        synchronized (this.beans) {
            if (this.beans.containsKey(name)) {
                Integer count = this.countMap.get(name);
                if (count == null) {
                    count = 0;
                }
                if (count < 2) {
                    count++;
                    this.countMap.put(name, count);
                    return this.beans.get(name);
                }
            }
            Object bean = objectFactory.getObject();
            this.beans.put(name, bean);
            this.countMap.put(name, 0);
            return bean;
        }
    }

    @Override
    public Object remove(@NonNull String name) {
        synchronized (this.beans) {
            this.countMap.remove(name);
            return this.beans.remove(name);
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // not implemented
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

}
