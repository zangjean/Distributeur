package editor.composants.templates.hotdrink.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class HotdrinkController {

    @FXML
    private GridPane root;
    @FXML
    private StackPane stackpane1, stackpane2, stackpane3, stackpane4 ;

    @FXML
    private ImageView imgView1, imgView2, imgView3, imgView4 ;

    public HotdrinkController(){

    }

    @FXML
    public void initialize() {
        // Initialize the controller here if needed
        System.out.println("HotdrinkController initialized");

        // bind stackpane1 to the root
        stackpane1.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        stackpane1.prefHeightProperty().bind(root.heightProperty().multiply(0.25));
        stackpane2.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        stackpane2.prefHeightProperty().bind(root.heightProperty().multiply(0.25));
        stackpane3.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        stackpane3.prefHeightProperty().bind(root.heightProperty().multiply(0.25));
        stackpane4.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

        StackPane [][] stackPanes;

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
            }
        }


        String path1 = "/editor/composants/templates/hotdrink/ressources/images/cafe.jpg";
        addImage(imgView1, stackpane1, path1);

        String path2 =  "/editor/composants/templates/hotdrink/ressources/images/cafe.jpg";
        addImage(imgView2, stackpane2, path2 );

        String path3 =  "/editor/composants/templates/hotdrink/ressources/images/cafe.jpg";
        addImage(imgView3, stackpane3, path3 );

        String path4 =  "/editor/composants/templates/hotdrink/ressources/images/cafe.jpg";
        addImage(imgView4, stackpane4, path4 );



    }

    private void addImage(ImageView imgView1, StackPane stackpane1, String pathImage) {
        //

        Image image = new Image(getClass().getResource(pathImage).toExternalForm());
        imgView1.setImage(image);


        // bind the imageView to the stackpane
        imgView1.fitWidthProperty().bind(stackpane1.widthProperty());
        imgView1.fitHeightProperty().bind(stackpane1.heightProperty());
    }
}
