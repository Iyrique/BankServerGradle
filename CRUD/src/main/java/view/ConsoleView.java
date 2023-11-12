package view;

import connection.ConnectDB;
import crud.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class ConsoleView implements View{

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
                    crud.readClientByName(name);
                } else if (choose == 2) {
                    System.out.println("Введите id клиента: ");
                    crud.readClientById(scanner.nextInt());
                }
                break;
            case 2:
                System.out.println("1. Получить все кредиты человека, 2. Получить конкретный кредит: ");
                choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.println("Введите id клиента: ");
                    crud.readCreditsById(scanner.nextInt());
                } else if (choose == 2) {
                    System.out.println("Введите id кредита: ");
                    crud.readCreditById(scanner.nextInt());
                }
                break;
            case 3:
                System.out.println("1. Получить все депозиты человека, 2. Получить конкретный депозит: ");
                choose = scanner.nextInt();
                if (choose == 1) {
                    System.out.println("Введите id клиента: ");
                    crud.readDepositsById(scanner.nextInt());
                } else if (choose == 2) {
                    System.out.println("Введите id депозита: ");
                    crud.readDepositById(scanner.nextInt());
                }
                break;
            case 4:
                System.out.println("Введите id клиента для получения договора счета:");
                choose = scanner.nextInt();
                crud.readCardAccount(choose);
                break;
            case 5:
                System.out.println("Введите id клиента для получения счета:");
                choose = scanner.nextInt();
                crud.readAccount(choose);
                break;
            case 6:
                System.out.println("Введите id клиента для получения карты:");
                choose = scanner.nextInt();
                crud.readCard(choose);
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
                crud.updateClient(scanner.nextInt());
            }
            case 2 -> {
                System.out.println("Введите id кредита: ");
                crud.updateCredit(scanner.nextInt());
            }
            case 3 -> {
                System.out.println("Введите id депозита: ");
                crud.updateDeposit(scanner.nextInt());
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
            case 1 -> crud.getAllAccounts();
            case 2 -> crud.getAllBanks();
            case 3 -> crud.getAllCardAccounts();
            case 4 -> crud.getAllCards();
            case 5 -> crud.getAllClients();
            case 6 -> crud.getAllCredits();
            case 7 -> crud.getAllDeposits();
            default -> System.out.println("Введено неверное число, попробуйте еще раз!");
        }
    }
}
