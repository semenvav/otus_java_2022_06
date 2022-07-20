package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {
    final Deque<Customer> customerStack = new ArrayDeque<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    public void add(Customer customer) {
        customerStack.push(customer);
    }

    public Customer take() {
        return customerStack.pop();
    }
}
