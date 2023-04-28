import request from "@/utils/request";
// 分页查询产品列表
export function listProductByPage(query) {
  return request({
    url: '/product/page',
    method: 'get',
    params: query
  })
}
// 删除产品
export function delProduct(productId) {
  return request({
    url: '/product/' + productId,
    method: 'delete'
  })
}
// 查询产品详细
export function getProduct(productId) {
  return request({
    url: '/product/' + productId,
    method: 'get'
  })
}

// 新增产品
export function addProduct(data) {
  return request({
    url: '/product',
    method: 'post',
    data: data
  })
}

// 修改产品
export function updateProduct(data) {
  return request({
    url: '/product',
    method: 'put',
    data: data
  })
}