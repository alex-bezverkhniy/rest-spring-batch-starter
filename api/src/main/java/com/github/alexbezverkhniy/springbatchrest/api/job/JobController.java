package com.github.alexbezverkhniy.springbatchrest.api.job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

  @Autowired
  private JobExplorer jobExplorer;

  @GetMapping("/batch/jobs")
  public List<Job> jobs() {
    List<String> jobNames = jobExplorer.getJobNames();
    List<Job> result = new ArrayList<>();
    if (jobNames != null && !jobNames.isEmpty()) {
      result = jobNames.stream().map(name -> {
        return new Job(name);
      }).collect(Collectors.toList());
    }
    return result;
  }
}
