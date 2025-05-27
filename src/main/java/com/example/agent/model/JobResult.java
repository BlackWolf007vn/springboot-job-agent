package com.example.agent.model;

public class JobResult {
    private int exitCode;
    private String output;

    public JobResult(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;
    }

    public int getExitCode() { return exitCode; }
    public void setExitCode(int exitCode) { this.exitCode = exitCode; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }
}
