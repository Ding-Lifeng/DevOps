package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ListMapper {

    User findUserById(int uid);

    List<TodoList> findListByUid(int uid);

    void insert_todoList(TodoList todolist);

    void update_todoList(TodoList todoList);

    void delete_todoList(TodoList todolist);

    TodoList findListById(int id);
}
