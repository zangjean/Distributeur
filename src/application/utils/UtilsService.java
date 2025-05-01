package application.utils;

import application.model.distrib.panier.ProductForPanier;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public abstract class UtilsService {

    private static ProgressBar progressBar = new ProgressBar();
    private static ProgressIndicator progressIndicator = new ProgressIndicator();
    private static int mls = 0;
    private static boolean isEnd = false;

    static Service<Void> service = new Service<>() {
        @Override
        protected Task<Void> createTask() {
            Task<Void> createTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    if (isCancelled()){
                        return null;
                    }
                    for (int i = 0; i < mls; i++){
                        waitOneTime();
                        // Vous pouvez essayer updateProgress(i+1, mls) pour que la progression atteigne 1 à la fin
                        updateProgress(i + 1, mls);
                        System.out.println("DANS LA TASK -> i : " + i);
                        //System.out.println("Voici get progress: "+getProgress());
                    }
                    return null;
                }
            };
            return createTask;
        }
    };

    public static void runService() {
        System.out.println("runService");
        System.out.println("mls : " + mls);

        progressBar.progressProperty().bind(service.progressProperty());
        progressIndicator.progressProperty().bind(service.progressProperty());

        service.setOnSucceeded(event -> {
            System.out.println("SERVICE SUCCEED");
        });

        service.restart();
    }

    private static void waitOneTime() {
        try {
            Thread.sleep(70); // Augmenter le délai pour visualiser la progression
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setMls(int ml) {
        mls = ml;
    }

    public static ProgressBar getProgressBar() {
        return progressBar;
    }

    public static ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public static Service<Void> getService() {
        return service;
    }

    public static void setProgressBar(ProgressBar pB) {
        progressBar = pB;
    }

    public static void setProgressIndicator(ProgressIndicator pI) {
        progressIndicator = pI;
    }

    public static void runService2(ProgressBar bar, ProgressIndicator indicator, int ml, VBox vBox, Label nameLabel, ArrayList<ProductForPanier> products, int[] currentIndex) {
        //progressBar = bar;
        //progressIndicator = indicator;
        mls = ml;
        vBox.getChildren().addAll(progressBar,progressIndicator);
        progressBar.progressProperty().bind(service.progressProperty());
        progressIndicator.progressProperty().bind(service.progressProperty());

        service.setOnSucceeded(event -> {
            System.out.println("SERVICE SUCCEED");
            progressBar.progressProperty().unbind();
            progressBar.setProgress(1);
            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(1);
            isEnd = true;
        });

        service.setOnSucceeded(event -> {
            System.out.println("Produit traité : " + products.get(currentIndex[0]).getNameProduct());
            currentIndex[0]++; // Passe au produit suivant

            // Si tous les produits sont traités
            if (currentIndex[0] >= products.size()) {
                nameLabel.setText("Tous les produits ont été traités.");
                System.out.println("Tous les produits ont été traités.");
            } else {
                // Préparation du produit suivant
                ProductForPanier nextProduct = products.get(currentIndex[0]);
                nameLabel.setText("Préparation de : " + nextProduct.getNameProduct());
            }
        });

        service.setOnCancelled(event -> {
            System.out.println("Traitement annulé.");
            nameLabel.setText("Traitement annulé.");
        });

        service.setOnFailed(event -> {
            Throwable exception = service.getException();
            System.out.println("Erreur lors du traitement du produit : " + exception.getMessage());
            nameLabel.setText("Erreur pendant le traitement.");
        });

        service.restart();
    }

    public static boolean isEnd() {
        return isEnd;
    }
}