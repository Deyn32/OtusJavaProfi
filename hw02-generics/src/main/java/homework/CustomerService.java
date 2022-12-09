package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomerService {

    private SortedMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return map.entrySet().iterator().next();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var result =  map.entrySet().stream()
            .filter(val -> val.getKey().getScores() > customer.getScores())
            .findFirst();
        return result.orElse(null);
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
