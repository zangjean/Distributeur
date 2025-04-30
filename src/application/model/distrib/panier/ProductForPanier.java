package application.model.distrib.panier;

public class ProductForPanier {

    private String nameProduct;
    private int quantityML;
    private double price;
    private int askingQuantity;
    private String originaClassName;

    public ProductForPanier(String nameProduct, int quantityML, double price) {
        this.nameProduct = nameProduct;
        this.quantityML = quantityML;
        this.price = price;
        this.askingQuantity = 1;
    }

    public String getOriginaClassName() {
        return originaClassName;
    }

    public void setOriginaClassName(String originaClassName) {
        this.originaClassName = originaClassName;
    }
    public String getNameProduct() {
        return nameProduct;
    }

    public int getQuantityML() {
        return quantityML;
    }

    public double getPrice() {
        return price;
    }

    public int getAskingQuantity() {
        return askingQuantity;
    }

    public void setAskingQuantity(int askingQuantity) {
        this.askingQuantity = askingQuantity;
    }
}
