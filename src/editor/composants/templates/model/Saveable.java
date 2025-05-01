package editor.composants.templates.model;

import editor.composants.savegarde.SaveLoad;

public interface Saveable {

    SaveLoad getLayout();

    void applySaveLoad(SaveLoad saveLoad);


}
