package recipeditor.recipe;

public class Ingredient {
    public int amount;
    public String name;
    public String unit;

    public Ingredient(String name, String amount, String unit){
        this.name = name;
        this.amount = Integer.parseInt(amount);
        this.unit = unit;
    }
}
