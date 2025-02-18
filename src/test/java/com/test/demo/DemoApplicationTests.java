package com.test.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	void setUp() {
		user = new User();
		user.setName("Nguyen Van A");
		user.setPhone("0123456789");
		user.setAddress("123 Main St");
		user.setEmail("nguyenvana@example.com");
		user.setCreatedDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
	}

	@Test
	void testCreateUser() {
		User savedUser = userRepository.save(user);
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isNotNull();
	}

	@Test
	void testReadUser() {
		User savedUser = userRepository.save(user);
		Optional<User> foundUser = userRepository.findById(savedUser.getId());
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getEmail()).isEqualTo("nguyenvana@example.com");
	}

	@Test
	void testUpdateUser() {
		User savedUser = userRepository.save(user);
		savedUser.setName("Nguyen Van B");
		User updatedUser = userRepository.save(savedUser);
		assertThat(updatedUser.getName()).isEqualTo("Nguyen Van B");
	}

	@Test
	void testDeleteUser() {
		User savedUser = userRepository.save(user);
		userRepository.delete(savedUser);
		Optional<User> deletedUser = userRepository.findById(savedUser.getId());
		assertThat(deletedUser).isEmpty();
	}
}
