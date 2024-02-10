package com.example.testngparallel.pages.pagefactory;

import com.example.testngparallel.pages.GenericPage;
import com.example.testngparallel.pages.LoadPage;
import com.example.testngparallel.pages.MainPage;

public class PageFactory {

    public final MainPage mainPage;
    public final LoadPage loadPage;
    public final GenericPage genericPage;


    public PageFactory() {
        mainPage = new MainPage();
        loadPage = new LoadPage();
        genericPage = new GenericPage();
    }
}
