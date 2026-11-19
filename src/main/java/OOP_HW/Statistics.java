package OOP_HW;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Statistics {


    public BigDecimal getTotalIncome(Wallet wallet) {
        List<Transaction> transactions = wallet.getTransactions();
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getSignedAmount().compareTo(BigDecimal.ZERO) > 0) {
                totalIncome = totalIncome.add(transaction.getSignedAmount());
            }
        }
        return totalIncome;

    }

    public BigDecimal getTotalExpenses(Wallet wallet) {
        List<Transaction> transactions = wallet.getTransactions();
        BigDecimal totalExpenses = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getSignedAmount().compareTo(BigDecimal.ZERO) < 0) {
                totalExpenses = totalExpenses.add(transaction.getSignedAmount());
            }
        }
        return totalExpenses.abs();

    }

    public Map<Category, BigDecimal> getExpensesByCategory(Wallet wallet) {
        Map<Category, BigDecimal> expensesByCategory = new HashMap<>();
        List<Transaction> transactions = wallet.getTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getSignedAmount().compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal amount = transaction.getSignedAmount().abs();
                expensesByCategory.put(
                        transaction.getCategory(),
                        expensesByCategory.getOrDefault(transaction.getCategory(), BigDecimal.ZERO)
                                .add(amount)
                );
            }
            }
        return expensesByCategory;
        }

    public BigDecimal getBalance(Wallet wallet) {
        return wallet.calculateBalance();
    }
}
