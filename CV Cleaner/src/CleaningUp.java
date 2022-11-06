package com.bham.pij.assignments.candidates;

import java.io.*;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class CleaningUp {

    ArrayList<CV> laterCvs = new ArrayList<CV>();

    public ArrayList<CV> getLaterCvs() {
        return laterCvs;
    }

    public void cleanUpFile() {

        Path fileIn = Paths.get("dirtycv.txt");

        File cleanCV = new File("cleancv.txt");

        Path fileOut = cleanCV.toPath();

        if (!Files.isReadable(fileIn)) {
            System.err.println("dirtycv.txt not readable");
            System.exit(1);
        }

        ArrayList<String> lines = new ArrayList<String>();

        try (Scanner reader = new Scanner(fileIn);) {
            Boolean restart = null;
            String line = null;
            CV firstCV = new CV();


            while (reader.hasNext()) {
                line = reader.nextLine();
                lines.add(line);
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

        int CVCount = countCVs(lines);

        ArrayList<Integer> endPositions = new ArrayList<Integer>();

        endPositions = getEndPositions(lines);

        String[] splitLine = splitDataColon(lines.get(4));

        ArrayList<CV> organisedCVs = convertToCVList(lines, CVCount, endPositions);

        for (int i = 0; i < organisedCVs.size(); i++) {
            laterCvs.add(organisedCVs.get(i));
        }

        String[] cleanWriteLines = new String[20];

        for (int i = 0; i < CVCount; i++) {
            StringBuilder tempString = new StringBuilder();

            tempString.append(organisedCVs.get(i).getSurname());
            tempString.append("000");
            tempString.append(i + 1);
            tempString.append(",");
            if (organisedCVs.get(i).getQualification() != null) {
                tempString.append(organisedCVs.get(i).getQualification());
                tempString.append(",");
            }
            if (organisedCVs.get(i).getPosition() != null) {
                tempString.append(organisedCVs.get(i).getPosition());
                tempString.append(",");
            }
            if (organisedCVs.get(i).getExperience() != null) {
                tempString.append(organisedCVs.get(i).getExperience());
                tempString.append(",");
            }
            if (organisedCVs.get(i).getPositionTwo() != null) {
                tempString.append(organisedCVs.get(i).getPositionTwo());
                tempString.append(",");
            }
            if (organisedCVs.get(i).getExperienceTwo() != null) {
                tempString.append(organisedCVs.get(i).getExperienceTwo());
                tempString.append(",");
            }
            if (organisedCVs.get(i).geteMail() != null) {
                tempString.append(organisedCVs.get(i).geteMail());
                tempString.append(",");
            }
            cleanWriteLines[i] = tempString.toString();

        }

        try (BufferedWriter writer = Files.newBufferedWriter(fileOut);) {

            for (int i = 0; i < CVCount; i++) {
                String toWrite = cleanWriteLines[i];
                writer.write(toWrite);
                writer.newLine();
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

    }

    public int countCVs(ArrayList lineStore){

        ArrayList<String> newLines = new ArrayList<String>();

        int endCounter = 0;

        String endString = "End";

        for (int i = 0; i < lineStore.size(); i++) {
            newLines.add(lineStore.get(i).toString());
        }


        for (int i = 0; i < newLines.size(); i++) {
            if (newLines.get(i).equals(endString)){
                endCounter++;
            }
        }

        return endCounter;
    }

    public ArrayList<Integer> getEndPositions(ArrayList lines) {

        String endString = "End";

        ArrayList<Integer> endPositions = new ArrayList<Integer>();

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(endString)){
                endPositions.add(i);
            }
        }

        return endPositions;

    }

    public String[] splitDataColon(String line){

        String toStrip = line;

        String[] stripped = toStrip.split(":");

        return stripped;
    }

    public ArrayList<CV> convertToCVList (ArrayList<String> lines, int CVCount, ArrayList<Integer> endPositions){

        ArrayList<CV> CVStore = new ArrayList<CV>();

        int recursionsNeeded = CVCount;

        int startIndex = 0;

        CV tempCV = null;

        for (int i = 0; i < recursionsNeeded; i++) {
            tempCV = new CV();
            for (int j = startIndex+1; j < endPositions.get(i); j++) {
                String[] splitLine = splitDataColon(lines.get(j));
                tempCV.addToCv(splitLine);
            }
            CVStore.add(tempCV);
            startIndex = endPositions.get(i);
        }

        return CVStore;
    }

}
