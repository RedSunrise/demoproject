package apitests;

import apitests.apiinfo.Counter;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MyCsvParser {
    public static List<String[]> read(String path, int numberOfRows){
        try (var inputReader = new InputStreamReader(new FileInputStream(new File(path)), "UTF-8")) {
            var settings = new CsvParserSettings();
            int toRead = numberOfRows;
            int toSkip = Counter.getAndAdd(toRead);
            settings.setNumberOfRowsToSkip(toSkip);
            settings.setNumberOfRecordsToRead(toRead);
            return new CsvParser(settings).parseAll(inputReader);
        } catch (IOException e) {
            return null;
        }
    }
}
