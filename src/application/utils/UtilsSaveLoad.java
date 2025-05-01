package application.utils;

import java.io.File;
import java.io.IOException;

public abstract class UtilsSaveLoad {
    private static final String baseFilePath = "src/application/saveFiles/";

    public static String createNewFileIfNotExist(String filepath) {
        String filePathFull = baseFilePath + filepath;
        File file = new File(baseFilePath+filepath);
        if (!file.exists()) {
            // Créer les répertoires parents si nécessaire
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try {
                if (file.createNewFile()) {
                    System.out.println("[INFO] Fichier " + filepath + " créé automatiquement.");
                }
            } catch (IOException e) {
                System.err.println("[ERROR] Impossible de créer " + filepath);
                e.printStackTrace();
            }
        }
        return filePathFull;
    }

}
