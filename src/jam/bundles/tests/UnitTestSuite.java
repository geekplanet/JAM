package jam.bundles.tests;

import junit.framework.TestSuite;

public class UnitTestSuite extends TestSuite {
    public UnitTestSuite() {
        addTestSuite(OrmFirstTest.class);
        //addTestSuite(OrmSecondTest.class);
    }
}