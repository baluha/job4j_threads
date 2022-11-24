package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (!accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized boolean update(Account account) {
        if (accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(int id) {
        if (accounts.containsKey(id)) {
            accounts.remove(id);
            return true;
        }
        return false;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.of(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account src = getById(fromId).orElseThrow(() -> new IllegalArgumentException("Account id " + fromId + " not found"));
        Account dest = getById(fromId).orElseThrow(() -> new IllegalArgumentException("Account id " + toId + " not found"));
        if (src.amount() >= amount) {
            update(new Account(src.id(), src.amount() - amount));
            update(new Account(dest.id(), dest.amount() + amount));
            return true;
        }
        return false;
    }
}
