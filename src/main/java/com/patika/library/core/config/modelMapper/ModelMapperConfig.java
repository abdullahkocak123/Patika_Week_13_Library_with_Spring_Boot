package com.patika.library.core.config.modelMapper;

import com.patika.library.dto.request.book_borrowing.BookBorrowingSaveRequest;
import com.patika.library.enitites.BookBorrowing;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        // to skip null's so that modelmapper should not set other id's when saving
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // En doğru eşleşmeyi yapması için strict mod
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(BookBorrowingSaveRequest.class, BookBorrowing.class)
                .addMappings(mapper -> mapper.skip(BookBorrowing::setId));



        return modelMapper;
    }
}
