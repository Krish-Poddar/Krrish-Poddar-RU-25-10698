// Digital Wallet Payment System - Single File Implementation

public class WalletTest {

    // Inner Wallet class
    static class Wallet {
        private double balance;
        private static double cashbackRate = 0.02; // 2% cashback

        public Wallet() {
            this.balance = 0.0;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount + " | New Balance: " + balance);
            } else {
                System.out.println("Deposit failed: Invalid amount");
            }
        }

        public void transferTo(Wallet receiver, double amount) {
            try {
                if (amount <= 0) {
                    throw new IllegalArgumentException("Invalid transfer amount");
                }
                if (balance < amount) {
                    throw new Exception("Insufficient balance");
                }

                balance -= amount;
                receiver.balance += amount;

                double cashback = amount * cashbackRate;
                balance += cashback;

                System.out.println("Transaction successful. Cashback: " + cashback);
            } catch (Exception e) {
                System.out.println("Transaction failed: " + e.getMessage());
            }
        }

        public static void setCashbackRate(double newRate) {
            if (newRate >= 0 && newRate <= 0.1) {
                cashbackRate = newRate;
                System.out.println("Cashback rate updated to: " + (newRate * 100) + "%");
            } else {
                System.out.println("Invalid cashback rate");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Wallet w1 = new Wallet();
        Wallet w2 = new Wallet();

        w1.deposit(1000);
        w1.transferTo(w2, 300);

        System.out.println("Final Balance (W1): " + w1.getBalance());
        System.out.println("Final Balance (W2): " + w2.getBalance());
    }
}