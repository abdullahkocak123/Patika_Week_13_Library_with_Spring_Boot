package com.patika.library.api;

import com.patika.library.business.abstracts.IPublisherService;
import com.patika.library.core.config.modelMapper.IModelMapperService;
import com.patika.library.core.result.Result;
import com.patika.library.core.result.ResultData;
import com.patika.library.core.utils.ResultHelper;
import com.patika.library.dto.request.publisher.PublisherSaveRequest;
import com.patika.library.dto.request.publisher.PublisherUpdateRequest;
import com.patika.library.dto.response.publisher.PublisherResponse;
import com.patika.library.enitites.Publisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);

        this.publisherService.save(savePublisher);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class);
        return ResultHelper.createdData(publisherResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);

        this.publisherService.save(updatePublisher);

        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class);
        return ResultHelper.createdData(publisherResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") long id) {
        Publisher publisher = this.publisherService.get(id);
        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(publisher, PublisherResponse.class);
        return ResultHelper.successData(publisherResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<PublisherResponse>> getAll() {
        List<Publisher> publisherList = this.publisherService.getAll();
        List<PublisherResponse> publisherResponseList =publisherList.stream()
                .map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class))
                .toList();

        return ResultHelper.successData(publisherResponseList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") long id){
        this.publisherService.delete(id);
        return ResultHelper.success();
    }

}
