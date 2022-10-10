public class Snack {
  private String snackName;
  private int snackQuantity;
  private int snackPrice;

  public Snack(String snackName, int snackQuantity, int snackPrice) {
    this.snackName = snackName;
    this.snackQuantity = snackQuantity;
    this.snackPrice = snackPrice;
  }

  public String getSnackName() {
    return snackName;
  }

  public void setSnack_name(String snackName) {
    this.snackName = snackName;
  }

  public int getSnackQuantity() {
    return snackQuantity;
  }

  public void setSnack_quantity(int snackQuantity) {
    this.snackQuantity = snackQuantity;
  }

  public int getSnackPrice() {
    return snackPrice;
  }

  public void setSnack_price(int snackPrice) {
    this.snackPrice = snackPrice;
  }

}
