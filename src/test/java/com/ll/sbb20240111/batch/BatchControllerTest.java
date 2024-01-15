package com.ll.sbb20240111.batch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
public class BatchControllerTest {
    @Autowired
    private JobLauncherTestUtils hello1JobLauncherTestUtils;
    @Autowired
    private JobLauncherTestUtils hello2JobLauncherTestUtils;
    @Autowired
    private JobLauncherTestUtils hello3JobLauncherTestUtils;

    @DisplayName("hello1Job")
    @Test
    public void t1() throws Exception {
        hello1JobLauncherTestUtils.launchJob();
    }


    @DisplayName("hello2Job")
    @Test
    public void t2() throws Exception {
        hello2JobLauncherTestUtils.launchJob();
    }
}
