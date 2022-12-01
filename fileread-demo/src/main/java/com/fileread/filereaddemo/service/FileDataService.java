package com.fileread.filereaddemo.service;

import com.fileread.filereaddemo.entity.FileDataDetails;
import com.fileread.filereaddemo.entity.FileDataRepository;
import com.fileread.filereaddemo.exception.DataNotFoundException;
import com.fileread.filereaddemo.validation.imp.FileDataValidationImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileDataService {
    @Autowired
    private FileDataValidationImp fileDataValidationImp;

    @Autowired
    private FileDataRepository fileDataRepository;

    /**
     * This method will insert file data in db
     */
    public void insertFileData(final List<String[]> allRecords){
        List<FileDataDetails> validFileData= new ArrayList<>();
        //List<FileDataDetails> inValidFileData= new ArrayList<>();
        allRecords.stream().parallel().forEach(data->{
            String fileId = data[0];
            String name = data[1];
            String description = data[2];
            String date = data[3];
            FileDataDetails fileDts= new FileDataDetails();
            fileDts.setFileID(fileId);
            fileDts.setName(name);
            fileDts.setDescriptions(description);
            fileDts.setUpdatedTime(date);
            if(fileDataValidationImp.fileDataValidator(fileDts)) validFileData.add(fileDts);
            //else inValidFileData.add(fileDts);
        });
        fileDataRepository.saveAll(validFileData);
        //return inValidFileData;
    }

    /**
     * This method will fetch data by fileId
     */
    public FileDataDetails fetchDataByID(final String fileId){
        //fileDataRepository.findById(Integer.valueOf(fileId)).orElse
        return fileDataRepository.findById(fileId).orElseThrow(() -> new DataNotFoundException("Could not find employee " + fileId));
    }

    /**
     * This method will fetch data by fileId
     */
    public void deleteDataByID(final String fileId){
        fileDataRepository.deleteById(fileId);
    }
}
