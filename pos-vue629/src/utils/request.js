import axios from "axios";

// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  timeout: 5000, // 设置请求超时为5秒
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 此处通常放置验证token代码
    return config;
  },
  (error) => {
    // 错误处理
    console.log(error); // 调试
    return Promise.reject(error);
  }
);
//响应拦截器
service.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    console.log("err" + error); 
    return Promise.reject(error);
  }
);

export default service;