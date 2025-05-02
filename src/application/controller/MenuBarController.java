package application.controller;

import application.MainApp;
import application.controller.connexion.ConnexionController;
import application.controller.distrib.ProductController;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import application.model.distrib.productModel.product.Product;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Contrôleur de la barre de menu générale de l'application.
 * Gère les interactions utilisateur liées à la navigation, au compte, au panier et aux actions d'achat.
 */
public class MenuBarController {

    /** Label affichant le message de bienvenue ou d'état de connexion. */
    @FXML public Label fx_id_label_hello;

    /** Image de profil ou bouton d'accès au compte. */
    @FXML public ImageView fx_id_account;

    /** Conteneur principal de l'en-tête de l'application. */
    @FXML public BorderPane fx_id_header;

    /** Bouton pour retourner à l'accueil. */
    @FXML public Button fx_id_acceuilButton;

    /** Le panier de l'utilisateur. */
    private Panier panier;

    /** Contrôleur de gestion des produits. */
    private ProductController productController;

    /** Indices des colonnes du tableau de panier. */
    private int panierType = 0, panierNomProduit = 1, panierTaille = 2, panierPrix = 3, panierQuantiteDemande = 4, panierDelete = 5;

    /** Nom d'utilisateur actuellement connecté. */
    private String currentUsername = "";

    /** Indique si le message "panier vide" a déjà été affiché. */
    private Boolean messagePanierEmpty = false;

    /**
     * Initialise le contrôleur après chargement FXML.
     * Configure le message de bienvenue selon l'état de connexion.
     */
    @FXML
    public void initialize() {
        this.panier = MainApp.getPanier();
        this.productController = MainApp.productController;

        if (MainApp.connexion.is_connected()) {
            this.currentUsername = MainApp.panier.getUser().getUsername() + " Vous avez " + MainApp.panier.getUser().getPoints() + " points.";
        }

        fx_id_label_hello.textProperty().bind(
                Bindings.when(MainApp.connexion.isConnectedProperty())
                        .then("Bienvenue, " + this.currentUsername + " !")
                        .otherwise("Pas d'utilisateur connecté.")
        );
    }

    /**
     * Applique un effet de survol (bordure rouge) sur le panneau du compte.
     */
    @FXML
    public void onMouseEnteredAccount(MouseEvent mouseEvent) {
        StackPane stackPaneAccount = (StackPane) mouseEvent.getSource();
        stackPaneAccount.setStyle("-fx-border-color: red ; -fx-border-width: 5 ; -fx-border-radius: 4");
    }

    /**
     * Supprime l'effet de survol du panneau du compte.
     */
    @FXML
    public void onMouseExitedAccount(MouseEvent mouseEvent) {
        StackPane stackPaneAccount = (StackPane) mouseEvent.getSource();
        stackPaneAccount.setStyle("-fx-border-color:transparent; ; -fx-border-width: 5");
    }

