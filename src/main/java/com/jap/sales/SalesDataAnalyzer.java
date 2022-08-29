package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SalesDataAnalyzer {
    public static void main(String[] args) {
        SalesDataAnalyzer salesDataAnalyzer = new SalesDataAnalyzer();
        String fileName = "src/main/resources/purchase_details.csv";
        SalesRecord[] salesRecords = salesDataAnalyzer.readFile(fileName);
        for (int i = 0; i < salesRecords.length; i++) {
            System.out.println(salesRecords[i]);
        }
    }


    // This method reads a file and adds each line of the file into the corresponding SalesRecord object
    public SalesRecord[] readFile(String fileName) {
        SalesRecord[] salesRecords = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {

                ////re-initializing file-reader and buffer reader
                fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
                line=bufferedReader.readLine();
                int index = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] split = line.split(",");
                    String date = split[0];
                    int customer_id =Integer.parseInt(split[1]);
                    int product_category =Integer.parseInt(split[2]);
                    String payment_method = split[3];
                    double amount = Double.parseDouble(split[4]);
                    double time_on_site = Double.parseDouble(split[5]);
                    int clicks_in_site = Integer.parseInt(split[6]);

                    salesRecords[index]=new SalesRecord(date,customer_id,product_category,payment_method,
                            time_on_site,clicks_in_site);
                    index++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesRecords;
    }


}
