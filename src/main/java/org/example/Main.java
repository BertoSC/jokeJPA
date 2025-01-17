package org.example;

import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory enf = ChisteJpaManager.getEntityManagerFactory(ChisteJpaManager.CHISTE_H2);

        System.out.println("Hello world!");
    }
}