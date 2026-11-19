package OOP_HW;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Income extends Transaction{

    public Income() {
        super();
    }

    public Income(BigDecimal amount, LocalDate date,
                  String description, Category category) {
        super(amount, date, description, category);
    }
    @Override
    public BigDecimal getSignedAmount() {
        return getAmount();
    }
}
