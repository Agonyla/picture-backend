package com.agony.picturebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Agony
 * @create: 2025/3/24 10:06
 * @describe:
 */

@SpringBootTest
public class RedisStringTest {


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testStringRedisTemplateOps() {

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        String key = "redisK1";
        String redisVal = "redisV1";

        ops.set(key, redisVal);
        String storedValue = ops.get(key);
        assertEquals(redisVal, storedValue, "存储的值与预期不一致");

        // 2. 测试修改操作
        String updatedValue = "updatedValue";
        ops.set(key, updatedValue);
        storedValue = ops.get(key);
        assertEquals(updatedValue, storedValue, "更新后的值与预期不一致");

        // 3. 测试查询操作
        storedValue = ops.get(key);
        assertNotNull(storedValue, "查询的值为空");
        assertEquals(updatedValue, storedValue, "查询的值与预期不一致");

        // 4. 测试删除操作
        stringRedisTemplate.delete(key);
        storedValue = ops.get(key);
        assertNull(storedValue, "删除后的值不为空");

    }
}
