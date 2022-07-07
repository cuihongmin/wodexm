package com.cui.user.dao;


import com.cui.user.entity.CertificatesFiles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CertificatesFilesMapper {
	
	
    int insert(CertificatesFiles record);
    
    
    
    int insertList(List<CertificatesFiles> records);
    
    String getFileById(String id);
    
    String getPicTureById(String id);
    
    //同步图片数据
    List<CertificatesFiles> getList(@Param("page")Integer page, @Param("rows")Integer rows);
    int updateModularById(@Param("id")String id, @Param("modular")String modular);

}