package com.dao;


import com.bean.Product;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Dao 接口，需要继承 ElasticsearchRepository
 *  泛型参数：
 *   1.Dao 接口对应的 Bean
 *   2.Dao 接口对应的 Bean 的主键
 * **/
@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Integer> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Integer integer);

    @Override
    Iterable<Product> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends Product> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    Iterable<Product> findAll(Sort sort);

    @Override
    Iterable<Product> search(QueryBuilder queryBuilder);

    @Override
    Page<Product> search(QueryBuilder queryBuilder, Pageable pageable);
}
