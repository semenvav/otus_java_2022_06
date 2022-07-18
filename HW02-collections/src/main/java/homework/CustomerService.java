package homework;


import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    NavigableMap<Customer, String> customerStringMap = new TreeMap<>();

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> entry = customerStringMap.firstEntry();
        return Map.entry(entry.getKey().clone(), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = customerStringMap.higherEntry(customer);
        if(entry == null){
            return null;
        }
        return Map.entry(entry.getKey().clone(), entry.getValue());
    }

    public void add(Customer customer, String data) {
        customerStringMap.put(customer, data);
    }
}
