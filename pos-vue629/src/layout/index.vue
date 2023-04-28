<template>
  <div>
    <el-container>
        <!-- SideBar组件 -->
        <el-aside width="210px">
          <side-bar></side-bar>
        </el-aside>
        <el-container>
          <el-header>
            <!-- HeaderBar组件 -->
            <header-bar></header-bar>
          </el-header>
          <el-main>
            <!--<app-main></app-main>-->
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
  </div>
</template>

<script>
import SideBar from './components/SideBar.vue'
import HeaderBar from './components/HeaderBar.vue'
import AppMain from './components/AppMain.vue'
export default {
  name: 'Layout',
  components: {
    SideBar,
    HeaderBar,
    
  }
};
</script>

<style scoped lang="scss">
  .el-header {
    text-align: right; 
    font-size: 12px;
    background-color: #d9e4e1;
    color: #fff;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
    text-align: left; 
    background-color: #fff;
  }

</style>