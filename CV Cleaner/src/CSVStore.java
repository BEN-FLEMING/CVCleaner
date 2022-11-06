package com.bham.pij.assignments.candidates;

import java.util.ArrayList;

public class CSVStore {

    ArrayList<String> CSVStore = new ArrayList<String>();

    public CSVStore() {
    }

    public ArrayList<String> getCSVStore() {
        return CSVStore;
    }

    public void setCSVStore(ArrayList<String> CSVStore) {
        this.CSVStore = CSVStore;
    }

    public void splitAndAdd(String csvLine){

        CSVStore toReturn = new CSVStore();

        ArrayList<String> toCSV = new ArrayList<>();

        String[] splitLine = csvLine.split(",");

        for (int i = 0; i < splitLine.length; i++) {
            toCSV.add(splitLine[i]);
        }

        toReturn.setCSVStore(toCSV);
    }

}
