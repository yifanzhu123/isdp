<template>
  <div>
      <el-row type="flex" class="row-bg" justify="center">
      <el-col :xl="6" :lg="7">
        <img src="@/assets/logo.png">
        <h2>ISDP后台管理系统</h2>
      </el-col>
      <el-col :xl="6" :lg="7">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" auto-complete="on" label-position="left" >
          <div>
            <h3>欢迎登录</h3>
          </div>
          <el-form-item prop="username">
            <el-input ref="username" v-model="loginForm.username" placeholder="Username" name="username" type="text" tabindex="1" auto-complete="on"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType" placeholder="Password" name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
          </el-form-item>
          <el-button :loading="loading" type="success" style="width: 100%; margin-bottom: 30px" @click.native.prevent="handleLogin" >登录</el-button>
        </el-form>
      </el-col>
    </el-row>
    
  </div>
</template>

<script>


export default {
  
  name: "Login",
 
  data() {
    return {
      loginForm: {
        username: "admin",
        password: "123456",
      },
      loginRules: {
        username: [{ required: true, trigger: "blur" }],
        password: [{ required: true, trigger: "blur" }],
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
    };
  },
   watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  methods:{
      showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin(){
        this.$refs.loginForm.validate((valid)=>{
            if(valid){
                this.loading=true;
                this.$store.dispatch("login",this.loginForm).then(()=>{
                    this.$router.push({path:this.redirect || "/"});
                    this,this.loading=false;
                })
                .catch(()=>{
                    this.loading=false;
                })
            }else{
                console.log("error submit!!");
                return false
            }
        })
    }
  }
}
</script>

<style>
.row-bg {
  background-color: #fafafa;
  height: 750px;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
}
</style>