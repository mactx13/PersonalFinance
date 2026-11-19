package OOP_HW;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Income.class, name = "INCOME"),
        @JsonSubTypes.Type(value = Expense.class, name = "EXPENSE")
})

public abstract class Transaction {
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private Category category;

    public Transaction() {
    }
    public Transaction(BigDecimal amount,LocalDate date,
                       String description, Category category) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    @JsonIgnore
    public abstract BigDecimal getSignedAmount();  // доход или трата?

}

