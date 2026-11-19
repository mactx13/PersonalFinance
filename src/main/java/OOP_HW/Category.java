package OOP_HW;

import java.util.Objects;

public class Category {
    private String name;
    private CategoryType type;

    public Category() {
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Category category = (Category) obj;

        return name.equals(category.name)
                && type == category.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }
    public enum CategoryType { INCOME, EXPENSE }

    public String getName() {
        return name;
    }
    public CategoryType getType() {
        return type;
    }
}
