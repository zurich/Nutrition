package ca.badenhorst.nutrition.model;

/**
 * Created with IntelliJ IDEA.
 * User: wade
 * Date: 17/02/14
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Nutrient {
  String nutrNo;
  String description;
  String tagname;
  double value;
  String units;   // g,kcal,mg,kJ,µg,IU

  @Override
  public String toString() {
    return "ca.badenhorst.food.Nutrient{" +
        "nutrNo='" + nutrNo + '\'' +
        ", description='" + description + '\'' +
        ", tagname='" + tagname + '\'' +
        ", value=" + value +
        ", units='" + units + '\'' +
        '}';
  }

  public String getNutrNo() {
    return nutrNo;
  }

  public void setNutrNo(String nutrNo) {
    this.nutrNo = nutrNo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTagname() {
    return tagname;
  }

  public void setTagname(String tagname) {
    this.tagname = tagname;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }
}
