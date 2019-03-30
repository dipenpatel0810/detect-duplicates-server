package com.example.detectduplicates;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeDuplicate implements DeDuplicateData {
  private Metaphone metaphone;
  private LevenshteinDistance levenshteinDistance;
  private String fileName;
  private List<String> first_name;
  private List<String> last_name;
  private List<String> company;
  private List<String> email;
  private List<String> allData;
  private List<String> uniqueData;
  private List<String> duplicateSet;
  private Iterable<CSVRecord> records;

  public DeDuplicate() {
    metaphone = new Metaphone();
    metaphone.setMaxCodeLen(20);
    levenshteinDistance = new LevenshteinDistance();
    fileName = "src/main/docs/normal.csv";
    first_name = new ArrayList<>();
    last_name = new ArrayList<>();
    company = new ArrayList<>();
    email = new ArrayList<>();
    allData = new ArrayList<>();
    uniqueData = new ArrayList<>();
    duplicateSet = new ArrayList<>();
  }

  @Override
  public void setFile(String fileLocation) {
    fileName = fileLocation;
    File file = new File(fileLocation);
    try {
      Reader in = new FileReader(fileLocation);
      Scanner inputStream = new Scanner(file);
      try {
        records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        inputStream.nextLine();
        while (inputStream.hasNext()) {
          String line = inputStream.nextLine();
          allData.add(line);
          uniqueData.add(line);
        }
        for (CSVRecord record : records) {
          first_name.add(record.get("first_name"));
          last_name.add(record.get("last_name"));
          company.add(record.get("company"));
          email.add(record.get("email"));
        }

      } catch (IOException io) {
        System.out.println("IO Exception");
      }
    } catch (FileNotFoundException f) {
      System.out.println("File not found");
    }
  }

  @Override
  public void deDuplicateData() {

    List<Integer> duplicateRecords = new ArrayList<>();

    int number = (int) ((CSVParser) records).getRecordNumber();


    for (int i = 0; i < number; i++) {
      String metaFirstName = metaphone.metaphone(first_name.get(i));
      String metaLastName = metaphone.metaphone(last_name.get(i));
      String metaCompany = metaphone.metaphone(company.get(i));
      String metaEmail = metaphone.metaphone(email.get(i));
      for (int j = 0; j < number; j++) {
        if (i != j) {
          // if metaphone of first name & last name matches then proceed further
          if (metaFirstName.equals(metaphone.metaphone(first_name.get(j))) &&
              metaLastName.equals(metaphone.metaphone(last_name.get(i)))) {

            // check for the metaphone of company is equal or email is same
            if (metaCompany.equals(metaphone.metaphone(company.get(j))) ||
                metaEmail.equals(metaphone.metaphone(email.get(j)))
            ) {
              //can also add more conditions to filter data but current conditions are sufficient
              // for the data given right now
              if (!duplicateRecords.contains(i)) {
                duplicateRecords.add(i);
              }
            }
          }
        }
      }
    }

    for (int k = 0; k < duplicateRecords.size(); k++) {
      int index = duplicateRecords.get(k);
      duplicateSet.add(allData.get(index));
      uniqueData.remove(allData.get(index));
    }
  }

  @Override
  public List<String> getDuplicateData() {
    return duplicateSet;
  }

  @Override
  public List<String> getUniqueData() {
    return uniqueData;
  }

  public static void main(String[] args) {

    DeDuplicateData d = new DeDuplicate();
    d.setFile("src/main/docs/advanced.csv");
    d.deDuplicateData();
    List<String> duplicatedData = d.getDuplicateData();
    List<String> uniqueData = d.getUniqueData();

    System.out.println("Duplicate Set");
    for (int j = 0; j < duplicatedData.size(); j++) {
      System.out.println(duplicatedData.get(j));
    }
    System.out.println("\n");
    System.out.println("Unique Set");
    for (int i = 0; i < uniqueData.size(); i++) {
      System.out.println(uniqueData.get(i));
    }


  }
}
