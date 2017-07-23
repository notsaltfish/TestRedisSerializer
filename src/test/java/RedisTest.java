
import com.redis.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

/**
 * Created by xx on 2017-07-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RedisTest {

    @Autowired
   private  RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /*测试JdkSerializationRedisSerializer和*/
    @Test
    public void testRedisSerializer(){
        User u = new User();
        u.setName("java");
        u.setSex("male");
        redisTemplate.opsForHash().put("user:","1",u);
    /*查看redisTemplate 的Serializer*/
        System.out.println(redisTemplate.getKeySerializer());
        System.out.println(redisTemplate.getValueSerializer());

    /*查看StringRedisTemplate 的Serializer*/
        System.out.println(stringRedisTemplate.getValueSerializer());
        System.out.println(stringRedisTemplate.getValueSerializer());

        /*将stringRedisTemplate序列化类设置成RedisTemplate的序列化类*/
        stringRedisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        /*即使在更换stringRedisTemplate的的Serializer和redisTemplate一致的
        * JdkSerializationRedisSerializer
        * 最后还是无法从redis中获取序列化的数据
        * */
        System.out.println(stringRedisTemplate.getValueSerializer());
        System.out.println(stringRedisTemplate.getValueSerializer());

        User user = (User)  redisTemplate.opsForHash().get("user:","1");
        User  user2 = (User) stringRedisTemplate.opsForHash().get("user:","1");
        System.out.println("dsd");
    }


}
