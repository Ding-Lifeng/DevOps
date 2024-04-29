<template>
  <div class="user-info">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>我的事项列表</span>
        <el-button type="primary" @click="add_todolist" style="float: right;">添加待办事项</el-button>
      </div>
      <el-table :data="ListInfo" style="width: 100%">
        <el-table-column prop="staff_name" label="事项名称" width="260"></el-table-column>
        <el-table-column prop="datetime" label="事项开始时间" width="260"></el-table-column>
        <el-table-column prop="status" label="完成状态" width="200">
          <template v-slot="scope">
            <el-tag :type="scope.row.status === '未完成' ? 'info' : 'success'">
              {{ scope.row.status}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template v-slot="scope">
            <el-button size="mini" @click="edit_todolist(scope.row)">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      ListInfo: []
    };
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      const token = localStorage.getItem('token');
      axios.get('/api/todoList/info', {
        headers: {
          'Authorization': token
        }
      })
          .then(response => {
            this.ListInfo = response.data;
          })
          .catch(error => {
            this.$message.error('获取待办事项信息失败');
          });
    },
    add_todolist() {
      this.$router.push('/add_todo');
    },
    edit_todolist(ListInfo) {
      this.$router.push({
        name: 'edit_todo',
        query: { id: ListInfo.id }
      });
    }
  }
};
</script>

