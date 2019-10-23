package com.hxlk.centre.mapper.aft;

import com.hxlk.centre.entity.aft.SickbedInfo;
import com.hxlk.centre.entity.aft.SickbedInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SickbedInfoMapper {
    int countByExample(SickbedInfoExample example);

    int deleteByExample(SickbedInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SickbedInfo record);

    int insertSelective(SickbedInfo record);

    List<SickbedInfo> selectByExample(SickbedInfoExample example);

    SickbedInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SickbedInfo record, @Param("example") SickbedInfoExample example);

    int updateByExample(@Param("record") SickbedInfo record, @Param("example") SickbedInfoExample example);

    int updateByPrimaryKeySelective(SickbedInfo record);

    int updateByPrimaryKey(SickbedInfo record);
}