package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bean.Person;
import com.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security 设置用户名密码实现类
 *
 * @author Alex McAvoy
 * @version 1.0
 * @date 2022/10/31 14:39
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PersonMapper personMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Person person = personMapper.selectOne(wrapper);

        //判断用户是否存在
        if (person == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //密码解码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(person.getPassword());

        //获取权限与角色
        String authority = person.getAuthority();
        String role = person.getRole();
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority + "," + role);

        User user = new User(person.getUsername(), password, authorities);
        System.out.println(user);
        return user;
    }
}
