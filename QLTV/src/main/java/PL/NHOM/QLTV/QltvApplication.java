package PL.NHOM.QLTV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class QltvApplication {

	public static void main(String[] args) {
		SpringApplication.run(QltvApplication.class, args);
	}

}
