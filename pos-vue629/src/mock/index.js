const Mock = require('mockjs');
const category = require('./category')

const mocks = [
  ...category,
]

for (const i of mocks) {
  Mock.mock(new RegExp(i.url), i.type || 'get', i.response);
}