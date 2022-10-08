package seedu.files;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import seedu.common.CommonFiles;
import seedu.data.Carpark;
import seedu.files.parsing.LtaJsonWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileLoader {

    public static List<Carpark> loadLtaJson() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(CommonFiles.API_JSON_DIRECTORY, "ltaResponseSample.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.reader(LtaJsonWrapper.class);
        LtaJsonWrapper wrapperObject = objectReader.readValue(jsonData);
        return wrapperObject.getValue();
    }

}
