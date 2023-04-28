<template>
 <div class="demo-image">
  <div class="block" v-for="(item, index) in productInformation" :key="index">
    <el-image
      :src="item.src"
      ></el-image><br>
      <span>{{ item.productName }}</span><br>
      <span>{{ item.price }}</span>
  </div>
</div>
</template>

<script>
export default {
  props:[
    "productInformation",
  ],
data() {
      return {
        
      }
    }
}
</script>

<style>
.block{
  width: 150px;
  float: left;
  padding: 20px;
}
</style>