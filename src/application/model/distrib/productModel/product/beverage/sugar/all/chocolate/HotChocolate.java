package application.model.distrib.productModel.product.beverage.sugar.all.chocolate;

import main.java.editormvc.model.productModel.product.NeedPreparation;
import main.java.editormvc.model.productModel.product.beverage.sugar.CanAddSugar;
import main.java.editormvc.model.productModel.product.beverage.sugar.all.chocolate.Aroma;
import java.util.ArrayList;
import main.java.editormvc.model.productModel.product.beverage.sugar.all.chocolate.Toping;

public class HotChocolate extends CanAddSugar implements NeedPreparation {
    //on peut choisir un seul arome mais plusieurs toppings

    private Boolean withMilk;
    private Aroma aroma;
    private ArrayList<Toping> topings;
    public HotChocolate(String name) {
        super(name);
        this.topings=new ArrayList<>();
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

    public ArrayList<Toping> getTopings() {
        return topings;
    }

    public void setTopings(ArrayList<Toping> topings) {
        this.topings = topings;
    }

    public void addToping( Toping toping){
        if(!this.topings.contains(toping)){
            this.topings.add(toping);
        }
    }

    public void deleteToping(Toping toping){
        this.topings.remove(toping);
    }
}
