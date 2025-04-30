package editor.composants.savegarde;

import editor.composants.templates.composants.hotdrink.model.ProductCard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SaveLoad implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nameTemplate;
    private Map<String, ProductCard> slots;

    public SaveLoad(String nameTemplate, Map<String, ProductCard> slots)
    {
        this.nameTemplate = nameTemplate;
        this.slots = (slots != null) ? slots : new HashMap<>();
    }
    public SaveLoad(String nameTemplate) {
        this(nameTemplate, null);
    }

    public String getNameTemplate() {
        return nameTemplate;
    }
    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }
    public Map<String, ProductCard> getSlots() {
        return slots;
    }
    public void setSlots(Map<String, ProductCard> slots) {
        this.slots = slots;
    }

    public void addSlot(String slotName, ProductCard productCard) {
        this.slots.put(slotName, productCard);
    }
    public void removeSlot(String slotName) {
        this.slots.remove(slotName);
    }

    public ProductCard getSlot(String slotName) {
        return this.slots.get(slotName);
    }
    public boolean containsSlot(String slotName) {
        return this.slots.containsKey(slotName);
    }
    public void clearSlots() {
        this.slots.clear();
    }

}
