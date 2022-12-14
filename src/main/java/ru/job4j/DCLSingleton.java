package ru.job4j;

public class DCLSingleton {

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        DCLSingleton localInst = inst;
        if (localInst == null) {
            synchronized (DCLSingleton.class) {
                localInst = inst;
                if (localInst == null) {
                    inst = localInst;
                    localInst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}