package eu.senla.lab.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
    @JsonProperty("id")
    private String id;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sortable_name")
    private String sortableName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("deathday")
    private String deathday;
    @JsonProperty("hometown")
    private String hometown;
    @JsonProperty("location")
    private String location;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("target_supply")
    private Boolean targetSupply;
    @JsonProperty("image_versions")
    private List<String> imageVersions;
}
