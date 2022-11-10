package com.service;

import com.bean.Product;
import com.dao.ProductDao;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public Product save(Product product) {
        return productDao.save(product);
    }

    public Product findById(Integer integer) {
        return productDao.findById(integer).get();
    }

    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    public void deleteById(Integer integer) {
        productDao.deleteById(integer);
    }

    public Iterable<Product> saveAll(Iterable<Product> iterable) {
        return productDao.saveAll(iterable);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    public Iterable<Product> findAll(Sort sort) {
        return productDao.findAll(sort);
    }

    public Iterable<Product> search(QueryBuilder queryBuilder) {
        return productDao.search(queryBuilder);
    }

    public Page<Product> search(QueryBuilder queryBuilder, Pageable pageable) {
        return productDao.search(queryBuilder, pageable);
    }
}
