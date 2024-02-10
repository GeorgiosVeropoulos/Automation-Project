package com.example.testngparallel.pages;

import com.codeborne.selenide.ElementsCollection;
import com.example.testngparallel.pages.page.Page;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;


public class GenericPage extends Page {

    private final ElementsCollection navigationBtns = $$(By.xpath("//*[@id = 'nav']//a"));








    public GenericPage checkNavigationBtnsNames(List<String> btnNames) {
        List<String> navBtnsTexts = navigationBtns.texts();
        for (String btnName : btnNames) {
            Assert.assertTrue(navBtnsTexts.contains(btnName));
        }
        return this;
    }
}
