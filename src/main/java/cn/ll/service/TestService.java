package cn.ll.service;

import cn.ll.domain.Test;
import cn.ll.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    //  @Autowired
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }

}