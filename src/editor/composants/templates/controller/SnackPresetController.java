package editor.composants.templates.controller;

import editor.composants.savegarde.SaveLoad;
import javafx.fxml.FXML;

public class SnackPresetController  {

    public SnackPresetController() {
    }

    @FXML
    public void initialize() {
        // Initialize the controller if needed
        System.out.println("SnackPresetController initialized");
    }
    public SaveLoad getCurrentLayout() {
        // Return the current layout as a SaveLoad object
        // This method should be implemented to return the current layout
        System.out.println("Getting current layout Snack");
        return new SaveLoad("Snack"); // Placeholder, replace with actual implementation
    }

    public void applyLayout(SaveLoad saveLoad) {
        // Apply the layout from the SaveLoad object
        // This method should be implemented to set the layout based on the provided SaveLoad object
        System.out.println("Applying layout: " + saveLoad.getNameTemplate());
    }
}
