package application.controller.distrib;

import application.MainApp;
import application.model.distrib.productModel.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CreateChocolateController {

    @FXML
    public Label errorMassage;
    // Boutons pour la sélection des cups
    @FXML
    private ToggleButton medium;
    @FXML
    private ToggleButton small;
    @FXML
    private ToggleButton large;

    // Boutons pour la sélection des arômes
    @FXML
    private ToggleButton vanilla;
    @FXML
    private ToggleButton hazelnut;
    @FXML
    private ToggleButton caramel;
    @FXML
    private ToggleButton almond;
    @FXML
    private ToggleButton rienArome;

    // Boutons pour la sélection de la base liquide
    @FXML
    private ToggleButton milk;
    @FXML
    private ToggleButton water;

    // Boutons pour les toppings
    @FXML
    private ToggleButton marshmallow;
    @FXML
    private ToggleButton chantilly;
    @FXML
    private ToggleButton speculos;
    @FXML
    private ToggleButton oreo;
    @FXML
    private ToggleButton rienTopping;

    @FXML
    public void initialize() {
        this.rienTopping.setSelected(true);
        this.rienArome.setSelected(true);

        ToggleGroup groupSize = new ToggleGroup();
        this.medium.setToggleGroup(groupSize);
        this.small.setToggleGroup(groupSize);
        this.large.setToggleGroup(groupSize);

        ToggleGroup groupArome = new ToggleGroup();
        this.vanilla.setToggleGroup(groupArome);
        this.hazelnut.setToggleGroup(groupArome);
        this.caramel.setToggleGroup(groupArome);
        this.almond.setToggleGroup(groupArome);
        this.rienArome.setToggleGroup(groupArome);

        ToggleGroup groupBase = new ToggleGroup();
        this.milk.setToggleGroup(groupBase);
        this.water.setToggleGroup(groupBase);

    }

    /**
     * Méthode utilitaire pour basculer la sélection d'un bouton.
     *
     * @param button Le bouton à basculer.
     * @param aroma
     */
    private void toggleSelection(ToggleButton button, String aroma) {
        button.setSelected(!button.isSelected());
        if(aroma.equals("AROMA") && !button.isSelected()){
            this.rienArome.setSelected(true);
        }
    }

    /**
     * Méthode utilitaire pour basculer la sélection d'un bouton et
     * réinitialiser les autres options liées, si nécessaire.
     *
     * @param buttonToToggle Le bouton à basculer.
     * @param buttonsToReset Les boutons à réinitialiser.
     */
    private void toggleSelectionWithReset(ToggleButton buttonToToggle, ToggleButton buttonsToReset) {
        buttonToToggle.setSelected(!buttonToToggle.isSelected());
        if (buttonToToggle.isSelected()) {
            buttonsToReset.setSelected(false);

        }
        nothingToppingIsSelected();
    }

    // Sélection des tailles de cup
    @FXML
    public void pressedMediumCup(MouseEvent mouseEvent) {
        toggleSelection(this.medium,"AROMA");
    }

    @FXML
    public void pressedSmallCup(MouseEvent mouseEvent) {
        toggleSelection(this.small, "AROMA");
    }

    @FXML
    public void pressedLargeCup(MouseEvent mouseEvent) {
        toggleSelection(this.large, "AROMA");
    }

    // Sélection des arômes
    @FXML
    public void pressedVanilla(MouseEvent mouseEvent) {
        toggleSelection(this.vanilla, "AROMA");

    }

    @FXML
    public void pressedHazelnut(MouseEvent mouseEvent) {
        toggleSelection(this.hazelnut, "AROMA");
    }

    @FXML
    public void pressedCaramel(MouseEvent mouseEvent) {
        toggleSelection(this.caramel, "AROMA");
    }

    @FXML
    public void pressedAlmond(MouseEvent mouseEvent) {
        toggleSelection(this.almond, "AROMA");
    }

    @FXML
    public void pressedNothingArome(MouseEvent mouseEvent) {
        toggleSelection(this.rienArome, "AROMA");
    }

    // Sélection des bases liquides
    @FXML
    public void pressedMilk(MouseEvent mouseEvent) {
        toggleSelection(this.milk, "AROMA");
    }

    @FXML
    public void pressedWater(MouseEvent mouseEvent) {
        toggleSelection(this.water, "AROMA");
    }

    // Sélection des toppings
    @FXML
    public void pressedNothingToppings(MouseEvent mouseEvent) {
        this.rienTopping.setSelected(true);

        // Réinitialise toutes les autres options de toppings
        this.marshmallow.setSelected(false);
        this.chantilly.setSelected(false);
        this.speculos.setSelected(false);
        this.oreo.setSelected(false);
    }

    @FXML
    public void pressedMarshmallow(MouseEvent mouseEvent) {
        toggleSelectionWithReset(this.marshmallow, this.rienTopping);
    }

    @FXML
    public void pressedChantilly(MouseEvent mouseEvent) {
        toggleSelectionWithReset(this.chantilly, this.rienTopping);
    }

    @FXML
    public void pressedSpeculos(MouseEvent mouseEvent) {
        toggleSelectionWithReset(this.speculos, this.rienTopping);
    }

    @FXML
    public void pressedOreo(MouseEvent mouseEvent) {
        toggleSelectionWithReset(this.oreo, this.rienTopping);
    }

    @FXML
    public void onActionCreateChocolate(ActionEvent actionEvent) {
        System.out.println("Create Chocolate");
        String selectedSize = witchSize();
        if(selectedSize.equals("RIEN")){
            this.errorMassage.setText("Please select a cup size !");
            this.errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
        }else{
            String selectedBase = witchBase();
            if(selectedBase.equals("RIEN")){
                this.errorMassage.setText("Please select a base !");
                this.errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
            }else{
                String selectedAroma = witchAroma();
                ArrayList<String> selectedToppings = getSelectedToppings();
                int quantityMl = witchQuantityMl(selectedSize);
                Product chocolate = MainApp.productController.createChocolate(selectedSize,selectedBase,selectedAroma,selectedToppings);
                MainApp.panier.addProduct(chocolate,quantityMl,chocolate.getOnePrice(quantityMl));

                this.errorMassage.setText("Chocolate created !");
                this.errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: green ");

            }
        }


    }

    private String witchAroma(){
        if(this.vanilla.isSelected()){
            return "VANILLA";
        }else if(this.hazelnut.isSelected()){
            return "HAZELNUT";
        }else if(this.caramel.isSelected()){
            return "CARAMEL";
        }else if(this.almond.isSelected()){
            return "ALMOND";
        }else{
            return "RIEN";
        }
    }

    private String witchBase(){
        if(this.milk.isSelected()){
            return "MILK";
        } else if (this.water.isSelected()) {
            return "WATER";
        }else {
            return "RIEN";
        }
    }

    private String witchSize(){
        if(this.medium.isSelected()){
            return "MEDIUM";
        }else if(this.small.isSelected()){
            return "SMALL";
        } else if (this.large.isSelected()) {
            return "LARGE";
        }else {
            return "RIEN";
        }
    }

    private int witchQuantityMl(String size){
        if(size.equals("SMALL")){
            return 240;
        }
        if(size.equals("MEDIUM")){
            return 350;
        }
        if(size.equals("LARGE")){
            return 450;
        }
        return 0;
    }


    private void nothingToppingIsSelected(){
        if(!this.marshmallow.isSelected() && !this.chantilly.isSelected() && !this.speculos.isSelected() && !this.oreo.isSelected()){
            this.rienTopping.setSelected(true);
        }
    }

    private ArrayList<String> getSelectedToppings(){
        ArrayList<String> toppings = new ArrayList<>();
        if(this.marshmallow.isSelected()){
            toppings.add("MARSHMALLOW");
        }
        if(this.chantilly.isSelected()){
            toppings.add("CHANTILLY");
        }
        if(this.speculos.isSelected()){
            toppings.add("SPECULOS");
        }
        if(this.oreo.isSelected()){
            toppings.add("OREO");
        }
        return toppings;
    }



}