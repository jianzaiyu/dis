package cn.ce.feignservice.service;

import cn.ce.feignservice.entity.Application;
import cn.ce.servicesupport.pojo.Page;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-09 15:21
 **/
public interface ApplicationService {
    int deleteByPrimaryKey(Integer id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Integer id);

    List<Application> selectAll();

    int updateByPrimaryKey(Application record);

    int updateByPrimaryKeySelective(Application record);

    Page<Application> selectPageAll(Page page);

    List<Application> selectByCreatorCode(String creatorCode);

    void insertSelectiveAndUploadIcon(Application application, MultipartFile appIcon) throws IOException;

    Page selectPageByCreatorCode(String creatorCode, String searchText, Page page);
    List<Application> getApplicationByCode(String applicationCode);
}
