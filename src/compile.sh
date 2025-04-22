#!/bin/bash

### Compilation du cours de POO-IHM
### Ce fichier devrait se trouver dans un dossier nommé "src". Ce dossier devrait aussi contenir le dossier poo_ihm2.

## On suppose que l'on se trouve dans le dossier contenant [...]/src
# Chemin absolu du dossier contenant les fichiers sources
# Exemple : SRC_DIR="/le/chemin/absolu/vers/POO-IHM2_CM_2025/src"

SRC_DIR="/home/jean/Documents/S6/RE/POO/PROJET/distrib/src" # à mettre à jour

# Chemin vers le dossier lib du SDK JavaFX 24.0.1 sur Windows (mettre à jour selon votre installation)
JAVA_FX_LIB_PATH="E:\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib"

cd ${SRC_DIR}

pwd

find . -name '*.java' > tempo

# Compilation des fichiers source avec support JavaFX 24.0.1
while read -r string; do
		string_size=${#string}
		string_without_leading_chars=${string:2:${string_size}}
		set -x
		
		# Attention : la version de Java doit être au moins égale à 21.
		# Pour supporter JavaFX 24.0.1, on ajoute le module-path et les modules nécessaires
		javac --module-path "$JAVA_FX_LIB_PATH" --add-modules javafx.controls,javafx.fxml -Xlint:all -Xdiags:verbose ${string_without_leading_chars}
		{ set +x; } &> /dev/null
done < tempo

rm tempo

# Mise à jour de la configuration
echo "localRootDirectory=file://${SRC_DIR}/" > application/configuration/config.properties
