package editor.composants.pizza.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SpiralPane extends Pane {
    /** Angle between successive items, in degrees. */
    private final DoubleProperty angleStep = new SimpleDoubleProperty(this, "angleStep", 30);
    /** Radial distance increment between successive items. */
    private final DoubleProperty radiusStep = new SimpleDoubleProperty(this, "radiusStep", 20);

    public final double getAngleStep() { return angleStep.get(); }
    public final void setAngleStep(double v) { angleStep.set(v); }
    public final DoubleProperty angleStepProperty() { return angleStep; }

    public final double getRadiusStep() { return radiusStep.get(); }
    public final void setRadiusStep(double v) { radiusStep.set(v); }
    public final DoubleProperty radiusStepProperty() { return radiusStep; }

    @Override
    protected void layoutChildren() {
        ObservableList<Node> children = getChildren();
        double cx = getWidth() / 2.0;
        double cy = getHeight() / 2.0;

        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            double angleDeg = i * getAngleStep();
            double angle = Math.toRadians(angleDeg);
            double radius = i * getRadiusStep();

            double childW = child.prefWidth(-1);
            double childH = child.prefHeight(-1);

            double x = cx + Math.cos(angle) * radius - childW / 2;
            double y = cy + Math.sin(angle) * radius - childH / 2;

            child.resizeRelocate(x, y, childW, childH);
        }
    }
}
