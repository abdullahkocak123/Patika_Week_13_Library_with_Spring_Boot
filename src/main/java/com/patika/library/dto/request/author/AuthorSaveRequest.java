package com.patika.library.dto.request.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {

    @NotNull(message = "İsim boş olamaz.")
    private String name;


    private Integer birthDate;


    private String country;


}
