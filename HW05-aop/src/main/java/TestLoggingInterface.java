public interface TestLoggingInterface {

    @Log
    void calculation1(int param);

    @Log
    void calculation2(int param1, int param2);

    @Log
    void calculation3(int param1, int param2, String param3);
}
