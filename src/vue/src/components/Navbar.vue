<template>
  <div class="navbar">
    <router-link to="/">登录界面</router-link>
    <router-link to="/add">添加用户</router-link>
    <router-link to="/about">用户页面</router-link>
  </div>
</template>

<script>
</script>

<style scoped>
/* 后续会添加样式 */
</style>


<style scoped>
.navbar {
  display: flex;
  flex-direction: column; /* 垂直排列链接 */
  justify-content: start;
  height: 100vh; /* 高度填满视窗 */
  padding: 20px; /* 内边距 */
  box-sizing: border-box; /* 包括内边距在内的宽度计算 */
  /* 更多样式 */
}
</style>