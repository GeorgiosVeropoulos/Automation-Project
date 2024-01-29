package browser;

import com.codeborne.selenide.*;
import com.codeborne.selenide.files.FileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

public class Element implements SelenideElement {
    private final SelenideElement delegate;
    public Element(By locator) {
        this.delegate = Selenide.$(locator);;
    }



    @Nonnull
    @Override
    public SelenideElement setValue(@Nullable String text) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement val(@Nullable String text) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement val(SetValueOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement setValue(SetValueOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement type(CharSequence text) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement type(TypeOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement append(String text) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement paste() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement pressEnter() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement pressTab() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement pressEscape() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement press(CharSequence... keysToPress) {
        return null;
    }

    @Nonnull
    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Nullable
    @Override
    public String getAlias() {
        return null;
    }

    @Nonnull
    @Override
    public String text() {
        return null;
    }

    @Nonnull
    @Override
    public String getOwnText() {
        return null;
    }

    @Nonnull
    @Override
    public String innerText() {
        return null;
    }

    @Nonnull
    @Override
    public String innerHtml() {
        return null;
    }

    @Nullable
    @Override
    public String attr(String attributeName) {
        return null;
    }

    @Nullable
    @Override
    public String name() {
        return null;
    }

    @Nullable
    @Override
    public String val() {
        return null;
    }

    @Nullable
    @Override
    public String getValue() {
        return null;
    }

    @Nonnull
    @Override
    public String pseudo(String pseudoElementName, String propertyName) {
        return null;
    }

    @Nonnull
    @Override
    public String pseudo(String pseudoElementName) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement selectRadio(String value) {
        return null;
    }

    @Nullable
    @Override
    public String data(String dataAttributeName) {
        return null;
    }

    @Nullable
    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Nonnull
    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public boolean is(Condition condition) {
        return false;
    }

    @Override
    public boolean has(Condition condition) {
        return false;
    }

    @Nonnull
    @Override
    public SelenideElement setSelected(boolean selected) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement should(Condition... condition) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement should(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldHave(Condition... condition) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldHave(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldBe(Condition... condition) {
        this.delegate.shouldBe(condition);
        return this;
    }

    @Nonnull
    @Override
    public SelenideElement shouldBe(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNot(Condition... condition) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNot(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNotHave(Condition... condition) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNotHave(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNotBe(Condition... condition) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement shouldNotBe(Condition condition, Duration timeout) {
        return null;
    }

    @Nonnull
    @Override
    public String toString() {
        return null;
    }

    @Nonnull
    @Override
    public String describe() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement highlight() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement highlight(HighlightOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement as(String alias) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement parent() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement sibling(int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement preceding(int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement lastChild() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement ancestor(String selector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement ancestor(String selector, int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement closest(String selector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement find(String cssSelector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement find(String cssSelector, int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement find(By selector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement find(By selector, int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $(String cssSelector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $(String cssSelector, int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $(By selector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $(By selector, int index) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $x(String xpath) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement $x(String xpath, int index) {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection findAll(String cssSelector) {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection findAll(By selector) {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection $$(String cssSelector) {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection $$(By selector) {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection $$x(String xpath) {
        return null;
    }

    @Nonnull
    @Override
    public File uploadFromClasspath(String... fileName) {
        return null;
    }

    @Nonnull
    @Override
    public File uploadFile(File... file) {
        return null;
    }

    @Override
    public void selectOption(int index, int... otherIndexes) {

    }

    @Override
    public void selectOption(String text, String... otherTexts) {

    }

    @Override
    public void selectOptionContainingText(String text, String... otherTexts) {

    }

    @Override
    public void selectOptionByValue(String value, String... otherValues) {

    }

    @Nonnull
    @Override
    public SelenideElement getSelectedOption() throws NoSuchElementException {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection getSelectedOptions() {
        return null;
    }

    @Nonnull
    @Override
    public ElementsCollection getOptions() {
        return null;
    }

    @Nullable
    @Override
    public String getSelectedOptionValue() {
        return null;
    }

    @Nullable
    @Override
    public String getSelectedValue() {
        return null;
    }

    @Nullable
    @Override
    public String getSelectedOptionText() {
        return null;
    }

    @Nullable
    @Override
    public String getSelectedText() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement scrollTo() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement scrollIntoView(boolean alignToTop) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement scrollIntoView(String scrollIntoViewOptions) {
        return null;
    }

    @Nonnull
    @Override
    public File download() throws FileNotFoundException {
        return null;
    }

    @Nonnull
    @Override
    public File download(long timeout) throws FileNotFoundException {
        return null;
    }

    @Nonnull
    @Override
    public File download(FileFilter fileFilter) throws FileNotFoundException {
        return null;
    }

    @Nonnull
    @Override
    public File download(long timeout, FileFilter fileFilter) throws FileNotFoundException {
        return null;
    }

    @Nonnull
    @Override
    public File download(DownloadOptions options) throws FileNotFoundException {
        return null;
    }

    @Nonnull
    @Override
    public String getSearchCriteria() {
        return null;
    }

    @Nonnull
    @Override
    public WebElement toWebElement() {
        return null;
    }

    @Nonnull
    @Override
    public WebElement getWrappedElement() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement cached() {
        return null;
    }

    @Override
    public SelenideElement click(ClickOptions clickOption) {
        return null;
    }

    @Override
    public void click() {
        this.delegate.click();
    }

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {

    }

    @Nonnull
    @Override
    public SelenideElement contextClick() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement doubleClick() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement doubleClick(ClickOptions clickOption) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement hover() {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement hover(HoverOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement dragAndDropTo(String targetCssSelector) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement dragAndDropTo(WebElement target) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement dragAndDropTo(String targetCssSelector, DragAndDropOptions options) {
        return null;
    }

    @Nonnull
    @Override
    public SelenideElement dragAndDrop(DragAndDropOptions options) {
        return null;
    }

    @Override
    public <ReturnType> ReturnType execute(Command<ReturnType> command) {
        return null;
    }

    @Override
    public <ReturnType> ReturnType execute(Command<ReturnType> command, Duration timeout) {
        return null;
    }

    @Override
    public boolean isImage() {
        return false;
    }

    @Nullable
    @Override
    public File screenshot() {
        return null;
    }

    @Nullable
    @Override
    public BufferedImage screenshotAsImage() {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public WebDriver getWrappedDriver() {
        return null;
    }

    @Override
    public Coordinates getCoordinates() {
        return null;
    }
}
