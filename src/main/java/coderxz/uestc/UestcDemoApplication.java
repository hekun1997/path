package coderxz.uestc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("coderxz.uestc.dao")
@ServletComponentScan
@SpringBootApplication
public class UestcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UestcDemoApplication.class, args);
    }

}
