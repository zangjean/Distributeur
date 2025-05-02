package application.controller.distrib.hotDrink;

import application.MainApp;
import application.model.distrib.productModel.product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Contrôleur pour la création personnalisée de chocolat chaud.
 * Permet à l'utilisateur de sélectionner la taille, la base, l'arôme et les toppings d'un chocolat,
 * puis de l'ajouter au panier.
 */
public class CreateChocolateController {

    /** Label utilisé pour afficher des messages d'erreur ou de confirmation. */
    @FXML
    public Label errorMassage;

    // --- Boutons de taille de cup ---
    /** Bouton pour la taille "medium". */
    @FXML private ToggleButton medium;
    /** Bouton pour la taille "small". */
    @FXML private ToggleButton small;
    /** Bouton pour la taille "large". */
    @FXML private ToggleButton large;

    // --- Boutons pour les arômes ---
    /** Bouton pour l'arôme "vanille". */
    @FXML private ToggleButton vanilla;
    /** Bouton pour l'arôme "noisette". */
    @FXML private ToggleButton hazelnut;
    /** Bouton pour l'arôme "caramel". */
    @FXML private ToggleButton caramel;
    /** Bouton pour l'arôme "amande". */
    @FXML private ToggleButton almond;
    /** Bouton pour "aucun arôme". */
    @FXML private ToggleButton rienArome;

    // --- Boutons pour la base liquide ---
    /** Bouton pour une base "lait". */
    @FXML private ToggleButton milk;
    /** Bouton pour une base "eau". */
    @FXML private ToggleButton water;

    // --- Boutons pour les toppings ---
    /** Bouton pour le topping "marshmallow". */
    @FXML private ToggleButton marshmallow;
    /** Bouton pour le topping "chantilly". */
    @FXML private ToggleButton chantilly;
    /** Bouton pour le topping "speculos". */
    @FXML private ToggleButton speculos;
    /** Bouton pour le topping "oreo". */
    @FXML private ToggleButton oreo;
    /** Bouton pour "aucun topping". */
    @FXML private ToggleButton rienTopping;

    /**
     * Initialise les groupes de boutons pour s'assurer qu'un seul élément est sélectionné par groupe,
     * et active par défaut "aucun arôme" et "aucun topping".
     */
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

    // Méthodes associées à chaque interaction utilisateur (clics)

    /** Sélectionne la taille "medium". */
    @FXML public void pressedMediumCup(MouseEvent e) { toggleSelection(medium, "AROMA"); }

    /** Sélectionne la taille "small". */
    @FXML public void pressedSmallCup(MouseEvent e) { toggleSelection(small, "AROMA"); }

    /** Sélectionne la taille "large". */
    @FXML public void pressedLargeCup(MouseEvent e) { toggleSelection(large, "AROMA"); }

    /** Sélectionne l'arôme "vanille". */
    @FXML public void pressedVanilla(MouseEvent e) { toggleSelection(vanilla, "AROMA"); }

    /** Sélectionne l'arôme "noisette". */
    @FXML public void pressedHazelnut(MouseEvent e) { toggleSelection(hazelnut, "AROMA"); }

    /** Sélectionne l'arôme "caramel". */
    @FXML public void pressedCaramel(MouseEvent e) { toggleSelection(caramel, "AROMA"); }

    /** Sélectionne l'arôme "amande". */
    @FXML public void pressedAlmond(MouseEvent e) { toggleSelection(almond, "AROMA"); }

    /** Réinitialise la sélection d'arôme. */
    @FXML public void pressedNothingArome(MouseEvent e) { toggleSelection(rienArome, "AROMA"); }

    /** Sélectionne la base "lait". */
    @FXML public void pressedMilk(MouseEvent e) { toggleSelection(milk, "AROMA"); }

    /** Sélectionne la base "eau". */
    @FXML public void pressedWater(MouseEvent e) { toggleSelection(water, "AROMA"); }

    /** Sélectionne le topping "marshmallow". */
    @FXML public void pressedMarshmallow(MouseEvent e) { toggleSelectionWithReset(marshmallow, rienTopping); }

    /** Sélectionne le topping "chantilly". */
    @FXML public void pressedChantilly(MouseEvent e) { toggleSelectionWithReset(chantilly, rienTopping); }

    /** Sélectionne le topping "speculos". */
    @FXML public void pressedSpeculos(MouseEvent e) { toggleSelectionWithReset(speculos, rienTopping); }

    /** Sélectionne le topping "oreo". */
    @FXML public void pressedOreo(MouseEvent e) { toggleSelectionWithReset(oreo, rienTopping); }

    /**
     * Réinitialise tous les toppings sauf "rien".
     */
    @FXML
    public void pressedNothingToppings(MouseEvent e) {
        rienTopping.setSelected(true);
        marshmallow.setSelected(false);
        chantilly.setSelected(false);
        speculos.setSelected(false);
        oreo.setSelected(false);
    }

