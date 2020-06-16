package com.bridgelabz.bookstore;

import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class.class)
@SpringBootTest
class BookStoreApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	private MockMvc mockMvc;



	@Test
	void contextLoads() {
	}

	@Test
	public void getUserTest(){
		when(userRepository.findAll()).thenReturn(Stream.
				of(new User("ganesh", "ganesh@gmail.com", "12345678"), new User("ganesh1", "ganesh123@gmail.com", "12345678")).collect(Collectors.toList()));
		assertEquals(2,userService.getAllUsers().size());
	}

	@Test void deleteUserTest()throws Exception{
		long id = 18;
		userService.deleteUser(id);
		verify(userRepository).deleteById(id);
	}
}
