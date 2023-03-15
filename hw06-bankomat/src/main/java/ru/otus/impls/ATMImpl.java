package ru.otus.impls;

import lombok.extern.slf4j.Slf4j;
import ru.otus.enums.BanknoteType;
import ru.otus.interfaces.ATM;

import java.util.*;

@Slf4j
public class ATMImpl implements ATM {

    private final List<Cassette> strongBox;

    public ATMImpl(List<Cassette> strongBox) {
        if(strongBox == null) {
            log.error("strongBox не может быть null!!!");
            throw new NullPointerException();
        }
        this.strongBox = strongBox;
    }

    @Override
    public void placeBanknote(Banknote banknote) {
        if (banknote == null) {
            throw new IllegalArgumentException("Unable to place banknote. " +
                    "Received banknote dignity is null.");
        }
        boolean placed = false;
        for (Cassette cas : strongBox) {
            if (cas.getBanknoteType() == banknote.getBanknoteType()) {
                cas.placeBanknote(banknote);
                placed = true;
                break;
            }
        }
        if (!placed) {
            throw new IllegalStateException("Unable to place banknote. " +
                    "Banknotes of this dignity are not accepted.");
        }
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Cassette cas : strongBox) {
            balance = balance + cas.getBalance();
        }
        return balance;
    }

    @Override
    public List<Banknote> withdrawCash(int sumForPay) {
        int sum = sumForPay;

        List<BanknoteType> dens = getDignities();

        Map<BanknoteType, Integer> plan = new TreeMap<>();

        for (BanknoteType den : dens) {

            int count = sum / den.getDignity();

            if (count > 0) {
                int availableBanknotes = getCountBanknotes(den);

                if (count > availableBanknotes) {
                    count = availableBanknotes;
                }
                plan.put(den, count);
                sum = sum - count * den.getDignity();
            }

            if (sum == 0) {
                break;
            }
        }

        if (sum != 0) {
            throw new IllegalStateException("Unable to dispense cash. Not enough banknotes.");
        }

        List<Banknote> withdrawal = new ArrayList<>();
        for (Map.Entry<BanknoteType, Integer> pair : plan.entrySet()) {
            for (int i = 0; i < pair.getValue(); i++) {
                withdrawal.add(takeBanknote(pair.getKey()));
            }
        }

        return withdrawal;
    }

    public List<BanknoteType> defineAllocationSet(int sumForDeposit) {

        int sum = sumForDeposit;
        List<BanknoteType> digs = getDignities();
        List<BanknoteType> requiredDignities = new ArrayList<>();

        for (BanknoteType dignity : digs) {

            int count = sum / dignity.getDignity();

            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    requiredDignities.add(dignity);
                }
                sum = sum - count * dignity.getDignity();
            }

            if (sum == 0) {
                break;
            }
        }

        if (sum != 0) {
            requiredDignities = new ArrayList<>();
        }

        return requiredDignities;
    }

    public void depositingCash(List<Banknote> pack) {

        List<BanknoteType> dens = getDignities();
        for (Banknote banknote : pack) {
            if (!dens.contains(banknote.getBanknoteType())) {
                throw new IllegalArgumentException();
            }
        }
        pack.forEach(this::placeBanknote);
    }

    @Override
    public void addCassette(Cassette cassette) {

        if (cassette == null) {
            throw new IllegalArgumentException("Unable to add cassette." +
                    "Argument must be defined.");
        }
        if (containsDignityCassette(cassette)) {
            throw new IllegalArgumentException("Unable to add cassette." +
                    "A cassette of this dignity is already in the ATM.");
        }
        strongBox.add(cassette);
    }

    public List<BanknoteType> getDignities() {

        List<BanknoteType> digs = new ArrayList<>();
        for (Cassette cas : strongBox) {
            digs.add(cas.getBanknoteType());
        }
        digs.sort((o1, o2) -> o2.getDignity() - o1.getDignity());

        return digs;
    }

    public int getCountBanknotes(BanknoteType banknoteType) {
        if (banknoteType == null) {
            log.error("bancnoteType не может быть null!");
            throw new NullPointerException();
        } else {

            int count = 0;
            for (Cassette cas : strongBox) {
                if (cas.getBanknoteType() == banknoteType) {
                    count = cas.getBanknotesCount();
                    break;
                }
            }

            return count;
        }
    }

    public Banknote takeBanknote(BanknoteType banknoteType) {

        if (banknoteType != null) {
            Banknote banknote = null;
            for (Cassette cas : strongBox) {
                if (cas.getBanknoteType() == banknoteType && !cas.isEmpty()) {
                    banknote = cas.takeBanknote();
                    break;
                }
            }

            return banknote;
        } else {
            log.error("banknoteType не может быть null!!!");
            throw new NullPointerException();
        }
    }

    private boolean containsDignityCassette(Cassette cassette) {

        boolean contains = false;
        for (Cassette internalCas : strongBox) {
            if (internalCas.getBanknoteType() == cassette.getBanknoteType()) {
                contains = true;
                break;
            }
        }

        return contains;
    }
}
