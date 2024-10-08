package com.example.testngparallel.tests;

import com.example.testngparallel.pages.base.WebTestBase;
import com.example.testngparallel.testbase.TestBase;
import helpers.PdfUtilities;
import org.testng.annotations.Test;

public class C4_VerifyCVContainsValuesTest extends WebTestBase {


    @Test(groups = {"mainPage", "all"})
    public void yourTestMethod() {
        page.loadPage.goToMainPage();

        logStep("Step 1", "Download pdf");
        String fileName = page.mainPage.downloadCVwithHTTP();

        logStep("Step 2", "Verify pdf contains the value Georgios");

        PdfUtilities.verifyPdfContainsValue(fileName, "Georgios");
    }
}