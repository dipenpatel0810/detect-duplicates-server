package com.example.detectduplicates;

import java.util.List;

public interface DeDuplicateData {
  public void setFile(String fileLocation);

  public void deDuplicateData();

  public List<String> getDuplicateData();

  public List<String> getUniqueData();
}
