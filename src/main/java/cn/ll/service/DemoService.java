package cn.ll.service;

import cn.ll.domain.Demo;
import cn.ll.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {


    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }

}
