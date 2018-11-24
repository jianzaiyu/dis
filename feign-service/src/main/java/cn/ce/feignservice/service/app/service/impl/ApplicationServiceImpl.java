package cn.ce.feignservice.service.app.service.impl;

import cn.ce.feignservice.service.app.dao.ApplicationDao;
import cn.ce.feignservice.service.app.entity.Application;
import cn.ce.feignservice.service.app.service.ApplicationService;
import cn.ce.servicesupport.pojo.Page;
import cn.ce.servicesupport.support.FileSupport;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-09 15:21
 **/
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        Application application = applicationDao.selectByPrimaryKey(id);
        if (application != null && application.getCheckState() == 1) {
            return -1;
        }
        return applicationDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Application record) {
        return applicationDao.insert(record);
    }

    @Override
    public int insertSelective(Application record) {
        return applicationDao.insertSelective(record);
    }

    @Override
    public Application selectByPrimaryKey(Integer id) {
        return applicationDao.selectByPrimaryKey(id);
    }

    @Override
//    @Cacheable(cacheNames = "ggs")
    public List<Application> selectAll() {
        return applicationDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Application record) {
        return applicationDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Application record) {
        return applicationDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public Page<Application> selectPageAll(Page page) {
        PageHelper.startPage(page);
        page.fill(applicationDao.selectAll());
        return page;
    }

    @Override
    public List<Application> selectByCreatorCode(String creatorCode) {
        return applicationDao.selectByCreatorCode(creatorCode, "");
    }

    @Override
    public void insertSelectiveAndUploadIcon(Application application, MultipartFile appIcon) throws IOException {
        application.setIcon(FileSupport.build().validateIMG(appIcon).uploadFile(appIcon));
        applicationDao.insertSelective(application);
    }

    @Override
    public Page selectPageByCreatorCode(String creatorCode, String searchText, Page page) {
        PageHelper.startPage(page);
        page.fill(applicationDao.selectByCreatorCode(creatorCode, searchText));
        return page;
    }

    @Override
    public List<Application> getApplicationByCode(String applicationCode){
        return applicationDao.getApplicationByCondition(applicationCode);
    }

}
