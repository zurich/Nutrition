package ca.badenhorst.nutrition.model;

/**
 * Created with IntelliJ IDEA.
 * User: wade
 * Date: 17/02/14
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleSize {
  String ndbNo;
  double amount;
  String description;
  double weightGrams;

  public String getNdbNo() {
    return ndbNo;
  }

  public void setNdbNo(String ndbNo) {
    this.ndbNo = ndbNo;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getWeightGrams() {
    return weightGrams;
  }

  public void setWeightGrams(double weightGrams) {
    this.weightGrams = weightGrams;
  }

  @Override
  public String toString() {
    return "ca.badenhorst.food.SampleSize{" +
        "ndbNo='" + ndbNo + '\'' +
        ", amount=" + amount +
        ", description='" + description + '\'' +
        ", weightGrams=" + weightGrams +
        '}';
  }
}
