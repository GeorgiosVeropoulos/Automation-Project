package com.example.testngparallel.pages.base;

import com.example.testngparallel.pages.pagefactory.PageFactory;
import com.example.testngparallel.testbase.TestBase;
import org.testng.annotations.BeforeMethod;

public class WebTestBase extends TestBase {

    public PageFactory page;

    @BeforeMethod(alwaysRun = true)
    public void beforeEachTestFactory() {
        page = new PageFactory();
    }
}
