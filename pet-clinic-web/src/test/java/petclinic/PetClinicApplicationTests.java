package petclinic;

import com.springframework.petclinic.PetClinicApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PetClinicApplication.class)
public class PetClinicApplicationTests {

	@Test
	void contextLoads() {

	}

}
