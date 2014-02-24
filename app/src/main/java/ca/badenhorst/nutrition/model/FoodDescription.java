package ca.badenhorst.nutrition.model;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wade
 * Date: 17/02/14
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoodDescription {


    private String ndb_no;
    private String food_group_desc;
    private String short_desc;
    private String long_desc;
    private List<Nutrient> nutrients = null; // base nutrients are normalized for 100 grams of the particular food
    private List<SampleSize> sampleSizes = null;

    public List<SampleSize> getSampleSizes() {
        return sampleSizes;
    }

    public void setSampleSizes(List<SampleSize> sampleSizes) {
        this.sampleSizes = sampleSizes;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }


    public void setNdb_no(String ndb_no) {
        this.ndb_no = ndb_no;
    }

    public void setFood_group_desc(String food_group_desc) {
        this.food_group_desc = food_group_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public FoodDescription() {
    }

    @Override
    public String toString() {
        return "FoodDescription{" +
                "ndb_no='" + ndb_no + '\'' +
                ", food_group_desc='" + food_group_desc + '\'' +
                ", short_desc='" + short_desc + '\'' +
                ", long_desc='" + long_desc + '\'' +
                ", nutrients=" + nutrients +
                ", sampleSizes=" + sampleSizes +
                '}';
    }

    public String getNdb_no() {
        return ndb_no;
    }

    public String getFood_group_desc() {
        return food_group_desc;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public String getLong_desc() {
        return long_desc;
    }
}
