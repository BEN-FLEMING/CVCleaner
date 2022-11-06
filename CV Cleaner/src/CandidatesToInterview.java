package com.bham.pij.assignments.candidates;

import javax.print.DocFlavor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CandidatesToInterview {

    String [] keywordsDegree = {"Degree in Computer Science", "Masters in Computer Science"};

    String [] keywordsExperience = {"Data Analyst", "Programmer", "Computer programmer", "Operator"};

    ArrayList<ArrayList<String>> tempToInterviewStore = new ArrayList<ArrayList<String>>();

    public void findCandidates(){

        Path fileIn = Paths.get("cleancv.txt");

        File toInterview = new File("to-interview.txt");

        Path fileOut = toInterview.toPath();

        if (!Files.isReadable(fileIn)) {
            System.err.println("cleancv.txt not readable");
            System.exit(1);
        }

        ArrayList<String> csvLines = new ArrayList<String>();

        int csvLineCount = 0;

        try (Scanner reader = new Scanner(fileIn);) {

            String line = null;

            while (reader.hasNext()) {
                line = reader.nextLine();
                csvLines.add(line);
                csvLineCount++;
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

        ArrayList<ArrayList<String>> splitCsvList = new ArrayList<>();

        for (int i = 0; i < csvLineCount; i++) {
            ArrayList<String> temp = new ArrayList<String>();
            temp = toSplitList(csvLines.get(i));
            splitCsvList.add(temp);
        }


        ArrayList<ArrayList<String>> csvListWithQual = new ArrayList<>();

        for (int i = 0; i < splitCsvList.size(); i++) {
            if (splitCsvList.get(i).get(1).equals(keywordsDegree[0]) || splitCsvList.get(i).get(1).equals(keywordsDegree[1])){
                csvListWithQual.add(splitCsvList.get(i));
            }
        }

        ArrayList<ArrayList<String>> csvListWithQualAndExp = new ArrayList<>();

        for (int i = 0; i < csvListWithQual.size(); i++) {
            if (csvListWithQual.get(i).get(2).equals(keywordsExperience[0]) || csvListWithQual.get(i).get(4).equals(keywordsExperience[0]) || csvListWithQual.get(i).get(2).equals(keywordsExperience[1]) || csvListWithQual.get(i).get(4).equals(keywordsExperience[1]) || csvListWithQual.get(i).get(2).equals(keywordsExperience[2]) || csvListWithQual.get(i).get(4).equals(keywordsExperience[2]) || csvListWithQual.get(i).get(2).equals(keywordsExperience[3]) || csvListWithQual.get(i).get(4).equals(keywordsExperience[3])){
                csvListWithQualAndExp.add(csvListWithQual.get(i));
            }
        }

        String[] toInterviewWriteLines = new String[csvLineCount-1];

        String toBeWritten;

        for (int i = 0; i < csvListWithQualAndExp.size(); i++) {

            tempToInterviewStore.add(csvListWithQualAndExp.get(i));

            StringBuilder writeBuild = new StringBuilder();

            for (int j = 0; j < csvListWithQualAndExp.get(i).size(); j++) {
                writeBuild.append(csvListWithQualAndExp.get(i).get(j));
                if (j != csvListWithQualAndExp.get(i).size()-1){
                    writeBuild.append(" ");
                }
            }
            toBeWritten = writeBuild.toString();

            toInterviewWriteLines[i] = toBeWritten;
        }

        /*

        for (int i = 0; i < toInterviewWriteLines.length; i++) {
            System.out.println(toInterviewWriteLines[i]);
        }


         */

        try (BufferedWriter writer = Files.newBufferedWriter(fileOut);) {

            for (int i = 0; i < csvLineCount-1; i++) {
                String toWrite = toInterviewWriteLines[i];
                writer.write(toWrite);
                writer.newLine();
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

    }

    public ArrayList<String> toSplitList(String csvLine){

        ArrayList<String> toCSV = new ArrayList<>();

        String[] splitLine = csvLine.split(",");

        for (int i = 0; i < splitLine.length; i++) {
            toCSV.add(splitLine[i]);
        }

        return toCSV;
    }

    public ArrayList<String> toSplitListSpace(String csvLine){

        ArrayList<String> splitBySpace = new ArrayList<>();

        String[] splitLine = csvLine.split(" ");

        for (int i = 0; i < splitLine.length; i++) {
            splitBySpace.add(splitLine[i]);
        }

        return splitBySpace;
    }

    public void candidatesWithExperience(){

        Path fileIn = Paths.get("to-interview.txt");

        File toInterviewWithExp = new File("to-interview-experience.txt");

        Path fileOut = toInterviewWithExp.toPath();

        if (!Files.isReadable(fileIn)) {
            System.err.println("to-interview.txt not readable");
            System.exit(1);
        }

        ArrayList<String> toInterviewWithExpLines = new ArrayList<String>();

        int toInterviewLineCount = 0;

        try (Scanner reader = new Scanner(fileIn);) {

            String line = null;

            while (reader.hasNext()) {
                line = reader.nextLine();
                toInterviewWithExpLines.add(line);
                toInterviewLineCount++;
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

        ArrayList<ArrayList<String>> splitToInterviewList = new ArrayList<>();

        for (int i = 0; i < toInterviewLineCount; i++) {
            ArrayList<String> temp = new ArrayList<String>();
            temp = toSplitListSpace(toInterviewWithExpLines.get(i));
            splitToInterviewList.add(temp);
        }

        ArrayList<ArrayList<String>> realSplitToInterviewList = new ArrayList<>();

        for (int i = 0; i < tempToInterviewStore.size(); i++) {
            realSplitToInterviewList.add(tempToInterviewStore.get(i));
        }

        ArrayList<ArrayList<String>> toInterviewExperience = new ArrayList<>();

        for (int i = 0; i < realSplitToInterviewList.size(); i++) {
            int comparisonNum = Integer.parseInt(realSplitToInterviewList.get(i).get(3));
            if (comparisonNum > 5){
                toInterviewExperience.add(realSplitToInterviewList.get(i));
            }
        }

        String[] toInterviewWriteLines = new String[toInterviewExperience.size()-1];

        String toBeWritten;

        for (int i = 0; i < toInterviewExperience.size()-1; i++) {

            StringBuilder writeBuild = new StringBuilder();

            writeBuild.append(toInterviewExperience.get(i).get(0));

            writeBuild.append(" ");

            writeBuild.append(toInterviewExperience.get(i).get(3));

            toBeWritten = writeBuild.toString();

            toInterviewWriteLines[i] = toBeWritten;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(fileOut);) {

            for (int i = 0; i < toInterviewWriteLines.length; i++) {
                String toWrite = toInterviewWriteLines[i];
                writer.write(toWrite);
                writer.newLine();
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

    }

    public void createCSVFile(){

        ArrayList<ArrayList<String>> forCsvList = new ArrayList<>();

        for (int i = 0; i < tempToInterviewStore.size(); i++) {
            forCsvList.add(tempToInterviewStore.get(i));
        }

        ArrayList<String> csvLine = new ArrayList<>();

        for (int i = 0; i < forCsvList.size(); i++) {
            StringBuilder sb1 = new StringBuilder();
            if (forCsvList.get(i).size() > 5){
                for (int j = 0; j < forCsvList.get(i).size(); j++) {
                    sb1.append(forCsvList.get(i).get(j));
                    sb1.append(",");
                }
            }
            if (forCsvList.get(i).size() <= 5){
                sb1.append(forCsvList.get(i).get(0));
                sb1.append(",");
                sb1.append(forCsvList.get(i).get(1));
                sb1.append(",");
                sb1.append(forCsvList.get(i).get(2));
                sb1.append(",");
                sb1.append(forCsvList.get(i).get(3));
                sb1.append(",");
                sb1.append(",");
                sb1.append(",");
                sb1.append(forCsvList.get(i).get(4));
                sb1.append(",");
            }
            csvLine.add(sb1.toString());
        }

        File csvFileOut = new File("to-interview-table-format.csv");

        try (PrintWriter pw = new PrintWriter(csvFileOut)){

            StringBuilder sb = new StringBuilder();

            sb.append("Identifier");
            sb.append(",");
            sb.append("Qualification");
            sb.append(",");
            sb.append("Position1");
            sb.append(",");
            sb.append("Experience1");
            sb.append(",");
            sb.append("Position2");
            sb.append(",");
            sb.append("Experience2");
            sb.append(",");
            sb.append("eMail");
            sb.append(",");
            sb.append("\r\n");

            for (int i = 0; i < csvLine.size(); i++) {
                sb.append(csvLine.get(i));
                sb.append("\r\n");
            }

            pw.write(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createReport(){

        ArrayList<ArrayList<String>> fromCsv = new ArrayList<>();

        Path fileIn = Paths.get("to-interview-table-format.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileIn.toString()))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokenStore = line.split(",");
                ArrayList<String> tempList = new ArrayList<>();
                for (int i = 0; i < tokenStore.length; i++) {
                    tempList.add(tokenStore[i]);
                }
                fromCsv.add(tempList);
            }

        } catch (IOException ioException) {
            System.out.println("Something went wrong");
            ioException.printStackTrace();
        }

        for (int i = 0; i < fromCsv.size(); i++) {
            System.out.printf("%-20s%-30s%-30s%-20s%-30s\n", fromCsv.get(i).get(0), fromCsv.get(i).get(1), fromCsv.get(i).get(2), fromCsv.get(i).get(3), fromCsv.get(i).get(6));
        }
    }
}
