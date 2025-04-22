package application.controller.distrib;

import application.Main;
import application.model.distrib.panier.Panier;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.food.Pizza;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Map;

public class MenuPizzaController {
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab tab_margherita;
    @FXML
    public Tab tab_pepperoni;
    @FXML
    public Tab tab_veggie;
    @FXML
    public Tab tab_bbq;
    @FXML
    public Tab tab_hawaiian;
    @FXML
    public GridPane grid_pepperoni;
    @FXML
    public GridPane grid_veggie;
    @FXML
    public GridPane grid_bbq;
    @FXML
    public GridPane grid_hawaiian;

    private ProductController productController;
    private Panier panier;
    @FXML
    public void initialize() {
        this.productController = Main.getProductController();
        this.panier = Main.getPanier();
        initAllMargherita();
        initAllPepperoni();
        initAllVeggie();
        initAllBbq();
        initAllHawaiian();
    }

    private void initAllMargherita() {
        FlowPane flowPane_margherita = new FlowPane();
        flowPane_margherita.setHgap(10);
        flowPane_margherita.setVgap(10);

        Button orderButton = new Button("Order");
        orderButton.setOnAction(event -> openSelectPizzaDialog("Margherita", orderButton));
        flowPane_margherita.getChildren().add(orderButton);

        this.tab_margherita.setContent(flowPane_margherita);
    }


    private void initAllPepperoni() {
        FlowPane flowPane_pepperoni = new FlowPane();
        flowPane_pepperoni.setHgap(10);
        flowPane_pepperoni.setVgap(10);

        Button orderButton = new Button("Order");
        orderButton.setOnAction(event -> openSelectPizzaDialog("Pepperoni", orderButton));
        flowPane_pepperoni.getChildren().add(orderButton);

        this.tab_pepperoni.setContent(flowPane_pepperoni);
    }


    private void initAllVeggie() {
        FlowPane flowPane_veggie = new FlowPane();
        flowPane_veggie.setHgap(10);
        flowPane_veggie.setVgap(10);

        Button orderButton = new Button("Order");
        orderButton.setOnAction(event -> openSelectPizzaDialog("Veggie", orderButton));
        flowPane_veggie.getChildren().add(orderButton);

        this.tab_veggie.setContent(flowPane_veggie);
    }


    private void initAllBbq() {
        FlowPane flowPane_bbq = new FlowPane();
        flowPane_bbq.setHgap(10);
        flowPane_bbq.setVgap(10);

        Button orderButton = new Button("Order");
        orderButton.setOnAction(event -> openSelectPizzaDialog("BBQ", orderButton));
        flowPane_bbq.getChildren().add(orderButton);

        this.tab_bbq.setContent(flowPane_bbq);
    }


    private void initAllHawaiian() {
        FlowPane flowPane_hawaiian = new FlowPane();
        flowPane_hawaiian.setHgap(10);
        flowPane_hawaiian.setVgap(10);

        Button orderButton = new Button("Order");
        orderButton.setOnAction(event -> openSelectPizzaDialog("Hawaiian", orderButton));
        flowPane_hawaiian.getChildren().add(orderButton);

        this.tab_hawaiian.setContent(flowPane_hawaiian);
    }


    private void openSelectPizzaDialog(String pizzaName, Button button) {
        ArrayList<Pizza> pizzas = returnPizza();
        Pizza targetPizza = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getName().equalsIgnoreCase(pizzaName)) {
                targetPizza = pizza;
                break;
            }
        }
        if (targetPizza != null) {
            showSelectPizzaWindow(targetPizza, button);
        } else {
            System.out.println(pizzaName + " pizza not found.");
        }
    }


    private void showSelectPizzaWindow(Pizza pizza, Button button) {
        GridPane select = new GridPane();
        select.setGridLinesVisible(true);
        select.add(new Label(" Size "), 0, 0);
        select.add(new Label(" Price (€) "), 1, 0);
        select.add(new Label(" Select "), 2, 0);

        Map<Integer, Double> prices = pizza.getSizePriceMap();

        // Define fixed sizes and their labels
        String[] sizeLabels = {"Small", "Large", "Extra Large"};
        int[] sizeValues = {25, 30, 35}; // example sizes in cm
        double[] fixedPrices = {8.0, 12.0, 15.0}; // fixed prices for sizes

        int rowIndex = 1;
        for (int i = 0; i < sizeLabels.length; i++) {
            String sizeLabel = sizeLabels[i];
            int sizeValue = sizeValues[i];
            Double price = fixedPrices[i]; // use fixed price instead of map

            select.add(new Label(sizeLabel), 0, rowIndex);
            select.add(new Label(String.format("%.2f", price)), 1, rowIndex);

            Button btnAdd = new Button("Add");
            Map.Entry<Integer, Double> entry = new java.util.AbstractMap.SimpleEntry<>(sizeValue, price);
            addOnPanier(btnAdd, entry, pizza);

            select.add(btnAdd, 2, rowIndex);
            rowIndex++;
        }


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(select);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(closeEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });

        borderPane.setBottom(btnCancel);
        BorderPane.setAlignment(btnCancel, Pos.CENTER);

        Stage newStage = new Stage();
        newStage.setTitle(pizza.getName());

        Scene newScene = new Scene(borderPane, 400, 300);
        newStage.setScene(newScene);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(button.getScene().getWindow());

        newStage.show();
    }







    private void addOnPanier(Button button, Map.Entry<Integer, Double> entry, Pizza pizza) {
        button.setOnAction(event -> {
            System.out.println("Button clicked for size " + entry.getKey() + "cm");
            this.panier.addProduct(pizza, entry.getKey(), entry.getValue());
        });
    }

    private ArrayList<Pizza> returnPizza() {
        ArrayList<Product> products = productController.getProductsModel().getAllProducts();
        ArrayList<Pizza> pizzas = new ArrayList<>();
        for (Product product : products) {
            if (product.getClass().equals(Pizza.class)) {
                pizzas.add((Pizza) product);
            }
        }
        return pizzas;
    }




}
