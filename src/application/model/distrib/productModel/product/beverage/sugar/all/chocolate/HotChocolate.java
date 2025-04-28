package application.model.distrib.productModel.product.beverage.sugar.all.chocolate;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;
import java.util.ArrayList;
import java.util.HashSet;

public class HotChocolate extends CanAddSugar implements NeedPreparation {
    //on peut choisir un seul arome mais plusieurs toppings

    private Boolean withMilk;
    private Aroma aroma;
    private HashSet<Toping> topings;
    public HotChocolate(String name) {
        super(name);
        this.topings=new HashSet<>();
    }

    @Override
    public int getPreparationTime(Integer quantity) {
        if(this.getQuantityPriceMap().containsKey(quantity)){
            return quantity / 100 * 15;
        }else {
            System.out.println("ERROR : getPreparationTime HotChocolate -> la quantité n'existe pas");
            return 0;
        }
    }

    @Override
    public int getPrice(){
        return 0;
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
}
