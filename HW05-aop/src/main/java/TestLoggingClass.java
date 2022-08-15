

public class TestLoggingClass implements TestLoggingInterface{

    @Log
    public void calculation1(int param) {
        System.out.println(param / 100);
    }

    @Log
    public void calculation2(int param1, int param2){
        System.out.println(param1 + param2);
    }

    @Log
    public void calculation3(int param1, int param2, String param3){
       System.out.println(param3 + " " + param1 + " " + param2);
    }
}

