package editor.composants.templates.model;

public class Template {
    private final String name;
    private final String fxmlPath;

    public Template(String name, String fxmlPath) {
        this.name = name;
        this.fxmlPath = fxmlPath;
    }

    public String getName() {
        return name;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    @Override
    public String toString() {
        return name;
    }
}
