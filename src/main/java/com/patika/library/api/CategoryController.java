package com.patika.library.api;

import com.patika.library.business.abstracts.ICategoryService;
import com.patika.library.core.config.modelMapper.IModelMapperService;
import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;
import com.patika.library.core.utils.ResultHelper;
import com.patika.library.dto.request.category.CategorySaveRequest;
import com.patika.library.dto.request.category.CategoryUpdateRequest;
import com.patika.library.dto.response.category.CategoryResponse;
import com.patika.library.enitites.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);

        this.categoryService.save(saveCategory);

        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class);
        return ResultHelper.createdData(categoryResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);

        this.categoryService.update(updateCategory);

        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class);
        return ResultHelper.createdData(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") long id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(category, CategoryResponse.class);
        return ResultHelper.successData(categoryResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CategoryResponse>> getAll() {
        List<Category> categoryList = this.categoryService.getAll();
        List<CategoryResponse> categoryResponseList = categoryList.stream()
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class))
                .toList();

        return ResultHelper.successData(categoryResponseList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") long id){
        this.categoryService.delete(id);
        return ResultHelper.success();
    }

}
