<template>
  <div class="about">
    <h1>父组件</h1>
    <input v-model="info">
    <h4>收到子组件1传递数据为: {{childMsg}}</h4>
    <input v-model="value"><br>
    <h4>收到子组件2修改的数据为: {{value}}</h4>
    <el-row>
      <el-col :span="12">
        <hello-son :msg='info' @out="receiveMsgFromChild"></hello-son>
      </el-col>
      <el-col :span="12">
        <hello-child v-model="value"></hello-child>
      </el-col>
    </el-row>
  </div> 
</template>

<script>

import HelloSon from "@/components/HelloSon.vue";
import HelloChild from "@/components/HelloChild.vue"
export default {
  name: 'AboutView',
  components:{
    HelloSon,
    HelloChild,
  },
  data() {
    return {
      info:"TO子组件1：在做什么？",
      childMsg:'',
      value:'TO子组件2：天气很好'
    };
  },

  created() {
    
  },

  methods: {
   receiveMsgFromChild(value){
    this.childMsg=value
   }
  }
}
</script>
<style scoped lang="scss">
  .about {
    background-color:antiquewhite;
  }
</style>
