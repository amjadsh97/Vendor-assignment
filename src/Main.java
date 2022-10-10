public class Main {
  public static void main(String[] args) {

    VendingMachineInterface machineInterface = new TextVendingMachineInterface();

    machineInterface.displayProducts();

    machineInterface.selectSnack();

    machineInterface.chooseMethodOfPayment();

    machineInterface.ChooseTheInputMethod();

    machineInterface.displayEnterCoinsMessage();

    machineInterface.enterCoins();

    machineInterface.completeThisProcessOrNotMessage();

    machineInterface.displayChangeMessage();

  }
}