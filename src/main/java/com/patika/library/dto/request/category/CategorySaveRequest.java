package com.patika.library.dto.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {

    @NotNull(message = "Kategori ismi boş olamaz.")
    private String name;

    private String description;


}
