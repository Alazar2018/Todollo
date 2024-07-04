package et.com.alazdev.todollo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Todollo", version = "1.0",description = "Done by the Alazar Tilahun"))
public class TodolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolloApplication.class, args);
	}

}
