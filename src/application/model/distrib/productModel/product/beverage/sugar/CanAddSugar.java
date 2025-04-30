package application.model.distrib.productModel.product.beverage.sugar;


import application.model.distrib.productModel.product.beverage.Beverage;

public abstract class CanAddSugar extends Beverage {

    private int actualDose;
    public CanAddSugar(String name) {
        super(name);
        this.actualDose = 0;
    }

    public int getActualDose() {
        return actualDose;
    }

    private void setActualDose(int actualDose) {
        if(0<=actualDose || actualDose<6){
            this.actualDose = actualDose;
        }else {
            System.out.println("ERROR: setActualDose -> sugar between 0 - 5");
        }
    }
    
    public void addSugar(int add){
        int dose = this.actualDose + add;
        if(dose >5){
            setActualDose(5);
        } else if (dose < 0) {
            setActualDose(0);
            
        } else {
            setActualDose(dose);
        }
    }

    public abstract double getPrice();
}
