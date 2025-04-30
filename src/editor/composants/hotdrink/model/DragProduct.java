package editor.composants.hotdrink.model;

import editor.composants.templates.composants.hotdrink.model.ProductCard;

public class DragProduct

{
    private static ProductCard currentProduct;

    public static void setCurrentProduct(ProductCard product) {
        currentProduct = product;
    }

    public static ProductCard getCurrentProduct() {
        return currentProduct;
    }

    public static void clear() {
        currentProduct = null;
    }
}
