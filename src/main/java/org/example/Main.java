package org.example;

import jakarta.persistence.EntityManagerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ChisteDownloader cDown = ChisteDownloader.getInstance();
        Scanner sc = new Scanner(System.in);
        int numeroChistes = sc.nextInt();
        cDown.setNumChistes(numeroChistes);
        Thread th = new Thread(cDown);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}