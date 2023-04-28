import request from "@/utils/request";
// 分页查询类别列表
export function listCategoryByPage(query) {
  return request({
    url: '/category/page',
    method: 'get',
    params: query
  })
}
// 删除类别
export function delCategory(categoryId) {
  return request({
    url: '/category/' + categoryId,
    method: 'delete'
  })
}
// 查询类别详细
export function getCategory(categoryId) {
  return request({
    url: '/category/' + categoryId,
    method: 'get'
  })
}

// 新增类别
export function addCategory(data) {
  return request({
    url: '/category',
    method: 'post',
    data: data
  })
}

// 修改类别
export function updateCategory(data) {
  return request({
    url: '/category',
    method: 'put',
    data: data
  })
}
export function listAll() {
  return request({
    url: "/category/listAll",
    method: "get",
  });
}