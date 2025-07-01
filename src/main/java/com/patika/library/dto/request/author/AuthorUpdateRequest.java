package com.patika.library.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @NotNull(message = "Id değeri null olamaz")
    @Positive(message = "Id değeri pozitif değer olmak zorunda")
    private Long id;

    @NotNull(message = "İsim boş olamaz.")
    private String name;


    private Integer birthDate;


    private String country;


}