    /**
     * Crée un chocolat personnalisé à partir des choix de l'utilisateur,
     * vérifie les champs obligatoires et ajoute le produit au panier.
     *
     * @param actionEvent L'événement déclenché par le clic sur le bouton de création.
     */
    @FXML
    public void onActionCreateChocolate(ActionEvent actionEvent) {
        System.out.println("Create Chocolate");
        String selectedSize = witchSize();
        if (selectedSize.equals("RIEN")) {
            errorMassage.setText("Please select a cup size !");
            errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
        } else {
            String selectedBase = witchBase();
            if (selectedBase.equals("RIEN")) {
                errorMassage.setText("Please select a base !");
                errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
            } else {
                String selectedAroma = witchAroma();
                ArrayList<String> selectedToppings = getSelectedToppings();
                int quantityMl = witchQuantityMl(selectedSize);

                Product chocolate = MainApp.productController.createChocolate(
                        selectedSize, selectedBase, selectedAroma, selectedToppings
                );
                MainApp.panier.addProduct(chocolate, quantityMl, chocolate.getOnePrice(quantityMl));

                errorMassage.setText("Chocolate created !");
                errorMassage.setStyle("-fx-font-size: 20; -fx-text-fill: green ");
            }
        }
    }

    // --- Méthodes utilitaires internes ---

    /**
     * Bascule la sélection d'un bouton, et active "aucun arôme" si aucun autre n'est sélectionné.
     *
     * @param button Le bouton à basculer.
     * @param aroma  Le type (AROMA) pour appliquer le comportement spécial.
     */
    private void toggleSelection(ToggleButton button, String aroma) {
        button.setSelected(!button.isSelected());
        if (aroma.equals("AROMA") && !button.isSelected()) {
            rienArome.setSelected(true);
        }
    }

    /**
     * Active un bouton tout en désactivant une option par défaut.
     *
     * @param buttonToToggle Le bouton que l'on souhaite activer.
     * @param buttonsToReset Le bouton à désactiver si {@code buttonToToggle} est activé.
     */
    private void toggleSelectionWithReset(ToggleButton buttonToToggle, ToggleButton buttonsToReset) {
        buttonToToggle.setSelected(!buttonToToggle.isSelected());
        if (buttonToToggle.isSelected()) {
            buttonsToReset.setSelected(false);
        }
        nothingToppingIsSelected();
    }

    /**
     * Si aucun topping n'est sélectionné, sélectionne automatiquement "aucun".
     */
    private void nothingToppingIsSelected() {
        if (!marshmallow.isSelected() && !chantilly.isSelected() &&
                !speculos.isSelected() && !oreo.isSelected()) {
            rienTopping.setSelected(true);
        }
    }

    /**
     * Retourne l'arôme sélectionné, ou "RIEN" si aucun n'est sélectionné.
     *
     * @return Le nom de l'arôme.
     */
    private String witchAroma() {
        if (vanilla.isSelected()) return "VANILLA";
        if (hazelnut.isSelected()) return "HAZELNUT";
        if (caramel.isSelected()) return "CARAMEL";
        if (almond.isSelected()) return "ALMOND";
        return "RIEN";
    }

    /**
     * Retourne la base liquide sélectionnée, ou "RIEN".
     *
     * @return La base sélectionnée.
     */
    private String witchBase() {
        if (milk.isSelected()) return "MILK";
        if (water.isSelected()) return "WATER";
        return "RIEN";
    }

    /**
     * Retourne la taille de cup sélectionnée, ou "RIEN".
     *
     * @return La taille choisie.
     */
    private String witchSize() {
        if (medium.isSelected()) return "MEDIUM";
        if (small.isSelected()) return "SMALL";
        if (large.isSelected()) return "LARGE";
        return "RIEN";
    }

    /**
     * Calcule la quantité en mL à partir de la taille sélectionnée.
     *
     * @param size La taille choisie.
     * @return La quantité en mL.
     */
    private int witchQuantityMl(String size) {
        switch (size) {
            case "SMALL": return 240;
            case "MEDIUM": return 350;
            case "LARGE": return 450;
            default: return 0;
        }
    }

    /**
     * Retourne la liste des toppings sélectionnés.
     *
     * @return Une liste de toppings.
     */
    private ArrayList<String> getSelectedToppings() {
        ArrayList<String> toppings = new ArrayList<>();
        if (marshmallow.isSelected()) toppings.add("MARSHMALLOW");
        if (chantilly.isSelected()) toppings.add("CHANTILLY");
        if (speculos.isSelected()) toppings.add("SPECULOS");
        if (oreo.isSelected()) toppings.add("OREO");
        return toppings;
    }
}
