package Datenkapselung;

public class BankAccount {
    private final int accountNumber;
    private double balance;

    public BankAccount(double initialBalance, int accountNumber){
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount){
        if(balance >= 0){
            balance += amount;
        }
    }

    public void withdraw(double amount){
        if(balance >= amount && amount > 0){
            balance -= amount;
        }
    }

    public void getBalance(){
        System.out.println("Account " + accountNumber + " has " + balance);
    }
}
