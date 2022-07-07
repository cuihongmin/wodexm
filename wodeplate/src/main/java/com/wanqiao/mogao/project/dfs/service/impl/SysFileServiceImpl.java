package com.wanqiao.mogao.project.dfs.service.impl;

import com.wanqiao.mogao.common.core.text.Convert;
import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.project.dfs.config.DfsConfig;
import com.wanqiao.mogao.project.dfs.domain.SysFile;
import com.wanqiao.mogao.project.dfs.mapper.SysFileMapper;
import com.wanqiao.mogao.project.dfs.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件上传Service业务层处理
 * 
 * @author zhangguangbin
 * @date 2020-05-27
 */
@Slf4j
@Service
public class SysFileServiceImpl implements ISysFileService
{
    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private DfsConfig dfsConfig;

    /**
     * 查询文件上传
     * 
     * @param id 文件上传ID
     * @return 文件上传
     */
    @Override
    public SysFile selectSysFileById(String id)
    {
        return sysFileMapper.selectSysFileById(id);
    }

    /**
     * 查询文件上传列表
     * 
     * @param sysFile 文件上传
     * @return 文件上传
     */
    @Override
    public List<SysFile> selectSysFileList(SysFile sysFile)
    {
        return sysFileMapper.selectSysFileList(sysFile);
    }

    /**
     * 新增文件上传
     * 
     * @param sysFile 文件上传
     * @return 结果
     */
    @Override
    public int insertSysFile(SysFile sysFile)
    {
        sysFile.setCreateTime(DateUtils.getNowDate());
        return sysFileMapper.insertSysFile(sysFile);
    }

    /**
     * 修改文件上传
     * 
     * @param sysFile 文件上传
     * @return 结果
     */
    @Override
    public int updateSysFile(SysFile sysFile)
    {
        sysFile.setUpdateTime(DateUtils.getNowDate());
        return sysFileMapper.updateSysFile(sysFile);
    }

    /**
     * 删除文件上传对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFileByIds(String ids)
    {
        return sysFileMapper.deleteSysFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件上传信息
     * 
     * @param id 文件上传ID
     * @return 结果
     */
    @Override
    public int deleteSysFileById(String id)
    {
        return sysFileMapper.deleteSysFileById(id);
    }

    @Override
    public List<SysFile> listFilesByBussinessId(String businessId) {
        return sysFileMapper.listFilesByBussinessId(businessId);
    }

    @Override
    public int deleteSysFileByBussinessId(String businessId) {
        return sysFileMapper.deleteSysFileByBussinessId(businessId);
    }

    @Override
    public void updateSysFileDelByBusinessId(SysFile file) {
        file.setUpdateTime(DateUtils.getNowDate());
        sysFileMapper.updateSysFileDelByBusinessId(file);
    }

}
