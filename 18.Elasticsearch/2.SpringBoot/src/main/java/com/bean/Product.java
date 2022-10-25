package com.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/** @Document 文档关联
 *   - indexName: 索引名称
 *   - shards: 主分片数量
 *   - replicas: 副本数量
 * **/
@Document(indexName = "product", shards = 3, replicas = 1)
public class Product {
    @Id
    private Integer id;
    /** @Field：字段映射
     *  - type: 映射类型
     *    - FieldType.Text: 文本类型
     *    - FieldType.Keyword: 关键字类型，不进行分词
     *  - analyzer: 分词器
     *  - index: 是否进行索引关联，即是否被查询，默认为 true
     * **/
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productName;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Keyword, index = false)
    private String imagesUrl;

    public Product() {
    }

    public Product(Integer id, String productName, String category, Double price, String imagesUrl) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.imagesUrl = imagesUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", imagesUrl='" + imagesUrl + '\'' +
                '}';
    }
}
