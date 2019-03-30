package com.example.detectduplicates;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class DataService {
  @GetMapping("/api/normal/duplicate")
  public List<String> getDuplicateDataForNormal(){
    DeDuplicateData d = new DeDuplicate();
    d.setFile("src/main/docs/normal.csv");
    d.deDuplicateData();
    return d.getDuplicateData();
  }

  @GetMapping("/api/normal/unique")
  public List<String> getUniqueDataForNormal(){
    DeDuplicateData d = new DeDuplicate();
    d.setFile("src/main/docs/normal.csv");
    d.deDuplicateData();
    return d.getUniqueData();
  }

  @GetMapping("/api/advanced/duplicate")
  public List<String> getDuplicateDataForAdvanced(){
    DeDuplicateData d = new DeDuplicate();
    d.setFile("src/main/docs/advanced.csv");
    d.deDuplicateData();
    return d.getDuplicateData();
  }

  @GetMapping("/api/advanced/unique")
  public List<String> getUniqueDataForAdvanced(){
    DeDuplicateData d = new DeDuplicate();
    d.setFile("src/main/docs/advanced.csv");
    d.deDuplicateData();
    return d.getUniqueData();
  }



}
