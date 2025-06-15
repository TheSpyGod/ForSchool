package master;

import java.io.IOException;
import java.util.List;

public class Main {

    private List<Klient> list;
    private Klient k;

    public Main() {
        k = new Klient();
        list = k.getKlientList();
        DodajKlienta(0, "Pawel", "Gawel", 20, 50);
        System.out.print(list.get(0).Oprocentowanie);
        Zapisz();
    }

    public void OdswiezList() {
        list = k.getKlientList();
    }

    public void WplacKonto(Klient k, double Saldo) {
        k.Saldo += Saldo;
    }

    public void DodajKlienta(int ID, String Imie, String Nazwisko, double Saldo, double Oprocentowanie) {
        k.AddKlient(ID, Imie, Nazwisko, Saldo, Oprocentowanie);
    }

    public void WyplacKonto(Klient k, double Saldo) {
        k.Saldo -= Saldo;
    }

    public void Przelew(Klient sender, Klient reciever, double Saldo) {
        sender.Saldo -= Saldo;
        reciever.Saldo += Saldo;
    }
    
    public void Zapisz() {
        try {
            k.writeBinaryFile("s.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void WyczytajZPliku() {
        try {
            k.readBinaryFile("s.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Klient> getList() {
        return list;
    }

    public Klient SzukajKlient(int ID) {
        for (Klient klient : list) {
            if (klient.getID() == ID) {
                return klient;
            }
        }
        return null;
    }
}
