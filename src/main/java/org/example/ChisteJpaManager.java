package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class ChisteJpaManager {

    public static final String CHISTE_H2 = "jokesH2";

    private static final Map<String, EntityManagerFactory> instancies = new HashMap<>();

    private ChisteJpaManager(){}

    private static boolean isEntityManagerFactoryClosed(String unidadPersistencia) {
        return !instancies.containsKey(unidadPersistencia) || instancies.get(unidadPersistencia)== null ||
                !instancies.get(unidadPersistencia).isOpen();
    }

    public static EntityManagerFactory getEntityManagerFactory(String unidadPersistencia){
        if (isEntityManagerFactoryClosed(unidadPersistencia)){
            synchronized (ChisteJpaManager.class){
                if (isEntityManagerFactoryClosed(unidadPersistencia)){
                    instancies.put(unidadPersistencia, Persistence.createEntityManagerFactory(unidadPersistencia));
                }
            }
        }

        return instancies.get(unidadPersistencia);
    }

    public static EntityManager getEntityManager(String unidadPersistencia){
        return getEntityManagerFactory(unidadPersistencia).createEntityManager();
    }

    public static void close (String nombreUnidadPersistencia){
        if (instancies.containsKey(nombreUnidadPersistencia)){
            instancies.get(nombreUnidadPersistencia).close();
            instancies.remove(nombreUnidadPersistencia);
        }
    }


}
