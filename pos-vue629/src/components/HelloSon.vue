<template>
  <div class="hello">
      <h1>子组件1</h1>
    <h5>(父子组件单独通信)</h5>
    <h4>收到父组件传递的数据： {{ msg }}</h4>
    <input v-model="childMsg" />
    <button @click="sendToFather">向父组件传值</button>
    <!-- Vuex演示 -->
     <h3>state中username是: {{this.$store.state.username}}</h3>
    <input v-model="newName">
    <button @click="updateByMutation">Mutation修改</button>
  </div>
</template>

<script>
export default {
    name:'HelloSon',
    data(){
        return{
            childMsg:'TO父组件：我在玩游戏',
            newName:''
        }
    },
    props:{
        msg:String,
    },
    methods:{
        sendToFather(){
            this.$emit('out',this.childMsg)
        },
        updateByMutation(){
            this.$store.commit("SET_NAME",this.newName)
        }
    }
}
</script>

<style scoped>
.hello {
    background-color:aquamarine;
  }
</style>