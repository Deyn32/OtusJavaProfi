package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomerService {

    SortedMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        return map.entrySet().iterator().next();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var result =  map.entrySet().stream()
            .filter(val -> val.getKey().getScores() > customer.getScores())
            .findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        else {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
