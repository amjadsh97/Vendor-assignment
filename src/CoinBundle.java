public class CoinBundle implements PaymentState {
  public int number10CentsCoins;
  public int number20CentsCoins;
  public int number50CentsCoins;
  public int number1DollarCoins;
  public int number20DollarCoins;
  public int number50DollarCoins;

  public CoinBundle(int... enteredCoins) {
    this.number10CentsCoins = enteredCoins[0];
    this.number20CentsCoins = enteredCoins[1];
    this.number50CentsCoins = enteredCoins[2];
    this.number1DollarCoins = enteredCoins[3];
    this.number20DollarCoins = enteredCoins[4];
    this.number50DollarCoins = enteredCoins[5];
  }

  @Override
  public int getTotal() {
    int total = 0;
    total = total + this.number10CentsCoins * Coin.TEN_CENTS.getValue();
    total = total + this.number20CentsCoins * Coin.TWENTY_CENTS.getValue();
    total = total + this.number50CentsCoins * Coin.FIFTY_CENTS.getValue();
    total = total + this.number1DollarCoins * Coin.ONE_DOLLAR.getValue();
    total = total + this.number20DollarCoins * Coin.TWENTY_DOLLAR.getValue();
    total = total + this.number50DollarCoins * Coin.FIFTY_DOLLAR.getValue();
    return total;
  }

  @Override
  public int getState() {
    return 1;
  }

}
