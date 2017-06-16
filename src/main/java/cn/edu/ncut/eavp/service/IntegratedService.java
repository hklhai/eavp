package cn.edu.ncut.eavp.service;

import cn.edu.ncut.eavp.model.assistant.IntegeratedQueryObj;
import cn.edu.ncut.eavp.webservice.base.DatagridResult;

public interface IntegratedService {
  public abstract DatagridResult getGridData(int firstResult,int maxResult,String orderby,String []fields, IntegeratedQueryObj model,String orgids);
}
