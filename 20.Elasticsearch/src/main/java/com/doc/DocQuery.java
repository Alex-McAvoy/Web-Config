package com.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @Description: 文档高级查询
 * @Author: Alex McAvoy
 * @Date: 2022/10/13 16:56
 * @Version: 1.0
 **/
public class DocQuery {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

//        allQuery(esClient); //查询索引中全部的数据
//        termQuery(esClient); //条件查询
//        pageQuery(esClient); //分页查询
//        sortQuery(esClient); //查询排序
//        filterQuery(esClient); //过滤字段查询
//        boolQuery(esClient); //组合查询
//        rangeQuery(esClient); //范围查询
//        fuzzyQuery(esClient); //模糊查询
//        highlightQuery(esClient); //高亮查询，查询结果高亮显示
//        aggregationQuery(esClient); //聚合查询
        groupQuery(esClient); //分组查询

        esClient.close();
    }

    /**
     * @Description: 查询索引中全部数据
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:26
     **/
    private static void allQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();

        //确定索引
        request.indices("user");
        //查询数据
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        //响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits(); //查询结果
        System.out.println(hits.getTotalHits()); //查询条数
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 条件查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:29
     **/
    private static void termQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 30)));

        //响应结果
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 分页查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:33
     **/
    public static void pageQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();

        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        // (当前页码-1)*每页显示数据条数
        builder.from(2);
        builder.size(2);
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println("第二页：");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 查询排序
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:34
     **/
    private static void sortQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        builder.sort("age", SortOrder.DESC);
        request.source(builder);

        //响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 过滤字段查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:36
     **/
    private static void filterQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());

        String[] excludes = {"age"}; //排除字段
        String[] includes = {}; //包含字段
        builder.fetchSource(includes, excludes);
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 组合查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:38
     **/
    private static void boolQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //必须满足
        //boolQueryBuilder.must(QueryBuilders.matchQuery("age", 30));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("sex", "男"));

        //必须不满足
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "男"));

        //可能满足
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 30));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 40));

        builder.query(boolQueryBuilder);
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 范围查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:43
     **/
    private static void rangeQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte(30);
        rangeQuery.lt(50);

        builder.query(rangeQuery);

        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 模糊查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:44
     **/
    private static void fuzzyQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "wangwu").fuzziness(Fuzziness.TWO));
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 高亮查询，查询结果高亮显示
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:46
     **/
    private static void highlightQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "zhangsan");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>"); //前缀标签
        highlightBuilder.postTags("</font>"); //后缀标签
        highlightBuilder.field("name"); //name属性

        builder.highlighter(highlightBuilder);
        builder.query(termsQueryBuilder);

        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 聚合查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:49
     **/
    private static void aggregationQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }
    }

    /**
     * @Description: 分组查询
     * @Param: [esClient]
     * @Return: void
     * @Author: Alex McAvoy
     * @Date: 2022/10/13 17:50
     **/
    private static void groupQuery(RestHighLevelClient esClient) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(aggregationBuilder);
        request.source(builder);

        // 响应信息
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        for ( SearchHit hit : hits ) {
            System.out.println(hit.getSourceAsString());
        }
    }
}
