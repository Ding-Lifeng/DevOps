<template>
  <div class="add-todo">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>添加待办事项</span>
      </div>
      <el-form :model="listForm" @submit.native.prevent="add_todo" label-width="100px">
        <el-form-item label="事项名称">
          <el-input v-model="listForm.staff_name" placeholder="请输入事项名称"></el-input>
        </el-form-item>
        <el-form-item label="开始日期">
          <el-date-picker v-model="listForm.datetime" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add_todo">添加任务</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      listForm: {
        staff_name: '',
        uid: null,
        datetime: new Date().toISOString().split('T')[0],
        status: '未完成'
      }
    };
  },
  methods: {
    add_todo() {
      const token = localStorage.getItem('token');
      axios.post('/api/todoList/insertList', this.listForm, {
        headers: {
          'Authorization': token
        }
      })
          .then(() => {
            this.$message.success('任务已添加');
            this.listForm.name = '';
            this.$router.push('/todolist');
          })
          .catch(error => {
            this.$message.error('添加任务失败');
            console.error('Error:', error);
          });
    }
  }
};
</script>

<style scoped>
.add-todo {
  margin: 20px;
}
.box-card {
  width: 500px;
}
</style>