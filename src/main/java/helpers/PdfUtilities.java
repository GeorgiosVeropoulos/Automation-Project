package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Log4j2
public class PdfUtilities {


    /**
     * Uses Selenide to download the pdf of the current page.
     * Must switch to the appropriate window before this method is called.
     * @return The filePath of the pdf.
     */
    public static String download() {
        String filePath = null;
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        try {
            File file = Selenide.download(url);
            filePath = file.getPath();
            log.info("Pdf download with file path = " + filePath);
        } catch (IOException | URISyntaxException e) {
            log.warn(e.getMessage());
        }
        return filePath;
    }

    public static String getTextFromPdf(String filePath) {
        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
            PDFTextStripper textStripper = new PDFTextStripper();
            return textStripper.getText(document);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        log.info("Pdf text coulnd't be loaded return empty String!");
        return "";
    }


    public static void verifyPdfContainsValue(String pdfFilePath, String value) {
        String pdfContent = getTextFromPdf(pdfFilePath);
        Assert.assertTrue(pdfContent.contains(value));

    }
}
