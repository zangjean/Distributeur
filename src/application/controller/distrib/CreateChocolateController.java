package application.controller.distrib;

import application.Main;
import application.model.distrib.panier.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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


    private ProductController productController;
    private Panier panier;

    @FXML
    public void initialize() {
        this.productController = Main.getProductController();
        this.panier = Main.getPanier();
        initAllRadioButtons();

    }

    public void onActionCreateChocolate(ActionEvent actionEvent) {
        if(!checkBaseButtonSelected()){
            this.errorMassage.setText("Il faut choisir une base pour le chocolat");
            this.errorMassage.setStyle("-fx-text-fill: red");
        }else {
            this.errorMassage.setText("");
            this.productController.createChocolate();

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


}
