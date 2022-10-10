public enum Coin {
  TEN_CENTS(10), TWENTY_CENTS(20), FIFTY_CENTS(50), ONE_DOLLAR(100), TWENTY_DOLLAR(2000), FIFTY_DOLLAR(5000);

  private final int value;

  Coin(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}