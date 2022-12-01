package com.fileread.filereaddemo.validation.imp;

import com.fileread.filereaddemo.entity.FileDataDetails;
import com.fileread.filereaddemo.validation.FileDataValidation;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Service
public class FileDataValidationImp implements FileDataValidation {

    private DateTimeFormatter dateFormatter;

    @Override
    public boolean fileDataValidator(FileDataDetails data) {
        if (null != data) {
            if (null != data.getFileID() && !(data.getFileID().isBlank() && data.getFileID().isEmpty())) {
                return checkValidDate(data.getUpdatedTime());
            }
        }
        return false;
    }

    /**
     * This method will check given string is valid IOS8601 Date standard
     */
    private boolean checkValidDate(String updateTime) {
        if (null != updateTime && !(updateTime.isBlank() && updateTime.isEmpty())) {
            try {
                TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(updateTime);
                Instant i = Instant.from(ta);
                Date d = Date.from(i);
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return true;
    }
}
