package seedu.files.parsing;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import seedu.data.Carpark;

/**
 * Blueprint class for the {@link org.codehaus.jackson.map.ObjectMapper} class to build.
 */
public class LtaJsonWrapper {
    private String metadata;
    private List<Carpark> value;

    /**
     * Getter method to retrieve metadata.
     *
     * @return Metadata.
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Setter method to set metadata.
     *
     * @param metadata metadata to be set.
     */
    @JsonProperty("odata.metadata")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * Getter method to retrieve value.
     *
     * @return Value.
     */
    public List<Carpark> getValue() {
        return value;
    }

    /**
     * Setter method to set value.
     *
     * @param value value to be set.
     */
    public void setValue(List<Carpark> value) {
        this.value = value;
    }
}
