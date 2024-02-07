package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import constants.TestConstants;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        log.info("URL to download file was: " + url);
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

    public static String downloadFile(SelenideElement button) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = null;
        String buttonUrl = button.getAttribute("href");
        try {
            uri = new URI(buttonUrl);
        } catch (URISyntaxException uriSyntaxException) {
            log.error(uriSyntaxException.getMessage());
        }
        HttpGet httpGet = new HttpGet(uri);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();

            // Create directory if it doesn't exist
            log.info("File will be downloaded in: " + TestConstants.DOWNLOADS_FOLDER);
            Path directory = Path.of(TestConstants.DOWNLOADS_FOLDER);
            Files.createDirectories(directory);

            // Generate file name based on URL

            String fileName = System.currentTimeMillis() + buttonUrl.substring(buttonUrl.lastIndexOf('/') + 1) ;
            String filePath = directory  + File.separator + fileName;
            log.info("FilePath is : " + filePath);
            // Create file output stream to save the downloaded file
            OutputStream outputStream = new FileOutputStream(filePath);

            // Copy input stream to output stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            outputStream.close();
            inputStream.close();
            httpClient.close();

            return filePath;
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
            return "";
        }
    }
}
