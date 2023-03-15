package ru.otus.impls;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.otus.enums.BanknoteType;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Cassette {

    private final String name;
    private final BanknoteType banknoteType;
    private final List<Banknote> banknotes;

    public void placeBanknote(Banknote banknote) {
        if (banknoteType == null) {
            throw new IllegalArgumentException("Unable to place banknote. " +
                    "Cassette dignity is not defined.");
        }
        if (banknote == null) {
            throw new IllegalArgumentException("Unable to place banknote. " +
                    "Received banknote is null.");
        }
        if (banknote.getBanknoteType() != banknoteType) {
            throw new IllegalArgumentException("Unable to place banknote. " +
                    "It's dignity does not match cassette dignity.");
        }
        banknotes.add(banknote);
    }

    public void placeBanknotes(List<Banknote> banknotes) {
        if (banknotes == null) {
            throw new IllegalArgumentException("Unable to place banknotes. " +
                    "Received list of banknotes is null.");
        }
        banknotes.forEach(this::placeBanknote);
    }

    public Banknote takeBanknote() {
        if (banknotes.isEmpty()) {
            throw new IllegalStateException("Unable to give banknote. Cassette is empty.");
        }
        return banknotes.remove(banknotes.size() - 1);
    }

    public int getBanknotesCount() {
        return banknotes.size();
    }

    public int getBalance() {
        return banknotes.size() * banknoteType.getDignity();
    }

    public boolean isEmpty() {
        return banknotes.isEmpty();
    }
}
