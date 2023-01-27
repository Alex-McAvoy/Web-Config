package com.service.impl;

import com.bean.Worker;
import com.mapper.WorkerMapper;
import com.service.WorkerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alex McAvoy
 * @since 2022-10-25
 */
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {

}
