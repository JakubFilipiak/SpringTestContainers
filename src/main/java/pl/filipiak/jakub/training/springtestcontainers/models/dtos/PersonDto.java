package pl.filipiak.jakub.training.springtestcontainers.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    @Min(0)
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(0)
    @NotNull
    private Integer age;
}
