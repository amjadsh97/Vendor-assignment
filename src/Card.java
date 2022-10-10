public final class Card implements PaymentState {

  private final int enteredCoins;

  public Card(int enteredCoins) {
    this.enteredCoins = this.ConvertToCents(enteredCoins);
  }

  @Override
  public int getTotal() {
    return enteredCoins;
  }

  @Override
  public int getState() {
    return 2;
  }

  public int ConvertToCents(int enteredCoins) {
    return enteredCoins * 100;
  }
}
