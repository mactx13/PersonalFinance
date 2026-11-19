package OOP_HW;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;

import static OOP_HW.Category.CategoryType.EXPENSE;
import static OOP_HW.Category.CategoryType.INCOME;

public class Main {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        UserStorage storage = new UserStorage();
        try {
            users.addAll(storage.loadUsers());
            System.out.println("Загружено пользователей: " + users.size());
        } catch (IOException e) {
            System.out.println("Ошибка загрузки пользователей: " + e.getMessage());
        }

        User currentUser = null;
        Scanner scanner = new Scanner(System.in);
        Statistics statistics = new Statistics();

        System.out.println("Личные Финансы.");
        int choice0 = -1;
        while (choice0 != 0) {

            System.out.println("1. Авторизация\n" +
                    "2. Регистрация\n" +
                    "0. Выход\n");
            choice0 = Integer.parseInt(scanner.nextLine());
            switch (choice0) {
                case 1: {
                    System.out.println("Авторизация:");
                    System.out.println("Введите логин: ");
                    String login = scanner.nextLine();
                    if (login.isBlank()) {
                        System.out.println("Логин не может быть пустым.");
                        break;
                    }
                    System.out.println("Введите пароль: ");
                    String password = scanner.nextLine();
                    if (password.isBlank()) {
                        System.out.println("Пароль не может быть пустым.");
                        break;
                    }
                    boolean userFound = false;
                    for (User user : users) {
                        if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                            currentUser = user;
                            userFound = true;
                            break;
                        }

                    }
                    if (userFound) {
                        System.out.println("Вход выполнен успешно");
                        Wallet wallet = currentUser.getWallet();

                        int choice = -1;
                        while (choice != 0) {

                            System.out.println("1. Добавить доход\n" +
                                    "2. Добавить расход\n" +
                                    "3. Посмотреть баланс\n" +
                                    "4. Посмотреть статистику\n" +
                                    "5. Посмотреть категории\n" +
                                    "6. Добавить категорию\n" +
                                    "7. Установить бюджет\n" +
                                    "8. Посмотреть бюджеты\n" +
                                    "0. Выход");
                            System.out.println("Выберите действие: ");


                            choice = Integer.parseInt(scanner.nextLine());


                            switch (choice) {
                                case 1: {

                                    List<Category> incomeCategories = new ArrayList<>();
                                    for (Category category : wallet.getCategories()) {
                                        if (category.getType() == INCOME) {
                                            incomeCategories.add(category);
                                        }
                                    }
                                    if (incomeCategories.isEmpty()) {
                                        System.out.println("Сначала добавьте категорию");
                                    } else {

                                        System.out.println("Добавление дохода");
                                        System.out.println("Введите сумму: ");
                                        BigDecimal amount = new BigDecimal(scanner.nextLine());
                                        System.out.println("Введите дату (ГГГГ-ММ-ДД): ");
                                        LocalDate date = LocalDate.parse(scanner.nextLine());
                                        System.out.println("Введите описание: ");
                                        String description = scanner.nextLine();


                                        System.out.println("Выберите категорию, указав номер: ");
                                        int i = 1;
                                        for (Category category : incomeCategories) {
                                            System.out.println(i + " - " + category.getName());
                                            i += 1;
                                        }

                                        int categoryChoice = scanner.nextInt();
                                        scanner.nextLine();
                                        if (categoryChoice < 1 || categoryChoice > incomeCategories.size()) {
                                            System.out.println("Неверный номер категории.");
                                            break;
                                        }
                                        Category category = incomeCategories.get(categoryChoice - 1);

                                        Income income = new Income(amount, date, description, category);
                                        wallet.addTransaction(income);
                                        System.out.println("Доход успешно добавлен.");
                                    }
                                    break;
                                }
                                case 2: {

                                    List<Category> expenseCategories = new ArrayList<>();
                                    for (Category category : wallet.getCategories()) {
                                        if (category.getType() == EXPENSE) {
                                            expenseCategories.add(category);
                                        }
                                    }
                                    if (expenseCategories.isEmpty()) {
                                        System.out.println("Сначала добавьте категорию");
                                    } else {


                                        System.out.println("Добавление расхода");
                                        System.out.println("Введите сумму: ");
                                        BigDecimal amount = new BigDecimal(scanner.nextLine());
                                        System.out.println("Введите дату (ГГГГ-ММ-ДД): ");
                                        LocalDate date = LocalDate.parse(scanner.nextLine());
                                        System.out.println("Введите описание: ");
                                        String description = scanner.nextLine();


                                        System.out.println("Выберите категорию, указав номер: ");
                                        int i = 1;
                                        for (Category category : expenseCategories) {
                                            System.out.println(i + " - " + category.getName());
                                            i += 1;
                                        }

                                        int categoryChoice = scanner.nextInt();
                                        scanner.nextLine();
                                        if (categoryChoice < 1 || categoryChoice > expenseCategories.size()) {
                                            System.out.println("Неверный номер категории.");
                                            break;
                                        }
                                        Category category = expenseCategories.get(categoryChoice - 1);

                                        Expense expense = new Expense(amount, date, description, category);
                                        wallet.addTransaction(expense);
                                        System.out.println("Расход успешно добавлен.");
                                    }
                                    break;
                                }
                                case 3: {
                                    System.out.println("Просмотр баланса");
                                    System.out.println("Текущий баланс: " + statistics.getBalance(wallet));
                                    break;
                                }
                                case 4: {
                                    System.out.println("Просмотр статистики");
                                    System.out.println("Общий доход, расход (1) или по категориям (2) ?");
                                    int statNum = Integer.parseInt(scanner.nextLine());
                                    if (statNum == 1) {
                                        System.out.println("Общий доход: " + statistics.getTotalIncome(wallet));
                                        System.out.println("Общий расход: " + statistics.getTotalExpenses(wallet));
                                        System.out.println("Текущий баланс: " + statistics.getBalance(wallet));
                                    } else if (statNum == 2) {
                                        Map<Category, BigDecimal> expensesByCategory = statistics.getExpensesByCategory(wallet);
                                        for (Map.Entry<Category, BigDecimal> entry : expensesByCategory.entrySet()) {
                                            System.out.println(entry.getKey().getName() + " - " + entry.getValue());
                                        }
                                    } else {
                                        System.out.println("Некорректный ввод.");
                                    }
                                    break;
                                }
                                case 5: {
                                    System.out.println("Просмотр категорий");
                                    if (wallet.getCategories().isEmpty()) {
                                        System.out.println("Список категорий пуст.");
                                    } else {
                                        for (Category category : wallet.getCategories()) {
                                            System.out.println(category.getName() + " — " + category.getType());
                                        }
                                    }
                                    break;
                                }
                                case 6: {
                                    System.out.println("Добавление категории");
                                    System.out.println("Введите название категории: ");
                                    String name = scanner.nextLine();
                                    System.out.println("Доход (1) или Расход (2) ?");
                                    int transaction = Integer.parseInt(scanner.nextLine());
                                    if (transaction == 1) {
                                        Category category = new Category(name, Category.CategoryType.INCOME);
                                        wallet.addCategory(category);
                                        System.out.println("Категория добавлена: " + name);
                                    } else if (transaction == 2) {
                                        Category category = new Category(name, Category.CategoryType.EXPENSE);
                                        wallet.addCategory(category);
                                        System.out.println("Категория добавлена: " + name);
                                    } else {
                                        System.out.println("Неверный ввод");
                                    }
                                    break;
                                }
                                case 7: {
                                    System.out.println("Установка бюджета");
                                    List<Category> expenseByCategories = new ArrayList<>();
                                    for (Category category : wallet.getCategories()) {
                                        if (category.getType() == EXPENSE) {
                                            expenseByCategories.add(category);
                                        }
                                    }

                                    if (expenseByCategories.isEmpty()) {
                                        System.out.println("Сначала добавьте категорию расходов.");
                                        break;
                                    }
                                    System.out.println("Выберите категорию:");
                                    int i = 1;
                                    for (Category category : expenseByCategories) {
                                        System.out.println(i + " - " + category.getName());
                                        i++;
                                    }

                                    int categoryChoice = Integer.parseInt(scanner.nextLine());
                                    if (categoryChoice < 1 || categoryChoice > expenseByCategories.size()) {
                                        System.out.println("Неверный номер категории.");
                                        break;
                                    }
                                    Category category = expenseByCategories.get(categoryChoice - 1);

                                    System.out.println("Введите лимит бюджета:");
                                    BigDecimal limit = new BigDecimal(scanner.nextLine());

                                    Budget budget = new Budget(limit, category);
                                    wallet.addBudget(budget);
                                    System.out.println("Бюджет на " + category.getName() + " установлен");
                                    break;
                                }
                                case 8: {
                                    System.out.println("Просмотр бюджета");
                                    if (wallet.getBudgets().isEmpty()) {
                                        System.out.println("Бюджеты не установлены.");
                                    } else {
                                        Map<Category, BigDecimal> expenses = statistics.getExpensesByCategory(wallet);
                                        for (Budget budget : wallet.getBudgets()) {
                                            System.out.println(budget.getCategory().getName() + ": " + budget.getLimit());
                                            BigDecimal spentAmount = expenses.getOrDefault(
                                                    budget.getCategory(),
                                                    BigDecimal.ZERO
                                            );
                                            System.out.println("Потрачено: " + spentAmount);
                                            System.out.println("Превышение лимита: " + budget.isExceeded(spentAmount));
                                        }
                                    }
                                    break;
                                }
                                case 0: {
                                    try {
                                        storage.saveUsers(users);
                                        System.out.println("Данные пользователя сохранены.");
                                    } catch (IOException e) {
                                        System.out.println("Ошибка сохранения: " + e.getMessage());
                                    }
                                    System.out.println("Назад к главному меню");
                                    break;
                                }
                                default:
                                    System.out.println("Неверный выбор");
                            }

                        }


                    } else {
                        System.out.println("Такого пользователя не существует");
                    }

                    break;
                }
                case 2: {
                    System.out.println("Регистрация:");
                    System.out.println("Введите логин: ");
                    String login = scanner.nextLine();
                    if (login.isBlank()) {
                        System.out.println("Логин не может быть пустым.");
                        break;
                    }
                    boolean userExists = false;
                    for (User existUser : users) {
                        if (login.equals(existUser.getLogin())) {
                            userExists = true;
                            break;
                        }
                    }
                    if (userExists) {
                        System.out.println("Пользователь с таким именем существует.");
                    } else {
                        System.out.println("Введите пароль: ");
                        String password = scanner.nextLine();
                        if (password.isBlank()) {
                            System.out.println("Пароль не может быть пустым.");
                            break;
                        }
                        User user = new User(login, password);
                        users.add(user);
                        System.out.println("Пользователь успешно добавлен");
                        try {
                            storage.saveUsers(users);
                            System.out.println("Пользователь успешно добавлен и сохранён.");
                        } catch (IOException e) {
                            System.out.println("Ошибка сохранения: " + e.getMessage());
                        }
                        break;
                    }

                }
                case 0: {
                    System.out.println("Завершение...");
                    break;
                }
                default:
                    System.out.println("Неверный выбор.");
            }




        }
    }
}

