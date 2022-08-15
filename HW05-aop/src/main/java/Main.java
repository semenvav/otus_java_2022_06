import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args)
    {
        TestLoggingInterface objectWithProxy = getNewClassInstance();
        objectWithProxy.calculation1(5);
        objectWithProxy.calculation2(4, 6);
        objectWithProxy.calculation3(56, 74, "test");
    }

    public static TestLoggingInterface getNewClassInstance(){
        TestLoggingClass originalObject = new TestLoggingClass();
        TestInvocationHandler handler = new TestInvocationHandler(originalObject);
        return (TestLoggingInterface) Proxy.newProxyInstance(TestLoggingInterface.class.getClassLoader(),
                new Class[]{TestLoggingInterface.class},
                handler);
    }
}
