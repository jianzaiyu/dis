package cn.ce.feignservice.controller;

import cn.ce.feignservice.entity.Application;
import cn.ce.feignservice.service.ApplicationService;
import cn.ce.servicesupport.exception.BusinessException;
import cn.ce.servicesupport.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-09 15:18
 **/
@RestController
@RequestMapping("app")
@Validated
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PutMapping
    public void updateByPrimaryKeySelective(@RequestBody Application application) {
        applicationService.updateByPrimaryKeySelective(application);
    }

    @PostMapping
    public void insertSelective(@RequestBody @Valid Application application) {
        applicationService.insertSelective(application);
    }

    @DeleteMapping("{id}")
    public void deleteByPrimaryKey(@PathVariable int id) {
        applicationService.deleteByPrimaryKey(id);
    }

    @GetMapping("{id}")
    public Application selectByPrimaryKey(@PathVariable int id) {
        throw new BusinessException("你好我是来捣乱的");
//        return applicationService.selectByPrimaryKey(id);
    }

    @GetMapping("createCode/{creatorCode}")
    public Page selectByCreatorCode(@PathVariable String creatorCode, String searchText, Page page) {
        return applicationService.selectPageByCreatorCode(creatorCode, searchText, page);
    }

    @GetMapping("all")
    public List<Application> selectAll() {
        return applicationService.selectAll();
    }

}
