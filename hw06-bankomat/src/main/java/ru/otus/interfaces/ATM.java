package ru.otus.interfaces;

import ru.otus.impls.Banknote;
import ru.otus.impls.Cassette;

import java.util.List;

public interface ATM {

    void placeBanknote(Banknote banknote);
    int getBalance();
    List<Banknote> withdrawCash(int sumForPay);
    void addCassette(Cassette cassette);
}
