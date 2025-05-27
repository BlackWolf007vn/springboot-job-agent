package com.example.agent.controller;

import com.example.agent.model.JobRequest;
import com.example.agent.model.JobResult;
import com.example.agent.service.JobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobExecutor jobExecutor;

    @PostMapping("/run")
    public ResponseEntity<JobResult> runJob(@RequestBody JobRequest request) {
        return ResponseEntity.ok(jobExecutor.execute(request));
    }
}
