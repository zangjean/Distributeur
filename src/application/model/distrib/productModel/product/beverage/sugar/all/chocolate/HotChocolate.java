package application.model.distrib.productModel.product.beverage.sugar.all.chocolate;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

import java.util.HashSet;

public class HotChocolate extends CanAddSugar{
    //on peut choisir un seul arome mais plusieurs toppings

    private Boolean withMilk;
    private Aroma aroma;
    private HashSet<Toping> topings;


    public HotChocolate(String name) {
        super(name);
        this.topings=new HashSet<>();
    }

    @Override
    public double getPrice(){
        return allTopingsPice()+aromaPrice();
    }

    public Aroma getAroma() {
        return aroma;
    }

    public void setAroma(Aroma aroma) {
        this.aroma = aroma;
    }

    public Boolean getWithMilk() {
        return withMilk;
    }

    public void setWithMilk(Boolean withMilk) {
        this.withMilk = withMilk;
    }

    public HashSet<Toping> getTopings() {
        return topings;
    }

    public void setTopings(HashSet<Toping> topings) {
        this.topings = topings;
    }

    public void addToping( Toping toping){
        this.topings.add(toping);
    }

    public void deleteToping(Toping toping){
        this.topings.remove(toping);
    }

    public int getSize(String size){
        switch (size){
            case "SMALL":return 240;
            case "MEDIUM": return 350;
            case "LARGE":return 450;
        }
        return 0;
    }

    private double allTopingsPice(){
        return 0.20 * this.topings.size();
    }

    private double aromaPrice(){
        if(this.aroma==null){
            return 0;
        }else {
            return 0.30;
        }
    }
}
