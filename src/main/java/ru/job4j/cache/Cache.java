package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("Versions are not equals");
            }
            Base temp = new Base(key, value.getVersion() + 1);
            temp.setName(model.getName());
            return temp;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}