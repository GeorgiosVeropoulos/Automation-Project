package com.example.testngparallel.pages;

import com.codeborne.selenide.WebDriverRunner;
import com.example.testngparallel.pages.page.Page;

public class LoadPage extends Page {


    public void goToMainPage() {
        openURL("https://www.georgeveropoulos.com/");
    }
}
