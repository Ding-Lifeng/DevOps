package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ListService {
    @Autowired
    private ListMapper ListMapper;

    @Transactional(readOnly = true)
    public List<TodoList> getListInfoByUser(String token) throws Exception {
        // 解析Token获取用户uid
        int uid = Integer.parseInt(JwtUtil.getUidFromToken(token));
        // 根据uid获取用户信息
        User user = ListMapper.findUserById(uid);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        return ListMapper.findListByUid(uid);
    }

    public void add_todoList(TodoList todolist){
        ListMapper.insert_todoList(todolist);
    }

    public void update_todoList(TodoList todolist){
        ListMapper.update_todoList(todolist);
    }

    public void delete_todoList(TodoList todolist){
        ListMapper.delete_todoList(todolist);
    }

    public TodoList getListInfoById(int id){
        return ListMapper.findListById(id);
    }
}