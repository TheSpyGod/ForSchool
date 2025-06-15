package master;

public class KlientVIP extends Klient {

    private double dodatkoweOprocentowanie;

    public KlientVIP(int ID, String Imie, String Nazwisko, double Saldo, double Oprocentowanie, double dodatkoweOprocentowanie) {
        super(ID, Imie, Nazwisko, Saldo, Oprocentowanie);
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }

    public double getDodatkoweOprocentowanie() {
        return dodatkoweOprocentowanie;
    }

    public void setDodatkoweOprocentowanie(double dodatkoweOprocentowanie) {
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }

    public void naliczOprocentowanie() {
        double oprocentowanieTotal = getOprocentowanie() + dodatkoweOprocentowanie;
        Saldo += Saldo * (oprocentowanieTotal / 100.0);
    }
}
