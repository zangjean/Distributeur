package application.controller.distrib.coldDrink;

import application.MainApp;
import application.controller.distrib.ProductController;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.Soda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.Main;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class ColdDrinkController {
    @FXML
    public Button zero;
    @FXML
    public Button un;
    @FXML
    public Button deux;
    @FXML
    public Button trois;
    @FXML
    public Button quatre;
    @FXML
    public Button cinq;
    @FXML
    public Button six;
    @FXML
    public Button sept;
    @FXML
    public Button huit;
    @FXML
    public Button neuf;
    @FXML
    public Button back;
    @FXML
    public Button submit;
    @FXML
    public Label printNumber;
    @FXML
    public GridPane coldDrinkGrid;

    private ProductController productController;


    @FXML
    public void initialize() {
        this.productController=MainApp.getProductController();
        System.out.println("ColdDrinkController initialized");
        System.out.println("TOUT LES SODAS: ");
        productController.getProductsModel().getSodas().forEach(System.out::println);
        fillGrid(productController.getProductsModel().getSodas());

    }

    private void fillGrid(ArrayList<Soda> sodas) {
        ArrayList<Soda> sodaList = sodas;
        int rowMax=this.coldDrinkGrid.getRowCount();
        int colMax=this.coldDrinkGrid.getColumnCount();
        int row=0;
        int col=0;

        for(Soda soda:sodaList){
            if(!((row==rowMax)&&(col==colMax))){
                for(Integer volume:soda.getQuantities()){
                    String s = soda.getName().toLowerCase().replaceAll(" ", "_");
                    String imagePath = "/application/ressources/images/coldDrink/" + s + "_" + volume + ".png";

                    URL imageUrl = getClass().getResource(imagePath);
                    if (imageUrl == null) {
                        System.err.println("❌ Image not found at path: " + imagePath);
                        continue; // Passe au volume suivant
                    }
                    Image image = new Image(imageUrl.toExternalForm());


                    if (image != null) {
                        System.out.println("Image found: " + imagePath);
                        ImageView imageView = new ImageView(image);

                        imageView.setFitHeight(60);
                        imageView.setPreserveRatio(true);

                        VBox vBox = new VBox();
                        Label labelPrix = new Label(""+soda.getQuantityPriceMap().get(volume)+"€");
                        Label labelNumber = new Label(""+row+""+col);
                        vBox.getChildren().addAll(imageView,labelPrix,labelNumber);
                        vBox.setSpacing(5);
                        vBox.setAlignment(javafx.geometry.Pos.CENTER);

                        this.coldDrinkGrid.add(vBox, col, row);

                    } else {
                        System.err.println("Image not found: " + imagePath);
                    }
                    col++;
                    if(col==colMax){
                        col=0;
                        row++;
                    }
                }
            }

        }
    }

    @FXML
    public void onActionZero(ActionEvent actionEvent) {
        onActionNumber(0);
    }

    @FXML
    public void onActionUn(ActionEvent actionEvent) {
        onActionNumber(1);
    }

    @FXML
    public void onActionDeux(ActionEvent actionEvent) {
        onActionNumber(2);
    }

    @FXML
    public void onActionTrois(ActionEvent actionEvent) {
        onActionNumber(3);
    }

    @FXML
    public void onActionQuatre(ActionEvent actionEvent) {
        onActionNumber(4);

    }

    @FXML
    public void onActionCinq(ActionEvent actionEvent) {
        onActionNumber(5);
    }
    @FXML
    public void onActionSix(ActionEvent actionEvent) {
        onActionNumber(6);
    }
    @FXML
    public void onActionSept(ActionEvent actionEvent) {
        onActionNumber(7);
    }
    @FXML
    public void onActionHuit(ActionEvent actionEvent) {
        onActionNumber(8);
    }
    @FXML
    public void onActionNeuf(ActionEvent actionEvent) {
        onActionNumber(9);
    }

    private void onActionNumber(int number) {
        if(this.printNumber.getText().length()<2){
            this.printNumber.setText(this.printNumber.getText() + number);
        }
    }


    @FXML
    public void onActionBack(ActionEvent actionEvent) {
        if (!this.printNumber.getText().isEmpty()) {
            this.printNumber.setText(this.printNumber.getText().substring(0, this.printNumber.getText().length() - 1));
        }
    }
    @FXML
    public void onActionSubmit(ActionEvent actionEvent) throws InterruptedException {

        String number = this.printNumber.getText();
        System.out.println("Submit -> Number: " + number);
        System.out.println("Is number valid: " + checkNumber(number));
        if(!checkNumber(number)){
            this.printNumber.setText("");
        }else{
            int row = Integer.parseInt(number.substring(0, 1));
            int col = Integer.parseInt(number.substring(1, 2));
            System.out.println("Row: " + row);
            System.out.println("Col: " + col);

            VBox vbox = getNodeFromGridPane(coldDrinkGrid, row, col);
            if (vbox != null) {
                System.out.println("VBox trouvé à la position "+row+","+col);

                ImageView imageView = (ImageView) vbox.getChildren().get(0);
                String s = imageView.getImage().getUrl().substring(imageView.getImage().getUrl().lastIndexOf("/")+1);
                System.out.println(s);
                Pair<String,Integer> res =  nameFromImage(s);
                Product product=MainApp.productController.getProductsModel().getProductByName(res.getKey());
                System.out.println(product.toString());
                MainApp.panier.addProduct(product, res.getValue(),product.getQuantityPriceMap().get(res.getValue()));
            } else {
                System.out.println("Aucun VBox trouvé à la position "+row+","+col);
            }

        }


    }


    private boolean checkNumber(String number){
        if (number != null && number.length() == 2 && Character.isDigit(number.charAt(0)) && Character.isDigit(number.charAt(1))) {
            int firstDigit = Character.getNumericValue(number.charAt(0));
            int secondDigit = Character.getNumericValue(number.charAt(1));

            return firstDigit >= 0 && firstDigit <= 6 && secondDigit >= 0 && secondDigit <= 4;
        }
        return false;

    }


    private VBox getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);

            if (nodeRow == null) nodeRow = 0;
            if (nodeCol == null) nodeCol = 0;

            if (nodeRow == row && nodeCol == col && node instanceof VBox) {
                return (VBox) node;
            }
        }
        return null;
    }

    private Pair<String,Integer> nameFromImage(String imagePath){
        String baseName = imagePath.substring(0, imagePath.lastIndexOf('.'));

        String[] parts = baseName.split("_");

        String volumeStr = parts[parts.length - 1];

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (i > 0) {
                nameBuilder.append(" ");
            }
            nameBuilder.append(parts[i]);
        }
        String sodaName = nameBuilder.toString();

        // Résultats
        System.out.println("Nom de la boisson : " + sodaName);
        System.out.println("Volume : " + volumeStr + " ml");

        return new Pair<>(sodaName,Integer.parseInt(volumeStr));

    }

}


