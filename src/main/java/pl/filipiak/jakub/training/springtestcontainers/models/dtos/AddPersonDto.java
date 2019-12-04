package pl.filipiak.jakub.training.springtestcontainers.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPersonDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(0)
    @NotNull
    private Integer age;
}
