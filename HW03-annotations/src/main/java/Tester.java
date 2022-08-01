
public class Tester
{
    private final ForTest forTest;
    Tester()
    {
        forTest = new ForTest();
    }

    @Before
    public void startInfo()
    {
        forTest.addToMap("Anton", 5);
        forTest.addToMap("Max", 8);
        forTest.addToMap("Alex", 3);
    }

    @Test
    public void correctIncrement()
    {
        forTest.increment("Anton");
    }

    @Test
    public void inCorrectIncrement()
    {
        forTest.increment("Semen");
    }

    @Test
    public void correctIncrement2()
    {
        forTest.increment("Alex");
    }

    @After
    public void clear()
    {
        forTest.clearMap();
    }
}
