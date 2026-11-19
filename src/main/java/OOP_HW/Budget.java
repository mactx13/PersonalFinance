package OOP_HW;

import java.math.BigDecimal;


public class Budget {
    private BigDecimal limit;
    private Category category;

    public Budget(BigDecimal limit, Category category) {
        this.limit = limit;
        this.category = category;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isExceeded(BigDecimal spentAmount) { // проверяем выход за установленный лимит в категории
        return spentAmount.compareTo(limit) > 0;
    }
}
