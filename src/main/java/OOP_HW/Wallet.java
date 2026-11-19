package OOP_HW;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private List <Transaction> transactions = new ArrayList<>();
    private List <Category> categories = new ArrayList<>();
    private List <Budget> budgets = new ArrayList<>();

    public List <Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public List <Category> getCategories() {
        return List.copyOf(categories);
    }

    public List<Budget> getBudgets() {
        return List.copyOf(budgets);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public BigDecimal calculateBalance() {
        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getSignedAmount());
        }

        return balance;
    }


}
