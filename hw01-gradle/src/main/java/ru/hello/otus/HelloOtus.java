package ru.hello.otus;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HelloOtus {
    public static void main(String[] args) {
        Set<String> prime = new HashSet<>();
        prime.add("One");
        prime.add("Two");
        prime.add("Three");
        List<String> second = Lists.newArrayList(prime);
        System.out.println(Lists.reverse(second));
    }
}
