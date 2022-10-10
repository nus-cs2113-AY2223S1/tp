package seedu.files.parsing;

import org.codehaus.jackson.annotate.JsonProperty;
import seedu.data.Carpark;

import java.util.List;

/**
 * Blueprint class for the {@link org.codehaus.jackson.map.ObjectMapper} class
 * to build
 */
public class LtaJsonWrapper {
    private String metadata;
    private List<Carpark> value;
    public String getMetadata() {
        return metadata;
    }

    @JsonProperty("odata.metadata")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public List<Carpark> getValue() {
        return value;
    }

    public void setValue(List<Carpark> value) {
        this.value = value;
    }
}
