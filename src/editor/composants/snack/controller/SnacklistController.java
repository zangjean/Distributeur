package editor.composants.snack.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnacklistController {

    @FXML
    private ScrollPane root;



    @FXML
    private TilePane tilePane;
    private List<ProductCard> snackList;
    // Constructor
    public SnacklistController() {
        // Initialize the SnacklistController
        System.out.println("SnacklistController created");
    }

    // Add methods to manage the snack list here
    // For example, methods to add, remove, or update snacks in the list
    public void initialize() {
        // Initialize the snack list
        System.out.println("Initializing SnacklistController");
        snackList = new ArrayList<>();
        try {
            loadSnack();
            displaySnack();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the tilePane to be scrollable
        tilePane.setPrefSize(600, 600);
        tilePane.setPrefColumns(3);
        tilePane.setHgap(20);
        tilePane.setVgap(20);

    }
    // Ajouter cette méthode pour l'animation au survol
    private void setupHoverAnimation(ProductCardLoader node) {
        javafx.animation.ScaleTransition enter = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(200), node);
        enter.setToX(1.05);
        enter.setToY(1.05);

        javafx.animation.ScaleTransition exit = new javafx.animation.ScaleTransition(javafx.util.Duration.millis(200), node);
        exit.setToX(1.0);
        exit.setToY(1.0);

        node.setOnMouseEntered(e -> enter.playFromStart());
        node.setOnMouseExited(e -> exit.playFromStart());
    }


    private void displaySnack() throws IOException {
        // Logic to display the snack list in the UI
        for (ProductCard snack : snackList) {
            System.out.println("Snack: " + snack.getName() + ", Price: " + snack.getPrice());
            ProductCardLoader productCardLoader = new ProductCardLoader();

            productCardLoader.getController().setProductCard(snack);

            // Set the product card to be draggable
            setDragandDrop(productCardLoader, snack);
            setupHoverAnimation(productCardLoader);


            // Add the product card to the tile pane
            tilePane.getChildren().add(productCardLoader);

        }
    }

    private void setDragandDrop(ProductCardLoader loader, ProductCard snack) {
        loader.setOnDragDetected(event -> {
            // Start drag-and-drop gesture
            System.out.println("Drag detected " + loader.getController().getProductCard().getName());
            // remember which product is being dragged
            DragProduct.setCurrentProduct(loader.getController().getProductCard());

            // start the drag & drop gesture
            Dragboard db = loader.startDragAndDrop(TransferMode.COPY);

            // put the product name (or ID) on the dragboard:
            ClipboardContent content = new ClipboardContent();
            content.putString(loader.getController().getProductCard().getName());
            db.setContent(content);

            // show a snapshot of the card under the cursor:
            db.setDragView(loader.snapshot(null, null));

            event.consume();
        });

        loader.setOnDragOver(event -> {
            // Accept the drag-and-drop gesture
            event.acceptTransferModes(TransferMode.COPY);
            event.consume();
        });

        loader.setOnDragDropped(event -> {
            // Handle the drop action
            System.out.println("Dropped: " + snack.getName());
            event.consume();
        });
    }

    private void loadSnack() {
        List<ProductCard> c = new java.util.ArrayList<>();
        c.add(new ProductCard("Bimbim", 1.5, "/editor/composants/templates/ressources/images/snack/snack1.jpg"));
        c.add(new ProductCard("Chips", 2.0, "/editor/composants/templates/ressources/images/snack/snack2.jpg"));
        c.add(new ProductCard("Chocolat", 2.5, "/editor/composants/templates/ressources/images/snack/snack3.jpg"));
        c.add(new ProductCard("Bonbon", 1.0, "/editor/composants/templates/ressources/images/snack/snack4.jpg"));
        c.add(new ProductCard("Barre chocolatée", 2.0, "/editor/composants/templates/ressources/images/snack/snack5.jpg"));
        c.add(new ProductCard("Barre de céréales", 1.5, "/editor/composants/templates/ressources/images/snack/snack6.jpg"));
        c.add(new ProductCard("Potate Pomlisse", 2.5, "/editor/composants/templates/ressources/images/snack/snack7.jpg"));
        c.add(new ProductCard("Barre de chocolat", 2.5, "/editor/composants/templates/ressources/images/snack/snack8.jpg"));
        c.add(new ProductCard("Vico", 2.5, "/editor/composants/templates/ressources/images/snack/snack9.jpg"));
        c.add(new ProductCard("Brets", 2.5, "/editor/composants/templates/ressources/images/snack/snack10.jpg"));
        c.add(new ProductCard("M&M's", 2.5, "/editor/composants/templates/ressources/images/snack/snack11.jpg"));
        c.add(new ProductCard("Pringles", 2.5, "/editor/composants/templates/ressources/images/snack/snack12.jpg"));
        c.add(new ProductCard("Mikado", 2.5, "/editor/composants/templates/ressources/images/snack/snack13.jpg"));
        c.add(new ProductCard("Mentos", 2.5, "/editor/composants/templates/ressources/images/snack/snack14.jpg"));
        c.add(new ProductCard("Tic Tac", 2.5, "/editor/composants/templates/ressources/images/snack/snack15.jpg"));
        snackList.addAll(c);


    }
}
