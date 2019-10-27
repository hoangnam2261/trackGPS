package com.nng.gps.validator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GPXFileValidatorTest {

    @Test
    public void validateGPXFileOK() throws IOException {
        File file_159_KB = new File("src/test/java/com/nng/gps/sample_test_file/sample.gpx");
        try(FileInputStream inputStream = new FileInputStream(file_159_KB);) {
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            file_159_KB.getName(),
                            GPXFileValidator.GPX_CONTENT_TYPE,
                            inputStream);
            Assert.assertTrue(GPXFileValidator.validateGPXFile(mockMultipartFile, 500));
        }
    }

    @Test
    public void validateGPXFileContentTypeFail() throws IOException {
        File file_159_KB = new File("src/test/java/com/nng/gps/sample_test_file/sample.gpx");
        try(FileInputStream inputStream = new FileInputStream(file_159_KB);) {
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            file_159_KB.getName(),
                            "application/json",
                            inputStream);
            Assert.assertFalse(GPXFileValidator.validateGPXFile(mockMultipartFile, 500));
        }
    }

    @Test
    public void validateGPXFileSizeFail() throws IOException {
        File file_800_KB = new File("src/test/java/com/nng/gps/sample_test_file/big_sample.xml");
        try(FileInputStream inputStream = new FileInputStream(file_800_KB);) {
            MockMultipartFile mockMultipartFile =
                    new MockMultipartFile(
                            "file",
                            file_800_KB.getName(),
                            GPXFileValidator.GPX_CONTENT_TYPE,
                            inputStream);
            Assert.assertFalse(GPXFileValidator.validateGPXFile(mockMultipartFile, 500));
        }
    }
}