import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextVendingMachineInterface implements VendingMachineInterface {

  private final SimpleCalculator calculator = new SimpleCalculator();
  private final List<Snack> Snacks = new ArrayList<Snack>(25);
  private int price_snack;
  private CoinBundle change;
  private int type;
  private int[] OldEnteredCoins = new int[6];
  Scanner scanner1 = new Scanner(System.in);

  public void AddSnacks() {
    int count = 1;
    for (int i = 0; i < 25; i++) {
      Snacks.add(i, new Snack("Snack" + count, (int) Math.floor(Math.random() * 10), (int) Math.floor(Math.random() * 100000)));
      count++;
    }
  }

  @Override
  public void displayProducts() {
    this.AddSnacks();
    System.out.println(" *********************************************");
    System.out.println("     WELCOME TO THE Snack Machine            ");
    System.out.println(" *********************************************");
    System.out.println("            Snacks available:               ");
    System.out.println("         [name ,quantity ,price]                                        ");
    int count = 0;
    for (int i = 0; i < Snacks.size(); i++) {
      if (Snacks.get(i).getSnackPrice() > 100) {
        int convertToDollar = Snacks.get(i).getSnackPrice() / 100;
        System.out.print("   [  " + Snacks.get(i).getSnackName() + " -  " + Snacks.get(i).getSnackQuantity() + " - " + convertToDollar + " $ ]  ");
      } else {
        System.out.print("   [  " + Snacks.get(i).getSnackName() + " -  " + Snacks.get(i).getSnackQuantity() + " - " + Snacks.get(i).getSnackPrice() + " ]  ");
      }
      count++;

      if (count == 5) {
        count = 0;
        System.out.println("");
      }

    }
    System.out.println("                                              ");
    System.out.println(" Please select your Snack: ");
  }


  @Override
  public void selectSnack() {

    String selectedSnack = scanner1.nextLine();
    int index = Integer.parseInt(selectedSnack);

    if ((index >= 1 && index <= 25) && Snacks.get(index - 1).getSnackQuantity() != 0) {
      this.isSnackAvailable(true);
      System.out.println(" , It Price is " + Snacks.get(index - 1).getSnackPrice() + " cents ");
      this.price_snack = Snacks.get(index - 1).getSnackPrice();
    } else {
      this.isSnackAvailable(false);
      selectSnack();
    }
  }

  @Override
  public void isSnackAvailable(boolean x) {
    if (x == true)
      System.out.print("The Snack is Available ");
    else
      System.out.println("The Snack is Not Available , Please Select another Snack");
  }

  @Override
  public void chooseMethodOfPayment() {
    System.out.println(" ***********************************************");
    System.out.println("     Choose The Input Method : ");
    System.out.println("     #1- CoinSlot");
    System.out.println("     #2- CardSlot ");
    System.out.println(" *********************************************");
    System.out.println("                                              ");
  }


  @Override
  public void ChooseTheInputMethod() {
    int type = Integer.parseInt(scanner1.nextLine());
    this.type = type;
  }

  @Override
  public void displayEnterCoinsMessage() {
    if (type == 1) {
      System.out.println(" Please enter coins as follows: ");
      System.out.println(" num of 10 cents coins,num of 20 cents coins,num of 50 cents coins, num of 1$ coins , num of 20$ coins ,num of 50$ coins  ");
      System.out.println("                                              ");
      System.out.println(" Example: If you would like to enter 2 (50 cents ) coins: 0,0,2,0,0,0");
      System.out.println("Plese enter coins:");
    } else if (type == 2) {
      System.out.println(" How much there is in the card ? (in Dollar ) ");
    } else {
      System.out.println("This is Error ,,,, Ejection");
      System.exit(0);
    }

  }

  @Override
  public void enterCoins() {
    if (type == 1) {
      String userEnteredCoins = scanner1.nextLine();
      int[] enteredCoins = SimpleCalculator.parseCoins(userEnteredCoins);
      for (int i =0;i<enteredCoins.length;i++) {
        System.out.println(enteredCoins[i]);
      }

      int[] result = SimpleCalculator.SumOldCoinsAndCurrentCoins(OldEnteredCoins, enteredCoins);
      OldEnteredCoins = enteredCoins;

      VendingMachineRequest request = new VendingMachineRequest(price_snack, result);
      if (request.checkTheRequest() == false) {
        this.thePriceHigherThanCreditMessage(request.enteredCoins.getTotal());
        enterCoins();
      } else if (request.checkTheRequest() == true)
        change = calculator.calculateChange(request);

    } else if (type == 2) {

      int CardMoney = Integer.parseInt(scanner1.nextLine());
      VendingMachineRequest request = new VendingMachineRequest(price_snack, CardMoney);
      if (request.checkTheRequest() == false) {
        this.thePriceHigherThanCreditMessage(request.totalFromCard.getTotal());
        this.ejectionTheCoins();
      } else if (request.checkTheRequest() == true)
        change = calculator.calculateChange(request);
    }
  }

  @Override
  public void thePriceHigherThanCreditMessage(int enteredCoins) {
    int total = enteredCoins;
    String TotalInScreen = (total >= 100) ? total / 100 + "$" : total + "c";
    System.out.println(" ***********************************************");
    System.out.println("     The Price higher Than Credit (" + TotalInScreen + ")");
    System.out.println(" *********************************************");
    System.out.println("     Please Enter Additional Coins");
    System.out.println("                                              ");
  }

  @Override
  public void completeThisProcessOrNotMessage() {
    System.out.println(" ***********************************************");
    System.out.println("     Plese Enter 1 To Complete This Process OR 0 To End");
    System.out.println(" *********************************************");
    System.out.println("                                              ");
    int x = Integer.parseInt(scanner1.nextLine());
    if (x == 0)
      this.ejectionTheCoins();
  }

  @Override
  public void displayChangeMessage() {
    System.out.println("                                              ");
    System.out.println("Your change is :" + change.getTotal() + "cents splitted as follows: ");
    System.out.println("    50$ coins: " + change.number50DollarCoins);
    System.out.println("    20$ coins: " + change.number20DollarCoins);
    System.out.println("    1$ coins: " + change.number1DollarCoins);
    System.out.println("    50 cents coins: " + change.number50CentsCoins);
    System.out.println("    20 cents coins: " + change.number20CentsCoins);
    System.out.println("    10 cents coins: " + change.number10CentsCoins);
  }

  @Override
  public void ejectionTheCoins() {
    System.exit(0);
  }

}
