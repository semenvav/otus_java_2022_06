import java.util.HashMap;
import java.util.Map;

public class ForTest {
    private final Map<String, Integer> stringIntegerMap = new HashMap<>();

    public void addToMap(String s, Integer i)
    {
        stringIntegerMap.put(s, i);
    }
    public void increment(String s)
    {
        stringIntegerMap.put(s, stringIntegerMap.get(s) + 1);
    }
    public void clearMap()
    {
        stringIntegerMap.clear();
    }
}
