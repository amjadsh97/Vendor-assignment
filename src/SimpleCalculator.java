import java.util.Scanner;

public class SimpleCalculator implements Calculator {

  Scanner scanner2 = new Scanner(System.in);

  public static int[] parseCoins(String coins) {
    String[] numberCoinsInText = coins.split(",");
    int[] result = new int[numberCoinsInText.length];
    for (int index = 0; index < numberCoinsInText.length; index++) {
      result[index] = Integer.parseInt(numberCoinsInText[index]);
    }
    return result;
  }

  public static int[] SumOldCoinsAndCurrentCoins(int[] old, int[] current) {
    for (int i = 0; i < current.length; i++) {
      current[i] = current[i] + old[i];
    }
    return current;
  }

  @Override
  public int calculateTotal(PaymentState PS) {
    return PS.getTotal();
  }

  public CoinBundle calculateChange(VendingMachineRequest request) {
    int total = (request.PaymentState == 1) ? this.calculateTotal(request.enteredCoins) : this.calculateTotal(request.totalFromCard);
    int totalChange = total - request.priceProduct;
    return this.calculateChange(totalChange);
  }

  @Override
  public CoinBundle calculateChange(int amountMoneyToReturn) {
    CoinBundle change = new CoinBundle(new int[6]);
    int remainingAmount = amountMoneyToReturn;

    change.number50DollarCoins = remainingAmount / Coin.FIFTY_DOLLAR.getValue();
    remainingAmount = remainingAmount % Coin.FIFTY_DOLLAR.getValue();

    change.number20DollarCoins = remainingAmount / Coin.TWENTY_DOLLAR.getValue();
    remainingAmount = remainingAmount % Coin.TWENTY_DOLLAR.getValue();

    change.number1DollarCoins = remainingAmount / Coin.ONE_DOLLAR.getValue();
    remainingAmount = remainingAmount % Coin.ONE_DOLLAR.getValue();

    change.number50CentsCoins = remainingAmount / Coin.FIFTY_CENTS.getValue();
    remainingAmount = remainingAmount % Coin.FIFTY_CENTS.getValue();

    change.number20CentsCoins = remainingAmount / Coin.TWENTY_CENTS.getValue();
    remainingAmount = remainingAmount % Coin.TWENTY_CENTS.getValue();

    change.number10CentsCoins = remainingAmount / Coin.TEN_CENTS.getValue();

    return change;
  }
}
