package com.patika.library.api;

import com.patika.library.business.abstracts.IAuthorService;
import com.patika.library.core.config.modelMapper.IModelMapperService;
import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;
import com.patika.library.core.utils.ResultHelper;
import com.patika.library.dto.request.author.AuthorSaveRequest;
import com.patika.library.dto.request.author.AuthorUpdateRequest;
import com.patika.library.dto.response.author.AuthorResponse;
import com.patika.library.enitites.Author;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);

        this.authorService.save(saveAuthor);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class);
        return ResultHelper.createdData(authorResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);

        this.authorService.update(updateAuthor);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class);
        return ResultHelper.createdData(authorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") long id) {
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(author, AuthorResponse.class);
        return ResultHelper.successData(authorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AuthorResponse>> getAll() {
        List<Author> authorList = this.authorService.getAll();
        List<AuthorResponse> authorResponseList = authorList.stream()
                .map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class))
                .toList();

        return ResultHelper.successData(authorResponseList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") long id){
        this.authorService.delete(id);
        return ResultHelper.success();
    }

}
