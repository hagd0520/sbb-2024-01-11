package com.ll.sbb20240111.batch;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BatchController.class, HelloJobConfig.class})
@SpringBatchTest
public class BatchControllerTest {
    private JobLauncherTestUtils jobLauncherTestUtils;

}
