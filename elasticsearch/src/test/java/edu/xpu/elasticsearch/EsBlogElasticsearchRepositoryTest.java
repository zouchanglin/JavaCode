package edu.xpu.elasticsearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogElasticsearchRepositoryTest {
    @Autowired
    private EsBlogElasticsearchRepository esBlogElasticsearchRepository;

    @Before
    public void init() {
        //清除所有数据
        esBlogElasticsearchRepository.deleteAll();
        esBlogElasticsearchRepository.save(new EsBlog("111", "你好Java或者C++", "是的呢"));
        esBlogElasticsearchRepository.save(new EsBlog("122", "深入裂解Java虚拟机", "嗯呢文字"));
        esBlogElasticsearchRepository.save(new EsBlog("123", "嗯嗯呢", "中文字"));
    }

    @Test
    public void findDistinctByTittleContainingOrSummaryContaining(){
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<EsBlog> res = esBlogElasticsearchRepository.findDistinctByTittleContainingOrSummaryContaining("嗯呢", "文", pageRequest);
        List<EsBlog> content = res.getContent();
        System.out.println(content);
        assertEquals(2, content.size());
    }
}