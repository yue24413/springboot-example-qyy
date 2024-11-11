## redis

### lua脚本
1.声明限流函数：local function expireAPI_58(keys,args)；keys 是一个Lua表（数组），用于接收键名。args 也是一个Lua表（数组），用于接收额外的参数。 <br/>

