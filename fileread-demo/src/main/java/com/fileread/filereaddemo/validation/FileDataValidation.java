package com.fileread.filereaddemo.validation;

import com.fileread.filereaddemo.entity.FileDataDetails;
import org.springframework.stereotype.Service;


public interface FileDataValidation {

    /**
     * This method will validate given data by ID not null and valid timestamp
     */
    public boolean fileDataValidator(FileDataDetails data);
}
