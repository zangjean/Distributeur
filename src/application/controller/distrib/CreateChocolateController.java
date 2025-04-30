package application.controller.distrib;

import application.Main;
import application.model.distrib.panier.Panier;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.HotChocolate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Map;

public class CreateChocolateController {
    @FXML
    public RadioButton milk;
    @FXML
    public RadioButton water;

    @FXML
    public RadioButton vanilla;
    @FXML
    public RadioButton hazelnut;
    @FXML
    public RadioButton caramel;
    @FXML
    public RadioButton almond;

    @FXML
    public RadioButton marshmallow;
    @FXML
    public RadioButton chantilly;
    @FXML
    public RadioButton speculos;
    @FXML
    public RadioButton oreo;

    @FXML
    public RadioButton rienTopping;
    @FXML
    public RadioButton rienArome;
    @FXML
    public Label errorMassage;


    @FXML
    public RadioButton small;
    @FXML
    public RadioButton medium;
    @FXML
    public RadioButton large;


    private ProductController productController;
    private Panier panier;

    @FXML
    public void initialize() {
        this.productController = Main.getProductController();
        this.panier = Main.getPanier();
        initAllRadioButtons();

    }

    public void onActionCreateChocolate(ActionEvent actionEvent) {
        if(!checkSizeButtonSelected()){
            this.errorMassage.setText("Il faut choisir une taille pour le chocolat");
            this.errorMassage.setStyle("-fx-text-fill: red");
        } else if (!checkBaseButtonSelected()){
            this.errorMassage.setText("Il faut choisir une base pour le chocolat");
            this.errorMassage.setStyle("-fx-text-fill: red");
        }else {
            this.errorMassage.setText("");
            String size = witchSize();
            String base = witchBase();
            String aroma = witchAroma();
            ArrayList<String> toppings = witchTopping();

            Product newProduct = this.productController.createChocolate(size, base, aroma, toppings);
            Map<Integer, Double> map = newProduct.getQuantityPriceMap();

// Supposons que vous choisissez une quantité/size.
            HotChocolate hotChocolate = (HotChocolate) newProduct;
            int selectedQuantity = hotChocolate.getSize(witchSize()); // Exemple : Petite taille
            if (map.containsKey(selectedQuantity)) {
                double selectedPrice = map.get(selectedQuantity); // Récupération du prix
                this.panier.addProduct(newProduct, selectedQuantity, selectedPrice); // Ajouté au panier
            } else {
                System.out.println("Quantité " + selectedQuantity + " non disponible pour ce produit !");
            }

        }
    }

    private void initAllRadioButtons() {

        // Créer un groupe de boutons (ToggleGroup) exclusif pour milk et water
        ToggleGroup toggleGroupBase = new ToggleGroup();

        // Ajouter milk et water au groupe
        this.milk.setToggleGroup(toggleGroupBase);
        this.water.setToggleGroup(toggleGroupBase);

        ToggleGroup toggleGroupAroma = new ToggleGroup();
        this.vanilla.setToggleGroup(toggleGroupAroma);
        this.hazelnut.setToggleGroup(toggleGroupAroma);
        this.caramel.setToggleGroup(toggleGroupAroma);
        this.almond.setToggleGroup(toggleGroupAroma);
        this.rienArome.setToggleGroup(toggleGroupAroma);

        initToppingLogic();
        this.rienTopping.setSelected(true);
        this.rienArome.setSelected(true);

        ToggleGroup toggleGroupSize = new ToggleGroup();
        this.small.setToggleGroup(toggleGroupSize);
        this.large.setToggleGroup(toggleGroupSize);
        this.medium.setToggleGroup(toggleGroupSize);


    }


    private void initToppingLogic() {
        // Si "Rien" est coché, décocher les autres
        this.rienTopping.setOnAction(event -> {
            if (this.rienTopping.isSelected()) {
                this.marshmallow.setSelected(false);
                this.chantilly.setSelected(false);
                this.speculos.setSelected(false);
                this.oreo.setSelected(false);
            }
        });

        // Si un des toppings est coché, décocher "Rien"
        this.marshmallow.setOnAction(event -> {
            if (this.marshmallow.isSelected()) {
                this.rienTopping.setSelected(false);
            }
        });
        this.chantilly.setOnAction(event -> {
            if (this.chantilly.isSelected()) {
                this.rienTopping.setSelected(false);
            }
        });
        this.speculos.setOnAction(event -> {
            if (this.speculos.isSelected()) {
                this.rienTopping.setSelected(false);
            }
        });
        this.oreo.setOnAction(event -> {
            if (this.oreo.isSelected()) {
                this.rienTopping.setSelected(false);
            }
        });
    }

    private boolean checkBaseButtonSelected (){
        return this.milk.isSelected() || this.water.isSelected();
    }

    private boolean checkSizeButtonSelected (){
        return this.small.isSelected() || this.medium.isSelected() || this.large.isSelected();
    }

    private String witchBase(){
        if(this.milk.isSelected()) return "MILK";
        else return "WATER";
    }

    private String witchAroma(){
        if(this.vanilla.isSelected()) return "VANILLA";
        else if(this.hazelnut.isSelected()) return "HAZELNUT";
        else if(this.caramel.isSelected()) return "CARAMEL";
        else if(this.almond.isSelected()) return "ALMOND";
        else return "RIEN";
    }

    private ArrayList<String> witchTopping(){
        ArrayList<String> toppings = new ArrayList<>();
        if(this.marshmallow.isSelected()) toppings.add("MARSHMALLOW");
        if(this.chantilly.isSelected()) toppings.add("CHANTILLY");
        if(this.speculos.isSelected()) toppings.add("SPECULOS");
        if(this.oreo.isSelected()) toppings.add("OREO");
        return toppings;
    }

    private String witchSize(){
        if(this.small.isSelected()) return "SMALL";
        else if(this.medium.isSelected()) return "MEDIUM";
        else return "LARGE";
    }


}
