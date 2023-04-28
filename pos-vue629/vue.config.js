const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: process.env.NODE_ENV === 'development',
  devServer: {
    host: "localhost",
    port: 8080,
    open: true,
    // 设置代理解决跨域
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: 'http://localhost:8081',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
  },
})

module.exports = {
  chainWebpack: config => {
    config
      .plugin('html')
      .tap(args => {
        args[0].title= 'isdp后台管理系统'
        return args
      })
  }
}
