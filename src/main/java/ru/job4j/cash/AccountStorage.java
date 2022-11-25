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
        return accounts.put(account.id, account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id, account) != null;
    }

    public synchronized boolean delete(int id) {
            return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return accounts.get(id) != null
                ? Optional.of(accounts.get(id)) : Optional.empty();

    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account src = getById(fromId).orElseThrow(() -> new IllegalArgumentException("Account id " + fromId + " not found"));
        Account dest = getById(toId).orElseThrow(() -> new IllegalArgumentException("Account id " + toId + " not found"));
        if (src.amount >= amount) {
            update(new Account(src.id, src.amount - amount));
            update(new Account(dest.id, dest.amount + amount));
            return true;
        }
        return false;
    }
}
