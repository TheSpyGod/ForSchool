package master;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Klient implements Serializable {

    public int ID;
    public String Imie;
    public String Nazwisko;
    public double Saldo;
    public double Oprocentowanie;

    private List<Klient> list;

    public Klient() {
        list = new ArrayList<>();
    }

    protected Klient(int ID, String Imie, String Nazwisko, double Saldo, double Oprocentowanie) {
        this.ID = ID;
        this.Imie = Imie;
        this.Nazwisko = Nazwisko;
        this.Saldo = Saldo;
        this.Oprocentowanie = Oprocentowanie;
    }

    public void AddKlient(int ID, String Imie, String Nazwisko, double Saldo, double Oprocentowanie) {
        Klient k = new Klient(ID, Imie, Nazwisko, Saldo, Oprocentowanie);
        list.add(k);
    }

    public Klient getKlient(int pos) {
        return list.get(pos);
    }

    public List<Klient> getKlientList() {
        return list;
    }

    public void writeBinaryFile(String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(list);
        out.close();
    }

    public void readBinaryFile(String filename) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<Klient>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return ID;
    }

    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public double getSaldo() {
        return Saldo;
    }

    public double getOprocentowanie() {
        return Oprocentowanie;
    }
}
