<template>
    <div>
        <el-dropdown>
        <i class="el-icon-user" style="margin-right: 15px"></i>
         <span>{{this.$store.state.username}}</span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>用户中心</el-dropdown-item>
      <el-dropdown-item>退出</el-dropdown-item>
        </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>