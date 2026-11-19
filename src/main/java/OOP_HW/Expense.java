package OOP_HW;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense extends Transaction {

    public Expense() {
        super();
    }
    public Expense(BigDecimal amount, LocalDate date,
                   String description, Category category) {
        super(amount, date, description, category);
    }

    @Override
    public BigDecimal getSignedAmount() {
        return getAmount().negate();
    }
}
