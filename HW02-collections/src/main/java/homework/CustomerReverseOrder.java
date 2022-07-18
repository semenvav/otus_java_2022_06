package homework;


import java.util.List;
import java.util.Stack;

public class CustomerReverseOrder {
    private final Stack<Customer> customerStack = new Stack<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    public void add(Customer customer) {
        customerStack.push(customer);
    }

    public Customer take() {
        return customerStack.pop();
    }
}
