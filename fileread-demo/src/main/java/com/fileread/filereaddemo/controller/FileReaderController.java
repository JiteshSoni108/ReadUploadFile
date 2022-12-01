package com.fileread.filereaddemo.controller;

import com.fileread.filereaddemo.entity.FileDataDetails;
import com.fileread.filereaddemo.exception.DataNotFoundException;
import com.fileread.filereaddemo.exception.FileNotFoundException;
import com.fileread.filereaddemo.service.FileDataService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file/read")
public class FileReaderController {

    @Autowired
    private FileDataService fileDataService;

    @GetMapping( value="/fetch/{id}")
    public FileDataDetails fetchDataByID(@PathVariable String id){
        return fileDataService.fetchDataByID(id);
    }

    @PostMapping ("/upload")
    public String uploadFile(@RequestParam("filePath") String filePath) throws Exception{

        if(null==filePath || (filePath.isEmpty() || filePath.isBlank()))
            throw new FileNotFoundException("File path can not be null or Empty");

        try(FileReader input = new FileReader(filePath)){
            List<FileDataDetails> fileData= new ArrayList<>();
            //InputStream inputStream=file.getInputStream();
            CsvParserSettings setting= new CsvParserSettings();
            setting.setHeaderExtractionEnabled(true);
            CsvParser parser= new CsvParser(setting);
            List<String[]> allRows=parser.parseAll(input);
            fileDataService.insertFileData(allRows);
        }catch(Exception ex){
            throw new FileNotFoundException("File not found");
        }
        return "done";
    }
    @DeleteMapping( value="/delete/{id}")
    public void deleteDataByID(@PathVariable String id){
        if(null != id && !(id.isBlank() && id.isEmpty()))
            fileDataService.deleteDataByID(id);
        else throw new DataNotFoundException("Id can not be null or Empty");
    }
}
