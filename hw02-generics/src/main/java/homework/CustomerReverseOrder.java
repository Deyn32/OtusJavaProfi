package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    private Deque<Customer> customers = new ArrayDeque<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        Customer lastElem = customers.getLast();
        customers.remove(lastElem);
        return lastElem;
    }
}
