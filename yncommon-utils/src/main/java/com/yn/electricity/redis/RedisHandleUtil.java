package com.yn.electricity.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.CollectionUtils.*;

/**
 * @ClassName: RedisUtil
 * @Author: zzs
 * @Date: 2021/3/3 10:54
 * @Description: redis工具类
 */
@Slf4j
@Component
public class RedisHandleUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public  RedisResult expire(String key, long time) {
        RedisResult result = new RedisResult();

        if (time > 0) {
            return result;
        }

        try {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 根据 key 获取过期时间
     *
     * @param key 键(不能为 Null)
     * @return 时间(秒) 返回0代表永久有效
     */
    public  RedisResult getExpire(String key) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.getExpire(key, TimeUnit.SECONDS);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键(不能为 Null)
     * @return true 存在 false 不存在
     */
    public  RedisResult hashKey(String key) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.hasKey(key);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public  RedisResult del(String... key) {
        RedisResult result = new RedisResult();

        try {
            if(key != null && key.length > 0) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(arrayToList(key));
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    //==================================String====================================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public  RedisResult get(String key) {
        RedisResult result = new RedisResult();

        try {
            Object res =  key == null ? null : redisTemplate.opsForValue().get(key);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true 成功 false 失败
     */
    public  RedisResult set(String key, Object value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForValue().set(key, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time > 0 若 time <= 0 将设置无限期
     * @return true 成功 false 失败
     */
    public  RedisResult set(String key, Object value, long time) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public  RedisResult incr(String key, long delta) {
        RedisResult result = new RedisResult();

        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        try {
            redisTemplate.opsForValue().increment(key, delta);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public  RedisResult decr(String key, long delta) {
        RedisResult result = new RedisResult();

        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }

        try {
            redisTemplate.opsForValue().decrement(key, delta);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    public  RedisResult hGet(String key, String item) {
        RedisResult result = new RedisResult();

        try {
            Object res = redisTemplate.opsForHash().get(key, item);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public  RedisResult hmGet(String key) {
        RedisResult result = new RedisResult();

        try {
            Map<Object, Object> res = redisTemplate.opsForHash().entries(key);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public  RedisResult hmSet(String key, Map<Object, Object> map) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForHash().putAll(key, map);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public  RedisResult hmSet(String key, Map<Object, Object> map, long time) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public  RedisResult hSet(String key, String item, Object value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForHash().put(key, item, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public  RedisResult hSet(String key, String item, Object value, long time) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public  RedisResult hDel(String key, Object... item) {
        RedisResult result = new RedisResult();
        
        try {
            redisTemplate.opsForHash().delete(key, item);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public  RedisResult hHasKey(String key, String item) {
        RedisResult result = new RedisResult();
        
        try {
            redisTemplate.opsForHash().hasKey(key, item);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *  @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 
     */
    public  RedisResult hIncr(String key, String item, double by) {
        RedisResult result = new RedisResult();
        
        try {
            redisTemplate.opsForHash().increment(key, item, by);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public  RedisResult hDecr(String key, String item, double by) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForHash().increment(key, item, -by);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     */
    public  RedisResult sGet(String key) {
        RedisResult result = new RedisResult();
        try {
            Set<Object> res = redisTemplate.opsForSet().members(key);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public  RedisResult sHasKey(String key, Object value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForSet().isMember(key, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public  RedisResult sSet(String key, Object... values) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForSet().add(key, values);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public  RedisResult sSetAndTime(String key, long time, Object... values) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public  RedisResult sGetSetSize(String key) {
        RedisResult result = new RedisResult();

        try {
            Long res = redisTemplate.opsForSet().size(key);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */

    public  RedisResult setRemove(String key, Object... values) {
        RedisResult result = new RedisResult();

        try {
            Long res = redisTemplate.opsForSet().remove(key, values);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public  RedisResult lGet(String key, long start, long end) {
        RedisResult result = new RedisResult();

        try {
            List<Object> res = redisTemplate.opsForList().range(key, start, end);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public  RedisResult lGetListSize(String key) {
        RedisResult result = new RedisResult();

        try {
            Long res = redisTemplate.opsForList().size(key);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public  RedisResult lGetIndex(String key, long index) {
        RedisResult result = new RedisResult();

        try {
            Object res = redisTemplate.opsForList().index(key, index);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public  RedisResult lSet(String key, Object value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForList().rightPush(key, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public  RedisResult lSet(String key, Object value, long time) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public  RedisResult lSet(String key, List<Object> value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public  RedisResult lSet(String key, List<Object> value, long time) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */

    public  RedisResult lUpdateIndex(String key, long index, Object value) {
        RedisResult result = new RedisResult();

        try {
            redisTemplate.opsForList().set(key, index, value);
            return result.setSuccess(true);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */

    public  RedisResult lRemove(String key, long count, Object value) {
        RedisResult result = new RedisResult();

        try {
            Long res = redisTemplate.opsForList().remove(key, count, value);
            return result.setSuccess(true).setData(res);
        } catch (RedisConnectionFailureException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
