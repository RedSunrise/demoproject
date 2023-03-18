package apitests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;

import java.util.LinkedList;
import java.util.stream.Stream;

public class MyArgumentsProvider implements org.junit.jupiter.params.provider.ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        var argList = new LinkedList<Arguments>();
        for (String[] strArray : MyCsvParser.read("src/test/resources/GeorgianTowns.csv", 3)) {
            if (strArray[1] != null) {
                argList.add(Arguments.of(strArray[0], Integer.parseInt(strArray[1])));
            }
        }
        return argList.stream();
    }
}
