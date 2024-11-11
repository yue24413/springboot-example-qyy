-- 声明函数：expieAPI_{序号},限流函数;某一列keys，另一列args，参数放在了列里
local function expireAPI_58(keys,args)
    local key = keys[1] -- 索引从1开始
    local expire = args[1] --过期时间
    local count = args[2]  --允许时间内调用次数

    --redis集成的lua脚本，调用redis提供的函数,用逗号区分(代替redis命令的空格)
    if redis.call('exists',key) == 0 then
        redis.call('setex',key,expire,1)
        return true
    end

    if redis.call('get',key) >= count then
       return false
    end

    redis.call('incr',key)
    return true
end
-- 以键值对的形式注册到redis的服务器上，函数名称不可重复，用编号区分
redis.register_function('expireAPI_58', expireAPI_58)


local function buy_58(keys,args)
    local key = keys[1]
    if redis.call('hget',key,'total') == '0' then
        return -1
    end

    return redis.call('hincrby',key,'total',-1)
--  return redis.call('hget',key,'total') 返回的是字符串，与约定好的返回类型统一
end
redis.register_function('buy_58', buy_58)
