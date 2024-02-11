package com.example.testngparallel.pages;

import com.example.testngparallel.pages.page.Page;

import static constants.TestConstants.WEB_PAGE_URL;

public class LoadPage extends Page {


    public void goToMainPage() {
        openURL(WEB_PAGE_URL);
    }
}
