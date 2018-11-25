package cn.ce.feignservice.dao;

import cn.ce.feignservice.entity.Application;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @auther ggs
 * @date 2018-08-13 11:04:14.738
 */
public interface ApplicationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Integer id);

    Application selectByPrimaryCode(String appCode);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    List<Application> selectByCreatorCode(@Param(value = "creatorCode") String creatorCode, @Param(value = "searchText") String searchText);

    List<Application> selectAll();

    List<Application> getApplicationByCondition(@Param(value = "applicationCode") String applicationCode);
}