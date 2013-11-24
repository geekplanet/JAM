package jam.bundles.tests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

class TestMainRun {
    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        TestSuite suite = new TestSuite();
        suite.addTest(new OrmFirstTest("testSelect"));
        //suite.addTest(new OrmSecondTest());

        runner.doRun(suite);
    }
}
