package edu.xpu.elasticsearch;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author x
 */
@Data
@Builder
@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {

    private static final long serialVersionUID = 3014506637185745628L;
    @Id
    private String id;
    private String tittle;
    private String summary;

    public EsBlog(String id, String tittle, String summary) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
    }

    public EsBlog() {
    }
}
