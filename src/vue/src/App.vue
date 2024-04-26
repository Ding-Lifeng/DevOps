<template>
  <div class="app-container">
    <Navbar />
    <div class="content">
      <router-view></router-view> <!-- 动态显示选中的页面内容 -->
    </div>
  </div>
</template>

<script>
import Navbar from './components/Navbar'

export default {
  components: {
    Navbar
  }
}
</script>

<style>
.app-container {
  display: flex;
  height: 100vh; /* 全屏高度 */
}

.navbar {
  flex: 0 0 150px; /* 导航栏宽度 */
  background-color: #f0f0f0; /* 背景颜色 */
  /* 更多样式 */
}

.content {
  flex: 1; /* 剩余空间填充内容区域 */
  padding: 20px; /* 内边距 */
  /* 更多样式 */
}
</style>