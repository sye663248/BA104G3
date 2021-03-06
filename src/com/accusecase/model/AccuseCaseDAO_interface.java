package com.accusecase.model;

import java.util.*;

public interface AccuseCaseDAO_interface {
          public void insert(AccuseCaseVO accuseCaseVO);
          public void update(AccuseCaseVO accuseCaseVO);
          public void delete(String accuse_No);
          public AccuseCaseVO findByPrimaryKey(String accuse_No);
          public List<AccuseCaseVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
