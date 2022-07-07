package com.cui.user.service;

import com.cui.common.utils.BaseResult;

import java.util.List;

public interface CertificatesFilesService {

	
	
	/**
	 * 证件文件上传
	 */
	
	public BaseResult saveFiles(String file) ;


	/**
	 * 证件图片批量上传
	 */
	public BaseResult batchSaveFiles(List<String> files) ;
	
	/**
	 * 获取图片
	 */
	public BaseResult getFileById(String id) ;
	
	/**
	 * 证件图片上传2.0
	 * @param file
	 * @return
	 */
	public BaseResult savePicTures(String file) ;
	
	/**
	 * 获取图片2.0
	 */
	public BaseResult getPicTureById(String id) ;
	
	/**
	 * 同步图片数据
	 * @return
	 */
//	public BaseResult getList(Integer page, Integer rows);
}
