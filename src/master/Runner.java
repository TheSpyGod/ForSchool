package master;

import java.util.List;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Main app = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj klienta");
            System.out.println("2. Wpłać na konto");
            System.out.println("3. Wypłać z konta");
            System.out.println("4. Przelew między kontami");
            System.out.println("5. Nalicz oprocentowanie");
            System.out.println("6. Wyświetl wszystkich klientów");
            System.out.println("7. Szukaj klienta po ID");
            System.out.println("8. Zapisz dane");
            System.out.println("9. Wczytaj dane");
            System.out.println("0. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int opcja = scanner.nextInt();
            scanner.nextLine();

            switch (opcja) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Imię: ");
                    String imie = scanner.nextLine();
                    System.out.print("Nazwisko: ");
                    String nazwisko = scanner.nextLine();
                    System.out.print("Saldo: ");
                    double saldo = scanner.nextDouble();
                    System.out.print("Oprocentowanie: ");
                    double oprocentowanie = scanner.nextDouble();
                    app.DodajKlienta(id, imie, nazwisko, saldo, oprocentowanie);
                    app.OdswiezList();
                    break;
                }
                case 2 -> {
                    System.out.print("ID klienta: ");
                    int id = scanner.nextInt();
                    System.out.print("Kwota do wpłaty: ");
                    double kwota = scanner.nextDouble();
                    Klient k = app.SzukajKlient(id);
                    if (k != null) {
                        app.WplacKonto(k, kwota);
                    } else {
                        System.out.println("Nie znaleziono klienta.");
                    }
                    break;
                }
                case 3 -> {
                    System.out.print("ID klienta: ");
                    int id = scanner.nextInt();
                    System.out.print("Kwota do wypłaty: ");
                    double kwota = scanner.nextDouble();
                    Klient k = app.SzukajKlient(id);
                    if (k != null && k.Saldo >= kwota) {
                        app.WyplacKonto(k, kwota);
                    } else {
                        System.out.println("Za mało środków lub nie znaleziono klienta.");
                    }
                    break;
                }
                case 4 -> {
                    System.out.print("ID nadawcy: ");
                    int fromId = scanner.nextInt();
                    System.out.print("ID odbiorcy: ");
                    int toId = scanner.nextInt();
                    System.out.print("Kwota przelewu: ");
                    double kwota = scanner.nextDouble();
                    Klient from = app.SzukajKlient(fromId);
                    Klient to = app.SzukajKlient(toId);
                    if (from != null && to != null && from.Saldo >= kwota) {
                        app.Przelew(from, to, kwota);
                    } else {
                        System.out.println("Błąd przelewu.");
                    }
                    break;
                }
                case 5 -> {
                    System.out.print("ID klienta: ");
                    int id = scanner.nextInt();
                    Klient k = app.SzukajKlient(id);
                    if (k != null) {
                        k.Saldo += k.Saldo * (k.Oprocentowanie / 100.0);
                        System.out.println("Nowe saldo: " + k.Saldo);
                    } else {
                        System.out.println("Nie znaleziono klienta.");
                    }
                    break;
                }
                case 6 -> {
                    System.out.println("--- Lista klientów ---");
                    List<Klient> klienci = app.getList();
                    for (Klient k : klienci) {
                        System.out.printf("ID: %d, %s %s, Saldo: %.2f, Oprocentowanie: %.2f%%\n",
                                k.ID, k.Imie, k.Nazwisko, k.Saldo, k.Oprocentowanie);
                    }
                    break;
                }
                case 7 -> {
                    System.out.print("ID klienta: ");
                    int id = scanner.nextInt();
                    Klient k = app.SzukajKlient(id);
                    if (k != null) {
                        System.out.printf("Znaleziono: %s %s, Saldo: %.2f\n",
                                k.Imie, k.Nazwisko, k.Saldo);
                    } else {
                        System.out.println("Nie znaleziono klienta.");
                    }
                    break;
                }
                case 8 -> {
                    app.Zapisz();
                    System.out.println("Dane zapisane.");
                    break;
                }
                case 9 -> {
                    app.WyczytajZPliku();
                    app.OdswiezList();
                    System.out.println("Dane wczytane.");
                    break;
                }
                case 0 -> {
                    running = false;
                    System.out.println("Zamykanie programu...");
                    break;
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }

        scanner.close();
    }
}
