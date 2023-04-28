const Mock = require('mockjs')
const data = Mock.mock({
  'list|10': [
    {
      'categoryId|+1': 1, 
      categoryName: '@ctitle',
      createTime: Mock.Random.date()
    },
  ],
});

module.exports = [{
  url: '/category/listAll',
  type: 'get',
  response: _ => {
    return {
      code: 200,
      msg: '操作成功!',
      data: data.list
    }
  }
}]