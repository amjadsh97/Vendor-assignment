public interface Calculator {
    int calculateTotal(PaymentState PS);
    PaymentState calculateChange(int amountMoneyToReturn);
}