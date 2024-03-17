package managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AutomationException;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class DataManager {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads the values of the json file with the same name as the Test
     * and assigns them into a Map.
     * @return the map of the values.
     */
    public static Map<String, Object> getTestData()  {
        Map map = null;
        String filePath = "src/main/resources/data/" + getCallingClass() + ".json";
        try {
            map = objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            log.error("Make sure name of Json matches Test name " + e.getMessage());
        }
        return map;
    }

    /**
     * Get the {@code List<String>} from the data provided by the usage of a key.
     * @param data the json data to be provided.
     * @param key the key we want to use as a {@code List<String>}.
     * @return a new {@code List<String>}.
     */
    public static List<String> getListOfStrings(Map<?, ?> data, String key) {
        Object value = data.get(key);
        List<String> returnList = new ArrayList<>();
        if (value instanceof List<?>) {
            for (Object obj : (List<?>) value) {
                if (obj instanceof String) {
                    returnList.add((String) obj);
                } else {
                    log.warn("For key:" + key + " value ->" + obj + " was not a string! will convert it.");
                    returnList.add(String.valueOf(obj));
                }
            }
        } else {
            log.error("For key:" + key + " -> value was not a List check .json file.");
            throw new AutomationException();
        }
        return returnList;
    }



    private static String getCallingClass() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            throw new AutomationException("Unable to determine calling class");
        }
        // the class calling this will always be the 4 element in the stackTrace
        // if this changes is because we call something else in betweeen the following methods
        // 0-getStackTrace
        // 1-getCallingClass
        // 2-getTestDataManager
        // 3-Test assigning the data in the @BeforeMethod setUp.
        return trimToClassName(stackTrace[3].getClassName());
    }

    /**
     * this will trim the stackTrace to only the className.
     * ex: {@code com.example.testngparallel.tests.C1_VerifyLinksWorkInInfoSectionTest}.
     * will become this -> C1_VerifyLinksWorkInInfoSectionTest
     * @param fullyQualifiedClassName the full class path to be provided.
     * @return the ClassName.
     */
    public static String trimToClassName(String fullyQualifiedClassName) {
        int lastDotIndex = fullyQualifiedClassName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fullyQualifiedClassName.substring(lastDotIndex + 1);
        } else {
            return fullyQualifiedClassName;
        }
    }
}
