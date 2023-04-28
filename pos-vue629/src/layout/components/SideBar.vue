<template>
    <div>
      <el-menu default-active="$route.path" router unique-opened :collapse="isCollapse" class="el-menu-vertical-demo">
        <div class="logo">
           <img src="@/assets/logo.png" style="height:30px"><span>ISDP后台管理</span>
        </div>
        <div v-for="(item,i) in lastMenu" :key="i">
        <el-submenu :index="item.prouter" v-if="item.children">
          <template slot="title"><i :class="item.icon"> </i><span>{{item.name}}</span></template>
          <el-menu-item :index="val.crouter" v-for="val in item.children" :key="val.id"><span>{{val.name}}</span></el-menu-item>
        </el-submenu>
        <el-menu-item :index="item.prouter" v-else> <i :class="item.icon"></i> <span slot="title">{{item.name}}</span>></el-menu-item>
        </div>
      </el-menu>
      
    </div>
</template>

<script>
export default {
  name: "SideMenu",
  data() {
    return {
      defaultActive:'/home',
      isCollapse:false,
      lastMenu:[],
      pMenu:[],
      cMenu:[],
      meunList: [
        { id: '1', pid: '0', name: '首页', path: '/home', icon: 'el-icon-s-home' },
        { id: '2', pid: '0', name: '系统管理', path: '/system', icon: 'el-icon-setting' },
        { id: '3', pid: '2', name: '用户', path: '/system/user', icon: 'el-icon-user' },
        { id: '4', pid: '2', name: '角色', path: '/system/role', icon: 'el-icon-key' },
        { id: '5', pid: '0', name: '产品管理', path: '/product', icon: 'el-icon-menu' },
        { id: '6', pid: '5', name: '类别', path: '/product/category', icon: 'el-icon-files'},
        { id: '7', pid: '5', name: '产品', path: '/product/item', icon: 'el-icon-coin' },
        { id: '8', pid: '0', name: '销售管理', path: '/sale', icon: 'el-icon-s-goods' },
        { id: '9', pid: '8', name: '订单', path: '/sale/order', icon: 'el-icon-s-order' },
        { id: '10', pid: '8', name: '支付', path: '/sale/payment', icon: 'el-icon-s-ticket' },
        { id: '11', pid: '0', name: '关于', path: '/about', icon: 'el-icon-info' },
      ]
    }
  },
  mounted(){
    this.handleListToTree()
  },
  computed:  {},
	methods: {
   handleListToTree(){
     var pMenu=[]//父级菜单
     var cMenu=[]//子菜单
     if(this.meunList.length>0){
       //有数据则进行下一步
       //遍历菜单列表，如果没有pid=0，则放进pMeun,有则放进cMeun
       this.meunList.forEach((item)=>{
         if(item.pid==0){
           pMenu.push({
             id:item.id,
             name:item.name,
             icon:item.icon,
             prouter:item.path,
             children:[]
           })
         }else{
           cMenu.push(item)
         }
       })
       //判断pMenu和cMenu中id和pid是否相等，相等则将该项加入对应pMeun的children中
       pMenu.forEach((pitem)=>{
         cMenu.forEach((citem)=>{
           if(citem.pid==pitem.id){
             pitem.children.push({
               id:citem.id,
               name:citem.name,
               crouter:citem.path
             })
           }
         })
       })
       //如果pMenu中的children没有数据，则删除该children属性
       pMenu.forEach((item,index)=>{
         if(!item.children.length){
           delete pMenu[index].children
         }
       })
       this.lastMenu=pMenu//赋值给菜单数据源
     }else{
       return
     }
   },
   handleOpen(){},
   handleClose(){},
	}
}
</script>

<style scoped lang="scss">
  .logo {
    text-align: center;
    background-color: #fff;
    color: #079465;
    font-weight: bold;
    line-height: 60px;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 199px;
  min-height: calc(100vh - 30px);
  min-width: 100px;
}
</style>