    /**
     * Ouvre la fenêtre de connexion ou d'édition de compte.
     */
    @FXML
    public void onMousePressedAccount(MouseEvent mouseEvent) throws IOException {
        int width = 300, height = 200;
        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/connexion/createOrConnexion.fxml"))).load(), width, height);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(MainApp.connexion.is_connected() ? " DECONNEXION OR EDIT " : " CREATE OR CONNEXION ");
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Action de retour à l'écran d'accueil si ce n’est pas déjà la scène actuelle.
     */
    @FXML
    public void onActionAcceuil(ActionEvent actionEvent) {
        try {
            Scene currentScene = ((Button) actionEvent.getSource()).getScene();
            String currentFXML = currentScene.getRoot().getClass().getSimpleName();

            if (!"homePage.fxml".equals(currentFXML)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"));
                BorderPane acceuilView = loader.load();
                currentScene.setRoot(acceuilView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche le contenu du panier de l'utilisateur dans une nouvelle fenêtre.
     */
    public void onActionPanier(ActionEvent actionEvent) {
        BorderPane borderPane = new BorderPane();
        GridPane panierGrid = new GridPane();
        panierGrid.setGridLinesVisible(true);
        panierGrid.add(new Label(" Type "), panierType, 0);
        panierGrid.add(new Label(" Nom Produit "), panierNomProduit, 0);
        panierGrid.add(new Label(" Taille (ml) "), panierTaille, 0);
        panierGrid.add(new Label(" Prix (€) "), panierPrix, 0);
        panierGrid.add(new Label(" Quantité demandée "), panierQuantiteDemande, 0);
        panierGrid.add(new Label(" Delete "), panierDelete, 0);

        int rowIndex = 1;
        for (ProductForPanier product : panier.getProducts()) {
            panierGrid.add(new Label(product.getOriginaClassName()), panierType, rowIndex);
            panierGrid.add(new Label(product.getNameProduct()), panierNomProduit, rowIndex);
            panierGrid.add(new Label(String.valueOf(product.getQuantityML())), panierTaille, rowIndex);
            panierGrid.add(new Label(String.valueOf(product.getPrice())), panierPrix, rowIndex);
            panierGrid.add(new Label(String.valueOf(product.getAskingQuantity())), panierQuantiteDemande, rowIndex);

            Button btnDelete = new Button("Delete");
            deleteProductFromPanier(btnDelete, product);
            panierGrid.add(btnDelete, panierDelete, rowIndex);
            rowIndex++;
        }

        borderPane.setCenter(panierGrid);

        Button btnAchat = new Button("PAY (tot: " + panier.getTotalPrice() + " )");
        VBox vBox = new VBox(btnAchat);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setBottom(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);

        byAllPanier(btnAchat, vBox);

        Stage newStage = new Stage();
        newStage.setTitle(" Panier ");
        newStage.setScene(new Scene(borderPane, 500, 300));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());
        newStage.show();
    }

    /**
     * Supprime un produit du panier lors du clic sur le bouton associé.
     */
    private void deleteProductFromPanier(Button button, ProductForPanier product) {
        button.setOnAction(e -> {
            panier.removeProduct(product);
            ((Stage) button.getScene().getWindow()).close();
        });
    }

    /**
     * Initialise l'action du bouton "Valider l'achat" dans le panier.
     */
    private void byAllPanier(Button button, VBox vBox) {
        button.setOnAction(e -> {
            if (panier.getProducts().isEmpty()) {
                if (!messagePanierEmpty) {
                    Label label = new Label("Votre panier est vide.");
                    label.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: red");
                    vBox.getChildren().add(label);
                    messagePanierEmpty = true;
                }
            } else {
                if (MainApp.connexion.is_connected()) {
                    int points = panier.howManyPointsUserWin();
                    panier.setPointsForCurrentUser(points);
                }
                byAllPanierSave(e);
                byAllPanierRun(e);
            }
        });
    }

    /**
     * Sauvegarde les produits du panier pour les favoris.
     */
    private void byAllPanierSave(ActionEvent actionEvent) {
        panier.updateFavorite(panier);
    }

    /**
     * Démarre l’affichage de la fenêtre de préparation des produits du panier.
     */
    private void byAllPanierRun(ActionEvent actionEvent) {
        VBox vBox = new VBox();
        Label label = new Label("Votre panier contient : ");
        Label nameLabel = new Label();
        nameLabel.setStyle("-fx-font-weight: bold");

        vBox.getChildren().addAll(label, nameLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox, 400, 200);
        Stage stage = new Stage();
        stage.setTitle("Réalisation des produits du panier");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        runProductsSequentially(new ArrayList<>(panier.getProducts()), vBox, nameLabel, panier.getProducts().getFirst().getAskingQuantity());
    }

    /**
     * Traite séquentiellement chaque produit du panier avec animation de progression.
     */
    private void runProductsSequentially(ArrayList<ProductForPanier> products, VBox vBox, Label nameLabel, int quantity) {
        if (products == null || products.isEmpty()) {
            nameLabel.setText("Aucun produit à traiter.");
            return;
        }

        final int[] currentIndex = {0};
        nameLabel.setText("Préparation de : " + products.get(currentIndex[0]).getNameProduct());

        ProgressBar bar = new ProgressBar();
        ProgressIndicator indicator = new ProgressIndicator();
        bar.setPrefWidth(250);
        vBox.getChildren().addAll(bar, indicator);

        Service<Void> productService = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        ProductForPanier product = products.get(currentIndex[0]);
                        for (int i = 0; i < product.getQuantityML(); i++) {
                            if (isCancelled()) break;
                            updateProgress(i + 1, product.getQuantityML());
                            Thread.sleep(50);
                        }
                        return null;
                    }
                };
            }
        };

        bar.progressProperty().bind(productService.progressProperty());
        indicator.progressProperty().bind(productService.progressProperty());

        productService.setOnSucceeded(e -> {
            if (quantity == 1) {
                currentIndex[0]++;
                if (currentIndex[0] >= products.size()) {
                    nameLabel.setText("Tous les produits ont été traités.");
                    MainApp.panier.clear();
                    pickupProduct(vBox);
                } else {
                    restartProduct(productService, products, vBox, nameLabel, quantity, currentIndex);
                }
            } else {
                restartProduct(productService, products, vBox, nameLabel, quantity - 1, currentIndex);
            }
        });

        productService.setOnFailed(e -> {
            nameLabel.setText("Erreur pendant le traitement.");
        });

        productService.start();
    }

    /**
     * Ajoute un bouton de confirmation de récupération des produits.
     */
    private void pickupProduct(VBox vBox) {
        Button pickup = new Button("All products have been picked up");
        vBox.getChildren().add(pickup);
        pickup.setOnAction(e -> ((Stage) pickup.getScene().getWindow()).close());
    }

    /**
     * Recharge le traitement d'un produit ou passe au suivant.
     */
    private void restartProduct(Service<Void> service, ArrayList<ProductForPanier> products, VBox vBox, Label nameLabel, int quantity, int[] currentIndex) {
        nameLabel.setText("Préparation de : " + products.get(currentIndex[0]).getNameProduct());
        vBox.getChildren().removeIf(node -> node instanceof ProgressBar || node instanceof ProgressIndicator);
        if (quantity > 1) {
            runProductsSequentially(products, vBox, nameLabel, quantity - 1);
        } else {
            ArrayList<ProductForPanier> sub = new ArrayList<>(products.subList(currentIndex[0], products.size()));
            runProductsSequentially(sub, vBox, nameLabel, sub.getFirst().getAskingQuantity());
        }
    }

    /**
     * Affiche tous les produits de la boutique en console.
     */
    public void onActionPrintShop(ActionEvent actionEvent) {
        ArrayList<Product> products = productController.getProductsModel().getAllProducts();
        System.out.println("Print Shop");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
