<template>
  <el-form ref="userForm" :model="userForm" label-width="100px">
    <el-form-item label="用户名">
      <el-input v-model="userForm.username" style="width:220px;"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="userForm.password" style="width:220px;"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userForm: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    submitForm() {
      axios.post('/api/todoList/add', this.userForm)
          .then(response => {
            this.$message.success('用户添加成功');
          })
          .catch(error => {
            this.$message.error('用户添加失败');
          });
    },
    resetForm() {
      this.$refs.userForm.resetFields();
    },
  },
};
</script>