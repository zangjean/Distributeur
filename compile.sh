#!/bin/bash

### Compilation du cours de POO-IHM
### Ce fichier devrait se trouver dans un dossier nommé "src". Ce dossier devrait aussi contenir le dossier poo_ihm2.

## On suppose que l'on se trouve dans le dossier contenant [...]/src
# Chemin absolu du dossier contenant les fichiers sources
# Exemple : SRC_DIR="/le/chemin/absolu/vers/POO-IHM2_CM_2025/src"
SRC_DIR="/home/jean/Documents/S6/RE/POO/TP/EXO_PERSO/tp2/src" # à mettre à jour

cd ${SRC_DIR}

pwd

find . -name '*.java' > tempo

# Compilation des fichiers source
while read -r string; do
		string_size=${#string}
		string_without_leading_chars=${string:2:${string_size}}
		set -x
		
		# Attention : la version de Java doit être au moins égale à 21.
		# Pour supporter JavaFX, il est recommandé d'utiliser une version JavaFX SDK de Azul : https://www.azul.com/downloads/
		javac -Xlint:all -Xdiags:verbose ${string_without_leading_chars}
		{ set +x; } &> /dev/null
done < tempo

rm tempo

# Mise à jour de la configuration
echo "localRootDirectory=file://${SRC_DIR}/" > exo1/configuration/config.properties


