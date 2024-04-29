<template>
  <div class="edit-list">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>编辑TodoList</span>
      </div>
      <el-form :model="listForm" label-width="100px">
        <el-form-item label="事项名称">
          <el-input v-model="listForm.staff_name"></el-input>
        </el-form-item>
        <el-form-item label="完成状态">
<!--      通过switchValue映射status-->
          <el-switch v-model="switchValue" @change="handleSwitchChange"></el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveList">保存</el-button>
          <el-button @click="cancel">取消</el-button>
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
        id: null,
        staff_name: '',
        status: ''
      },
      switchValue: false
    };
  },
  created() {
    this.initializeList();
  },
  methods: {
    initializeList() {
      const list_id = this.$route.query.id;
      if (list_id) {
        this.fetchTask(list_id);
      }
    },
    fetchTask(list_id) {
      const token = localStorage.getItem('token');
      axios.get(`/api/todoList/${list_id}`, {
        headers: {
          'Authorization': token
        }
      })
          .then(response => {
            this.listForm = response.data;
            this.switchValue = this.listForm.status === '完成';
          })
          .catch(error => {
            this.$message.error('加载事项信息失败');
          });
    },
    saveList() {
      const token = localStorage.getItem('token');
      const list_id = this.$route.query.id;

      axios.post(`/api/todoList/updateList`, this.listForm, {
        headers: {
          'Authorization': token
        }
      })
          .then(response => {
            this.$message.success('事务信息已保存');
          })
          .catch(error => {
            this.$message.error('保存事务信息失败');
          });
      this.$router.go(-1);
    },
    cancel() {
      this.$router.go(-1); // 返回上一页
    },
    handleSwitchChange(newValue) {
      this.listForm.status = newValue ? '完成' : '未完成';
    }
  }
};
</script>

<style scoped>
.edit-list {
  margin: 20px;
}
.box-card {
  width: 400px;
}
</style>