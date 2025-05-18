import junit.framework.TestCase;

import java.lang.annotation.Target;

public class TryMultiThreadedTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }


    public void tearDown() throws Exception {
        super.tearDown();
    }

    class A{
      public A(){

      }
    }
    class B extends A{
        public B(){

        }
    }


    public void test() {
        A a = new A();
        B b = new B();

        assertTrue(A.class.isAssignableFrom(B.class));
    }
}