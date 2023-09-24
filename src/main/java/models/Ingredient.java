package models;

public class Ingredient {

    private String name;
    private String description;
    private int calPer100Unit;
    private String unitOfMeasurement;

    //---------------------
    // Constructor Methods
    //---------------------

    public Ingredient(String name, String description, int calPer100Unit, String unitOfMeasurement){
        setName(name);
        setDescription(description);
        setCalPer100Unit(calPer100Unit);
        setUnitOfMeasurement(unitOfMeasurement);
    }

    //---------------------
    // Setter Methods
    //---------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public void setCalPer100Unit(int calPer100Unit) {
        this.calPer100Unit = calPer100Unit;
    }

    //---------------------
    // Getter Methods
    //---------------------

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCalPer100Unit() {
        return calPer100Unit;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    //---------------------
    // Handling Methods
    //---------------------

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", calPer100Unit=" + calPer100Unit +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}
