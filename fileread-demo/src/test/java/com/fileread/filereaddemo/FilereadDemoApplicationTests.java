package com.fileread.filereaddemo;

import com.fileread.filereaddemo.controller.FileReaderController;
import com.fileread.filereaddemo.entity.FileDataDetails;
import com.fileread.filereaddemo.exception.DataNotFoundException;
import com.fileread.filereaddemo.exception.FileNotFoundException;
import com.fileread.filereaddemo.service.FileDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FilereadDemoApplicationTests {

	@InjectMocks
	FileReaderController fileReaderController;

	@Mock
	FileDataService fileDataService;

	@Test
	public void uploadFileTest() throws Exception {
		doNothing().when(fileDataService).insertFileData(any());
		String output = fileReaderController.uploadFile("src/test/resources/inputTest.csv");
		assertEquals(output, "done");
	}

	@Test
	public void readFileTest() {
		when(fileDataService.fetchDataByID(any())).thenReturn(fetchData());
		FileDataDetails fileDataDetails = fileReaderController.fetchDataByID("1");
		assertNotNull(fileDataDetails);
	}

	private FileDataDetails fetchData() {
		FileDataDetails data= new FileDataDetails();
		data.setFileID("1");
		data.setName("Test");
		data.setDescriptions("Test Data");
		data.setUpdatedTime("2020-02-13T18:51:09.840Z");
		return data;
	}

	@Test
	public void deletFileTest() {
		doNothing().when(fileDataService).deleteDataByID(anyString());
		fileDataService.deleteDataByID("1");
		verify(fileDataService).deleteDataByID("1");
	}

	@Test
	public void deletExceptionTest() {
		doNothing().when(fileDataService).deleteDataByID(anyString());
		Exception ex=assertThrows(DataNotFoundException.class , ()->{
			fileReaderController.deleteDataByID("");
		});
		String output =ex.getMessage();
		assertEquals(output, "Id can not be null or Empty");
	}

	@Test
	public void uploadExceptionTest() throws Exception {
		doNothing().when(fileDataService).insertFileData(any());
		Exception ex=assertThrows(FileNotFoundException.class , ()->{
			fileReaderController.uploadFile("src/test/resources/inputTest1.csv");
		});
		String output =ex.getMessage();
		assertEquals(output, "File not found");
	}
}
