import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;


public class TestInvocationHandler implements InvocationHandler {

    private final TestLoggingInterface originalObject;

    private final Map<Method, Parameter[]> logMethodsMap;

    public TestInvocationHandler(TestLoggingInterface originalObject) {
        this.originalObject = originalObject;
        logMethodsMap = getLogMethodsMap();
    }

    public Map<Method, Parameter[]> getLogMethodsMap() {
        Class<?> clazz = TestLoggingInterface.class;
        Map<Method, Parameter[]> annotatedMethods = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Log.class)) {
                annotatedMethods.put(method, method.getParameters());
            }
        }
        return annotatedMethods;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(logMethodsMap.containsKey(method)) {
            String methodName = method.getName();
            Parameter[] parameters = logMethodsMap.get(method);
            StringBuilder log = new StringBuilder();
            log.append("Executed method: ").append(methodName).append(" ,");
            for (int i = 0; i < parameters.length; i++) {

                log.append(parameters[i].getName()).append(": ").append(args[i]).append(" ");
            }
            System.out.println(log);
        }
        return method.invoke(originalObject, args);
    }
}
