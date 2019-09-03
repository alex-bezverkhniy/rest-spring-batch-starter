package com.github.alexbezverkhniy.springbatchrest.api.job;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest
public class JobControllerTest {
  public static final String JOB_NAME = "jobName";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private JobExplorer jobExplorer;

  @Test
  public void jobs() throws Exception {
    List<Job> want = Arrays.asList(new Job(JOB_NAME));

    when(jobExplorer.getJobNames()).thenReturn(Arrays.asList(JOB_NAME));

    mockMvc.perform(get("/batch/jobs").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(want)));

    // Empty list
    when(jobExplorer.getJobNames()).thenReturn(null);

    mockMvc.perform(get("/batch/jobs").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new ArrayList<Job>())));

    // Empty list
    when(jobExplorer.getJobNames()).thenReturn(new ArrayList<String>());

    mockMvc.perform(get("/batch/jobs").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new ArrayList<Job>())));


    verify(jobExplorer, times(3)).getJobNames();
  }

}
