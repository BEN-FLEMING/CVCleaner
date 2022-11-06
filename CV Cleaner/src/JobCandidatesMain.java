package com.bham.pij.assignments.candidates;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.nio.*;


public class JobCandidatesMain {

    public static void main(String[] args) {

        CleaningUp CleanUp = new CleaningUp();

        CleanUp.cleanUpFile();

        CandidatesToInterview InterviewCandidates = new CandidatesToInterview();

        InterviewCandidates.findCandidates();

        InterviewCandidates.candidatesWithExperience();

        InterviewCandidates.createCSVFile();

        InterviewCandidates.createReport();
    }

}
