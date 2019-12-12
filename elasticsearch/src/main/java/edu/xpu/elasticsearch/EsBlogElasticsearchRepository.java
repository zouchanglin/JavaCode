package edu.xpu.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author x
 */
public interface EsBlogElasticsearchRepository extends ElasticsearchRepository<EsBlog, String> {
    /**
     * 分页查询博客去重功能
     * @param tittle 标题
     * @param summary 摘要
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<EsBlog> findDistinctByTittleContainingOrSummaryContaining(String tittle, String summary, Pageable pageable);
}
