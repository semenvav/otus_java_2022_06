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

    TestStarter(String testClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException
    {
        testClass = Class.forName(testClassName);
        fillMethodLists();
        startTesting();
    }

    private void startTesting() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException
    {
        int testMethodsCount = testMethods.size(), failedTestMethodsCount = 0;
        Constructor<?> testClassConstructor  = testClass.getDeclaredConstructor();
        for(Method methodForTest : testMethods) {
            Object testObject = testClassConstructor.newInstance();
            try {
                testMethod(methodForTest, testObject);
            } catch (Exception e) {
                failedTestMethodsCount++;
            } finally {
                invokeAfterMethods(testObject);
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
    }

    private void invokeAfterMethods(Object testObject){
        for (Method afterMethod : afterMethods)
        {
            try{
                afterMethod.invoke(testObject);
            }catch (Exception ignored){
            }
        }
    }
}
