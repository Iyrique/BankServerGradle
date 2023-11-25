package view;

import connection.ConnectDB;
import crud.CRUD;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class ConsoleView implements View {

    private final Scanner scanner = new Scanner(System.in);

    private CRUD crud;

    @Override
    public void displayMenu() throws SQLException {
        Connection connection = ConnectDB.connector();
        crud = new CRUD(connection);
        outer:
        while (true) {
            System.out.println("Выберите действие (1 - Добавить, 2 - прочитать что-то конкретное, 3 - обновить, 4 - удалить, 5 - прочитать все, 6 - отключиться):");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    readAll();
                    break;
                case 6:
                    break outer;
                default:
                    System.out.println("Введено неверное число, попробуйте еще раз!");
            }
        }
        connection.close();
    }

    @Override
    public void create() throws SQLException {
        System.out.println("Кого вы хотите добавить? (1. Клиента, 2. Кредит, 3. Депозит, 4. Договор счета, 5. Счет, 6. Карту (Для выхода нажмите на любое другое число)");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("Введите фамилию имя клиента:");
                String name = scanner.next();
                String surname = scanner.next();
                name = name + " " + surname;
                System.out.println("Введенное имя: " + name);
                System.out.println("Введите дату рождения клиента (ДД.ММ.ГГГГ):");
                String birthday = scanner.next();
                crud.createClient(name, birthday);
                break;
            case 2:
                System.out.println("Введите id клиента:");
                int id = scanner.nextInt();
                System.out.println("Введите сумму кредита клиента:");
                double sum = scanner.nextDouble();
                System.out.println("Введите проценты кредита клиента:");
                double percent = scanner.nextDouble();
                System.out.println("Введите ежемесячный платеж: ");
                double payment = scanner.nextDouble();
                System.out.println("Введите срок кредита (ДД.ММ.ГГГГ): ");
                String period = scanner.next();
                crud.createCredit(id, sum, percent, payment, period);
                break;
            case 3:
                System.out.println("Введите id клиента:");
                id = scanner.nextInt();
                System.out.println("Введите сумму депозита клиента:");
                sum = scanner.nextDouble();
                System.out.println("Введите проценты депозита клиента:");
                percent = scanner.nextDouble();
                System.out.println("Введите срок кредита (ДД.ММ.ГГГГ): ");
                period = scanner.next();
                System.out.println("Возможность пополнения (Yes/No): ");
                String topUp = scanner.next();
                boolean top = topUp.contains("Yes");
                System.out.println("Возможность снятия (Yes/No): ");
                String withdraw = scanner.next();
                boolean w = withdraw.contains("Yes");
                crud.createDeposit(id, sum, percent, period, top, w);
                break;
            case 4:
                System.out.println("Введите id клиента:");
                id = scanner.nextInt();
                crud.createCardAccount(id);
                break;
            case 5:
                System.out.println("Введите id клиента:");
                id = scanner.nextInt();
                crud.createAccount(id);
                break;
            case 6:
                System.out.println("Введите id клиента:");
                id = scanner.nextInt();
                crud.createCard(id);
                break;
            default:
                System.out.println("Введено неверное число, попробуйте еще раз!");
        }
    }

    @Override
    public void read() throws SQLException {
        System.out.println("Кого вы хотите найти? (1. Клиента, 2. Кредит, 3. Депозит, 4. Договор счета, 5. Счет, 6. Карту (Для выхода нажмите на любое другое число)");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("Что вы будете вводить, имя и фамилию или id пользователя? (1. имя, 2. id)");
                int choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.println("Введите имя и фамилию: ");
                    String name = scanner.next();
                    name = name + " " + scanner.next();
                    Client client = crud.readClientByName(name);
                    if (client == null) {
                        System.out.println("Клиент не найден");
                    } else System.out.println(client.toString());
                } else if (choose == 2) {
                    System.out.println("Введите id клиента: ");
                    Client client = crud.readClientById(scanner.nextInt());
                    if (client == null) {
                        System.out.println("Клиент не найден");
                    } else System.out.println(client.toString());
                }
                break;
            case 2:
                System.out.println("1. Получить все кредиты человека, 2. Получить конкретный кредит: ");
                choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.println("Введите id клиента: ");
                    List<Credit> credits = crud.readCreditsById(scanner.nextInt());
                    if (credits.isEmpty()) {
                        System.out.println("Кредитов нет!");
                    } else {
                        for (Credit credit : credits) {
                            System.out.println(credit.toString());
                        }
                    }
                } else if (choose == 2) {
                    System.out.println("Введите id кредита: ");
                    Credit credit = crud.readCreditById(scanner.nextInt());
                    if (credit != null) {
                        System.out.println(credit.toString());
                    } else System.out.println("Кредит не найден");
                }
                break;
            case 3:
                System.out.println("1. Получить все депозиты человека, 2. Получить конкретный депозит: ");
                choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.println("Введите id клиента: ");
                    List<Deposit> deposits = crud.readDepositsById(scanner.nextInt());
                    if (deposits.isEmpty()) {
                        System.out.println("Депозитов нет!");
                    } else {
                        for (Deposit deposit : deposits) {
                            System.out.println(deposit.toString());
                        }
                    }
                } else if (choose == 2) {
                    System.out.println("Введите id депозита: ");
                    Deposit deposit = crud.readDepositById(scanner.nextInt());
                    if (deposit != null) {
                        System.out.println(deposit.toString());
                    } else System.out.println("Не существует!");
                }
                break;
            case 4:
                System.out.println("Введите id клиента для получения договора счета:");
                choose = scanner.nextInt();
                System.out.println(crud.readCardAccount(choose).toString());
                break;
            case 5:
                System.out.println("Введите id клиента для получения счета:");
                choose = scanner.nextInt();
                System.out.println(crud.readAccount(choose).toString());
                break;
            case 6:
                System.out.println("Введите id клиента для получения карты:");
                choose = scanner.nextInt();
                System.out.println(crud.readCard(choose).toString());
                break;
            default:
                System.out.println("Введено неверное число, попробуйте еще раз!");
        }

    }

    @Override
    public void update() throws SQLException {
        System.out.println("Что вы хотите обновить? (1. Клиента, 2. Кредит, 3. Депозит(Для выхода введите любое другое число))");
        int num = scanner.nextInt();
        switch (num) {
            case 1 -> {
                System.out.println("Введите id пользователя: ");
                int id = scanner.nextInt();
                System.out.println("Введите name пользователя: ");
                String n = scanner.next();
                n += " ";
                n += scanner.next();
                crud.updateClient(id, n);
            }
            case 2 -> {
                System.out.println("Введите id кредита: ");
                int id = scanner.nextInt();
                System.out.println("Введите сумму кредита: ");
                double sum = scanner.nextDouble();
                System.out.println("Введите проценты: ");
                double percent = scanner.nextDouble();
                System.out.println("Введите размер ежемесячного платежа: ");
                double payment = scanner.nextDouble();
                crud.updateCredit(id, sum, percent, payment);
            }
            case 3 -> {
                System.out.println("Введите id депозита: ");
                int id = scanner.nextInt();
                System.out.println("Введите сумму депозита: ");
                double sum = scanner.nextDouble();
                System.out.println("Введите проценты: ");
                double percent = scanner.nextDouble();
                crud.updateDeposit(id, sum, percent);
            }
            default -> System.out.println("Введено неверное число, попробуйте еще раз!");
        }
    }

    @Override
    public void delete() throws SQLException {
        System.out.println("Что вы хотите удалить? (1. Клиента, 2. Кредит, 3. Депозит, 4. Договор счета, 5. Счет, 6.Карту(Для выхода введите любое другое число))");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("Введите id пользователя: ");
                crud.deleteClient(scanner.nextInt());
                break;
            case 2:
                System.out.println("Введите id кредита: ");
                crud.deleteCredit(scanner.nextInt());
                break;
            case 3:
                System.out.println("Введите id депозита: ");
                crud.deleteDeposit(scanner.nextInt());
                break;
            case 4:
                System.out.println("Введите id клиента для удаления договора счета: ");
                crud.deleteCardAccount(scanner.nextInt());
                break;
            case 5:
                System.out.println("Введите id клиента для удаления счета: ");
                crud.deleteAccount(scanner.nextInt());
                break;
            case 6:
                System.out.println("Введите id клиента для удаления карты");
                crud.deleteCard(scanner.nextInt());
                break;
            default:
                System.out.println("Введено неверное число, попробуйте еще раз!");
        }
    }

    @Override
    public void readAll() throws SQLException {
        System.out.println("Что вы хотите прочитать? (1.Account, 2. Bank, 3. CardAccount, 4. Card, 5. Client, 6. Credit, 7. Deposit)");
        int num = scanner.nextInt();
        switch (num) {
            case 1 -> {
                for (Account acc : crud.getAllAccounts()) {
                    System.out.println(acc.toString());
                }
            }
            case 2 -> {
                for (Bank bank : crud.getAllBanks()) {
                    System.out.println(bank.toString());
                }
            }
            case 3 -> {
                for (CardAccount cardAccount : crud.getAllCardAccounts()) {
                    System.out.println(cardAccount.toString());
                }
            }
            case 4 -> {
                for (Card card : crud.getAllCards()) {
                    System.out.println(card.toString());
                }
            }
            case 5 -> {
                for (Client client : crud.getAllClients()) {
                    System.out.println(client.toString());
                }
            }
            case 6 -> {
                for (Credit credit : crud.getAllCredits()) {
                    System.out.println(credit.toString());
                }
            }
            case 7 -> {
                for (Deposit deposit : crud.getAllDeposits()) {
                    System.out.println(deposit.toString());
                }
            }
            default -> System.out.println("Введено неверное число, попробуйте еще раз!");
        }
    }
}
