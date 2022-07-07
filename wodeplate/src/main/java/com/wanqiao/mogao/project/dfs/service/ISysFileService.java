package com.wanqiao.mogao.project.dfs.service;



import com.wanqiao.mogao.project.dfs.domain.SysFile;

import java.util.List;

/**
 * 文件上传Service接口
 * 
 * @author zhangguangbin
 * @date 2020-05-27
 */
public interface ISysFileService 
{
    /**
     * 查询文件上传
     * 
     * @param id 文件上传ID
     * @return 文件上传
     */
    public SysFile selectSysFileById(String id);

    /**
     * 查询文件上传列表
     * 
     * @param sysFile 文件上传
     * @return 文件上传集合
     */
    public List<SysFile> selectSysFileList(SysFile sysFile);

    /**
     * 新增文件上传
     * 
     * @param sysFile 文件上传
     * @return 结果
     */
    public int insertSysFile(SysFile sysFile);

    /**
     * 修改文件上传
     * 
     * @param sysFile 文件上传
     * @return 结果
     */
    public int updateSysFile(SysFile sysFile);

    /**
     * 批量删除文件上传
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileByIds(String ids);

    /**
     * 删除文件上传信息
     * 
     * @param id 文件上传ID
     * @return 结果
     */
    public int deleteSysFileById(String id);

    public List<SysFile> listFilesByBussinessId(String businessId);

    public int deleteSysFileByBussinessId(String businessId);

    public void updateSysFileDelByBusinessId(SysFile file);
}
