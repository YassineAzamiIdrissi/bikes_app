package com.yassine.practice.Bike;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BikeRequest {
    private Integer id;
    @NotNull(message = "the bike name has to be specified")
    @NotEmpty(message = "the bike name mustn't be empty")
    @NotBlank(message = "the bike name mustn't be blank")
    private String bikeName;
    @NotNull(message = "the bike description has to be specified")
    @NotEmpty(message = "the bike description mustn't be empty")
    @NotBlank(message = "the bike description mustn't be blank")
    private String description;
    @NotNull(message = "techIdentifier has to be specified")
    @NotEmpty(message = "techIdentifier mustn't be empty")
    @NotBlank(message = "techIdentifier mustn't be blank")
    private String techIdentifier;
    @NotNull(message = "price has to be specified")
    private boolean shareable;
}
