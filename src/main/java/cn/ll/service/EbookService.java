package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.domain.EbookExample;
import cn.ll.mapper.EbookMapper;
import cn.ll.req.EbookReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;



    public List<Ebook> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return ebookMapper.selectByExample(ebookExample);
    }


}
