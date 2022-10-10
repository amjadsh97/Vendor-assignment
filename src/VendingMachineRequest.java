public class VendingMachineRequest {
  public int priceProduct;
  public CoinBundle enteredCoins;
  public Card totalFromCard;
  public int PaymentState;

  public VendingMachineRequest(int priceProduct, int... enteredCoins) {
    this.priceProduct = priceProduct;
    this.enteredCoins = new CoinBundle(enteredCoins);
    this.PaymentState = this.enteredCoins.getState();
  }

  public VendingMachineRequest(int priceProduct, int totalFromCard) {
    this.priceProduct = priceProduct;
    this.totalFromCard = new Card(totalFromCard);
    this.PaymentState = this.totalFromCard.getState();
  }

  public boolean checkTheRequest() {
    int total = 0;
    int RequestType = this.PaymentState;

    if (RequestType == 1)
      total = this.enteredCoins.getTotal();
    else if (RequestType == 2) {
      total = this.totalFromCard.getTotal();
    }

    if (priceProduct > total) {
      return false;
    }
    return true;
  }
}