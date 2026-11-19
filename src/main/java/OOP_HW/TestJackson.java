
package OOP_HW;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestJackson {

    public static void main(String[] args) throws Exception {

        // 1. Создаём пользователя
        User user = new User("Max", "12345");

        // 2. Получаем его кошелёк
        Wallet wallet = user.getWallet();

        // 3. Создаём категории
        Category incomeCategory = new Category("Работа", Category.CategoryType.INCOME);
        Category expenseCategory = new Category("Продукты", Category.CategoryType.EXPENSE);

        // 4. Добавляем категории в кошелёк
        wallet.addCategory(incomeCategory);
        wallet.addCategory(expenseCategory);

        // 5. Создаём доход и расход
        Income income = new Income(
                new BigDecimal("100000"),
                LocalDate.of(2026, 11, 19),
                "Зарплата",
                incomeCategory
        );

        Expense expense = new Expense(
                new BigDecimal("15000"),
                LocalDate.of(2026, 11, 19),
                "Покупки",
                expenseCategory
        );

        // 6. Добавляем транзакции
        wallet.addTransaction(income);
        wallet.addTransaction(expense);

        // 7. Создаём список пользователей
        List<User> users = new ArrayList<>();
        users.add(user);

        // 8. Сохраняем пользователей
        UserStorage storage = new UserStorage();
        storage.saveUsers(users);

        System.out.println("Данные сохранены!");

        // 9. Загружаем пользователей
        List<User> loadedUsers = storage.loadUsers();

        // 10. Получаем загруженный кошелёк
        User loadedUser = loadedUsers.get(0);
        Wallet loadedWallet = loadedUser.getWallet();

        // 11. Выводим результаты
        System.out.println("\nПользователь: " + loadedUser.getLogin());
        System.out.println("Количество транзакций: " + loadedWallet.getTransactions().size());
        System.out.println("Баланс: " + loadedWallet.calculateBalance());

        System.out.println("\nСписок транзакций:");

        for (Transaction transaction : loadedWallet.getTransactions()) {
            System.out.println(
                    transaction.getClass().getSimpleName()
                            + " | "
                            + transaction.getDescription()
                            + " | "
                            + transaction.getAmount()
            );
        }
    }
}
