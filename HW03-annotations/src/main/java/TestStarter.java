import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestStarter
{
    private final List<Method> beforeMethods = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();
    private final List<Method> afterMethods = new ArrayList<>();
    private final Class<?> testClass;
    TestStarter(String testClassName) throws ClassNotFoundException, NoSuchMethodException
    {
        testClass = Class.forName(testClassName);
        fillMethodLists();
        int testMethodsCount = testMethods.size(), failedTestMethodsCount = 0;
        Constructor<?> testClassConstructor  = testClass.getDeclaredConstructor();
        for(Method methodForTest : testMethods) {
            try {
                Object testObject = testClassConstructor.newInstance();
                testMethod(methodForTest, testObject);
            }catch (Exception e){
                failedTestMethodsCount++;
            }
        }
        int passedTestMethodsCount = testMethodsCount - failedTestMethodsCount;
        System.out.println("Всего тестов: " + testMethodsCount);
        System.out.println("Пройдено тестов: " + passedTestMethodsCount);
        System.out.println("Провалено тестов: " + failedTestMethodsCount);
    }
    private void fillMethodLists()
    {
        Method[] methods = testClass.getDeclaredMethods();
        for(Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethods.add(method);
            }
        }
    }
    private void testMethod(Method methodForTest, Object testObject ) throws InvocationTargetException, IllegalAccessException
    {
        for (Method beforeMethod : beforeMethods)
        {
            beforeMethod.invoke(testObject);
        }
        methodForTest.invoke(testObject);
        for (Method afterMethod : afterMethods)
        {
            afterMethod.invoke(testObject);
        }
    }
}
