package ru.otus.impls;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.otus.enums.BanknoteType;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Banknote {

    private final BanknoteType banknoteType;
    private final String number;
}
