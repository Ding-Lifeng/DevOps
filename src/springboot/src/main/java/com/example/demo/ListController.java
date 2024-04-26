package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoList")
public class ListController {

    @Autowired
    private ListService ListService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            List<TodoList> list = ListService.getListInfoByUser(token);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/insertList")
    public String addList(@RequestHeader("Authorization") String token, @RequestBody TodoList todolist) throws Exception {
        // 根据token获取当前用户的uid
        todolist.setUid(Integer.parseInt(JwtUtil.getUidFromToken(token)));
        ListService.add_todoList(todolist);
        return "待办事项添加成功";
    }

    @PostMapping("/updateList")
    public String updateList(@RequestBody TodoList todolist){
        ListService.update_todoList(todolist);
        return "事项修改成功";
    }

    @PostMapping("/deleteList")
    public String deleteList(@RequestBody TodoList todolist){
        ListService.delete_todoList(todolist);
        return "事项删除成功";
    }

    @GetMapping("/{list_id}")
    public ResponseEntity<?> getListById(@PathVariable int list_id, @RequestHeader("Authorization") String token) {
        try {
            int uid = Integer.parseInt(JwtUtil.getUidFromToken(token));  // 获取当前用户的uid
            TodoList list = ListService.getListInfoById(list_id);  // 获取指定id的todoList

            if (list != null && list.getUid() == uid){
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("访问被拒绝");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误: " + e.getMessage());
        }
    }
}
