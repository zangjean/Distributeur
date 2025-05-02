package application.utils;

import java.io.File;
import java.io.IOException;

/**
 * Classe utilitaire dédiée à la gestion des fichiers de sauvegarde (création si non existant).
 */
public abstract class UtilsSaveLoad {

    /** Répertoire de base où seront enregistrés les fichiers. */
    private static final String baseFilePath = "src/application/saveFiles/";

    /**
     * Crée un fichier dans le répertoire de sauvegarde si celui-ci n'existe pas déjà.
     * Crée également les répertoires parents au besoin.
     *
     * @param filepath Nom relatif du fichier (ex: "users.dat", "faveprod.dat").
     * @return Le chemin absolu vers le fichier créé ou existant.
     */
    public static String createNewFileIfNotExist(String filepath) {
        String filePathFull = baseFilePath + filepath;
        File file = new File(filePathFull);

        if (!file.exists()) {
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
