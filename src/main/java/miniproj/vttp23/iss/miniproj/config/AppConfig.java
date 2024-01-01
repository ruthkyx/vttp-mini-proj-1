package miniproj.vttp23.iss.miniproj.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {
        // for redis ops 

    // create an instance of logger (mainly for debugging)
    private Logger logger = Logger.getLogger(AppConfig.class.getName());

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort; // indicates that the value is optional (injects values from app properties)
    
    @Value("${spring.redis.username}")
    private String redisUser;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.database}") 
    private Integer redisDatabase;

    // creating a redis configuration
    public RedisTemplate<String, String> createRedisConnection() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setDatabase(redisDatabase);
        // set only if it hasnt alrdy been set 
        if("NOT_SET".equals(redisUser.trim())) {
            config.setUsername(redisUser);
            config.setPassword(redisPassword);
        }
        // uses logger object to record log msg; Level set to INFO -> msg provides general info 
        logger.log(Level.INFO, "Using Redis database %d".formatted(redisPort)); 
        logger.log(Level.INFO, "Using Redis password is set: %b".formatted(redisPassword != "NOT_SET"));

        // part of Spring data REdis Library used to cr8 connection factory for Redis. 
        JedisClientConfiguration jedisClient = JedisClientConfiguration
            .builder().build(); 
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet(); // ensures JedisConnectionFactory properly initialized after property setting 

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisFac);

        // converts java Strings to UTF8 bytes so taht any program can read and execute the code functions 
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }
}
