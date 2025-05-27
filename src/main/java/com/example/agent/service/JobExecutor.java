package com.example.agent.service;

import com.example.agent.model.JobRequest;
import com.example.agent.model.JobResult;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class JobExecutor {

    public JobResult execute(JobRequest request) {
        try {
            ProcessBuilder pb = new ProcessBuilder(request.getCommand().split(" "));
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            return new JobResult(exitCode, output.toString());
        } catch (Exception e) {
            return new JobResult(1, "Error executing job: " + e.getMessage());
        }
    }
}
