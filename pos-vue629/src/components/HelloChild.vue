<template>
  <div class="hello">
    <h1>子组件2</h1>
  <h5>(父子组件通信响应式修改)</h5>
  <input :value="value" @input="handlerChange" />
    <h4>收到父组件传递的数据： {{ value }}</h4>
     <!-- Vuex演示 -->
     <h3>Vuex中state.username的FullName值是: {{ this.$store.getters.fullName }}</h3>
     <input v-model="newName">
    <button @click="updateByAction">Action修改</button>
  </div>
</template>

<script>
export default {
    name:'HelloChild',
    props:["value"],
    data(){
        return{
            newName:''
        }
    },
    methods:{
        handlerChange(e){
            this.$emit("input",e.target.value)
        },
        updateByAction(){
            this.$store.dispatch('updateUserName',this.newName)
        }
    }
}
</script>

<style scoped>
    .hello {
    background-color:beige;
  }
</style>