public interface VendingMachineInterface {
  void displayProducts();

  void selectSnack();

  void isSnackAvailable(boolean x);

  void chooseMethodOfPayment();

  void ChooseTheInputMethod();

  void displayEnterCoinsMessage();

  void enterCoins();

  void thePriceHigherThanCreditMessage(int enteredCoins);

  void completeThisProcessOrNotMessage();

  void displayChangeMessage();

  void ejectionTheCoins();
}
