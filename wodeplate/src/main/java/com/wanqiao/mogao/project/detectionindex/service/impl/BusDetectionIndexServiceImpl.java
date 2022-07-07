package com.wanqiao.mogao.project.detectionindex.service.impl;

import java.util.Date;
import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import com.wanqiao.mogao.project.detectionindex.dto.BusDetectionIndexDTO;
import com.wanqiao.mogao.project.equipmentindexrelat.domain.BusEquipmentIndexRelat;
import com.wanqiao.mogao.project.equipmentindexrelat.mapper.BusEquipmentIndexRelatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.detectionindex.mapper.BusDetectionIndexMapper;
import com.wanqiao.mogao.project.detectionindex.domain.BusDetectionIndex;
import com.wanqiao.mogao.project.detectionindex.service.IBusDetectionIndexService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 监测指标Service业务层处理
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Service
public class BusDetectionIndexServiceImpl implements IBusDetectionIndexService 
{
    @Autowired
    private BusDetectionIndexMapper busDetectionIndexMapper;
    @Autowired
    private BusEquipmentIndexRelatMapper busEquipmentIndexRelatMapper;

    /**
     * 查询监测指标
     * 
     * @param id 监测指标ID
     * @return 监测指标
     */
    @Override
    public BusDetectionIndex selectBusDetectionIndexById(Long id)
    {
        return busDetectionIndexMapper.selectBusDetectionIndexById(id);
    }

    /**
     * 查询监测指标列表
     * 
     * @param busDetectionIndex 监测指标
     * @return 监测指标
     */
    @Override
    public List<BusDetectionIndex> selectBusDetectionIndexList(BusDetectionIndex busDetectionIndex)
    {
        return busDetectionIndexMapper.selectBusDetectionIndexList(busDetectionIndex);
    }

    /**
     * 新增监测指标
     * 
     * @param busDetectionIndex 监测指标
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusDetectionIndex(BusDetectionIndexDTO busDetectionIndexDTO)
    {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        Date nowDate = DateUtils.getNowDate();
        //1、创建 监控指标对象
        BusDetectionIndex busDetectionIndex = new BusDetectionIndex();
        Long indexId = WanqiaoPrimaryKeyUtils.getSerialNumberLong();
        busDetectionIndex.setId(indexId);
        busDetectionIndex.setCreateBy(userId.toString());
        busDetectionIndex.setCreateTime(nowDate);
        busDetectionIndex.setName(busDetectionIndexDTO.getDetectionIndexName());
        busDetectionIndex.setPriority(busDetectionIndexDTO.getPriority());
        busDetectionIndex.setRemark(busDetectionIndexDTO.getDetectionIndexRemark());
        busDetectionIndex.setDetectionIndexTypeId(busDetectionIndexDTO.getDetectionIndexTypeId());
        //入库
        busDetectionIndexMapper.insertBusDetectionIndex(busDetectionIndex);
        //模拟异常，事务测试
//        int i = 10/0;
        //2、创建 设备和指标关联关系对象
        BusEquipmentIndexRelat busEquipmentIndexRelat = new BusEquipmentIndexRelat();
        busEquipmentIndexRelat.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busEquipmentIndexRelat.setCreateBy(userId.toString());
        busEquipmentIndexRelat.setCreateTime(nowDate);
        busEquipmentIndexRelat.setSensorId(busDetectionIndexDTO.getSensorId().toString());
        busEquipmentIndexRelat.setIndexId(indexId);//监控指标id
        busEquipmentIndexRelat.setCode(busDetectionIndexDTO.getSensorCode());
        busEquipmentIndexRelat.setPriority(Integer.parseInt(busDetectionIndexDTO.getPriority().toString()));
        //入库
        busEquipmentIndexRelatMapper.insertBusEquipmentIndexRelat(busEquipmentIndexRelat);

        return Integer.parseInt("1");
    }

    /**
     * 修改监测指标
     * 
     * @param busDetectionIndex 监测指标
     * @return 结果
     */
    @Override
    public int updateBusDetectionIndex(BusDetectionIndexDTO busDetectionIndexDTO)
    {
        Date nowDate = DateUtils.getNowDate();
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        // TODO: 2021/5/12 更新
        /*//1、组装 监测指标对象
        BusDetectionIndex busDetectionIndex = new BusDetectionIndex();
        busDetectionIndex.setId(busDetectionIndexDTO.getDetectionIndexId());
        busDetectionIndex.setName(busDetectionIndexDTO.getDetectionIndexName());
        busDetectionIndex.setPriority(Long.parseLong(busDetectionIndexDTO.getDetectionIndexPriority().toString()));
        busDetectionIndex.setRemark(busDetectionIndexDTO.getDetectionIndexRemark());
        busDetectionIndex.setDetectionIndexTypeId(busDetectionIndexDTO.getDetectionIndexTypeId());
        busDetectionIndex.setModifyBy(userId);
        busDetectionIndex.setModifyTime(nowDate);
        //2、组装 检测指标和设备关系 对象
        BusEquipmentIndexRelat busEquipmentIndexRelat = new BusEquipmentIndexRelat();
        busEquipmentIndexRelat.setSensorId(busDetectionIndexDTO.getSensorId().toString());
        busEquipmentIndexRelat.setIndexId(busDetectionIndexDTO.getDetectionIndexId());//监控指标id
        busEquipmentIndexRelat.setCode(busDetectionIndexDTO.getSensorCode());
        busEquipmentIndexRelat.setUpdateBy(userId.toString());
        busEquipmentIndexRelat.setUpdateTime(nowDate);
        busEquipmentIndexRelat.setPriority(Integer.parseInt(busDetectionIndexDTO.getPriority().toString()));
        //3、*/

        return 0;
    }

    /**
     * 批量删除监测指标
     * 
     * @param ids 需要删除的监测指标ID
     * @return 结果
     */
    @Override
    public int deleteBusDetectionIndexByIds(Long[] ids)
    {
        return busDetectionIndexMapper.deleteBusDetectionIndexByIds(ids);
    }

    /**
     * 删除监测指标信息
     * 
     * @param id 监测指标ID
     * @return 结果
     */
    @Override
    public int deleteBusDetectionIndexById(Long id)
    {
        return busDetectionIndexMapper.deleteBusDetectionIndexById(id);
    }
}
