package com.example.demo.game.utils;

public class Utils {

    /**
     * Fonction pour attendre avant d'afficher un message
     * pour rendre l'utilisation meilleur
     *
     * @param number temp en milliseconde
     */
    public static void waitSecond(int number) {
        try {
            Thread.sleep(number);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
