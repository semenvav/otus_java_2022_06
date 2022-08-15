import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TestInvocationHandler implements InvocationHandler {

    private final TestLoggingInterface originalObject;

    public TestInvocationHandler(TestLoggingInterface originalObject) {
        this.originalObject = originalObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Log.class)) {
            String methodName = method.getName();
            Parameter[] parameters = method.getParameters();
            StringBuilder log = new StringBuilder();
            log.append("Executed method: ").append(methodName).append(" ,");
            for (int i = 0; i < method.getParameterCount(); i++) {
                log.append(parameters[i].getName()).append(": ").append(args[i]).append(" ");
            }
            System.out.println(log);
        }
        return method.invoke(originalObject, args);
    }
}
