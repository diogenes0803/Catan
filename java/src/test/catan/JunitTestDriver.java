package test.catan;


//all tests use "John"as username and "Doe" as password
public class JunitTestDriver {
    public static void main(String[] args) {

        String[] testClasses = new String[]{
                "test.ModelInitTest",
                "test.ServerCommunicatorTest",
                "test.ServerProxyTest",
                "test.GameTest",
                "test.ServerPollerTest"
        };

        org.junit.runner.JUnitCore.main(testClasses);
    }
}
