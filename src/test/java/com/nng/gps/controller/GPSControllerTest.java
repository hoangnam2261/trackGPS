package com.nng.gps.controller;

import com.nng.gps.validator.GPXFileValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GPSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUploadFileWithoutCredential() throws Exception {
        File sample = new File("src/test/java/com/nng/gps/sample_test_file/sample.gpx");
        try(FileInputStream inputStream = new FileInputStream(sample)) {
            MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                    sample.getName(),
                    GPXFileValidator.GPX_CONTENT_TYPE,
                    inputStream);
            mockMvc.perform(MockMvcRequestBuilders
                    .fileUpload("/gpx/uploadFile")
                    .file(mockMultipartFile))
                    .andExpect(status().isUnauthorized());
        }

    }

    @WithMockUser(username = "hoangnam2261", password = "123456", roles = "USER")
    @Test
    public void testUploadFileWithAuthenCredential() throws Exception {
        File sample = new File("src/test/java/com/nng/gps/sample_test_file/sample.gpx");
        try(FileInputStream inputStream = new FileInputStream(sample)) {
            MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                    sample.getName(),
                    GPXFileValidator.GPX_CONTENT_TYPE,
                    inputStream);
            mockMvc.perform(MockMvcRequestBuilders
                    .fileUpload("/gpx/uploadFile")
                    .file( mockMultipartFile))
                    .andExpect(status().isCreated());
        }
    }
}