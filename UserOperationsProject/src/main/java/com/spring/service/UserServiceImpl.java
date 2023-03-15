package com.spring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.exception.UserException;
import com.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) throws UserException {
		String str = new String();
		str = validate(user);

		if (!"".equalsIgnoreCase(str)) {
			throw new UserException(str);
		}

		user.setCreateDate(LocalDate.now());
		user.setUpdateDate(LocalDate.now());
		return userRepository.save(user);

	}

	@Override
	public List<User> saveListOfUsers(List<User> users) throws UserException {
		String str = new String();
		for(int i=0;i<users.size();i++) {
			str = validate(users.get(i));

			if (!"".equalsIgnoreCase(str)) {
				throw new UserException("user with id: "+users.get(i).getUserId()+" has following exceptions: "+str);
			}

			users.get(i).setCreateDate(LocalDate.now());
			users.get(i).setUpdateDate(LocalDate.now());
		}
		return userRepository.saveAll(users);
	}
	
	@Override
	public User updateUser(String userId, User user) throws UserException {
		Optional<User> u = userRepository.findById(userId);
		if (!u.isPresent()) {
			throw new UserException("User not found with given Id: " + userId);
		}else {
			
		StringBuilder sb = new StringBuilder();
		List<User> users = userRepository.findAll();
		
		// validating firstname
		if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
			if (!(user.getFirstName().length() <= 50)) {
				sb.append(" max-length of firstname should be 50,");
			}
		} else {
			sb.append(" FirstName should be mandatory,");
		}
		// validating lastname
		if (!(user.getLastName().length() <= 50))
			sb.append(" max-length of lastname should be 50,");

		// validating gender
		if (user.getGender().length() == 1) {
			if (!(user.getGender().matches("(?:[M|F|O])"))) {
				sb.append(" Please provide proper gender [M|F|O],");
			} 
		}else {
				sb.append(" max-length of gender should be 1,");
		}
		
		// validating email
		if (Objects.nonNull(user.getEmailId()) && !"".equalsIgnoreCase(user.getEmailId())) {

			if ((user.getEmailId().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getEmailId().equals(user.getEmailId())) {
						sb.append(" dupliacte email,");
					}
				}
			} else {
				sb.append(" Email should match with proper format,");
			}

		} else {
			sb.append(" EmailId should be mandatory,");
		}

		// validating phoneNumber
		if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
			if ((user.getPhoneNumber().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getPhoneNumber().equals(user.getPhoneNumber())) {
						sb.append(" duplicate phone number,");
					}
				}
			} else
				sb.append(" PhoneNumber should match with proper format,");
		} else {
			sb.append(" Phone number should be mandatory,");
		}

		// validating password
		if ((Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword()))) {
			if (((user.getPassword().length() >= 8 && user.getPassword().length() <= 20))) {
				if (!(user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$"))) {
					sb.append(" Password should match with proper format");
				}
			} else {
				sb.append(" Password should be in the range of 8-20 characters");
			}

		} else {
			sb.append(" Password should be mandatory");
		}
		
		String str=sb.toString();
		if (!"".equalsIgnoreCase(str)) {
			throw new UserException(str);
		}else {
			User userDB = userRepository.findById(userId).get();

			if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
				userDB.setFirstName(user.getFirstName());
			}
			if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
				userDB.setLastName(user.getLastName());
			}
			if (Objects.nonNull(user.getGender()) && !"".equalsIgnoreCase(user.getGender())) {
				userDB.setGender(user.getGender());
			}
			if (Objects.nonNull(user.getEmailId()) && !"".equalsIgnoreCase(user.getEmailId())) {
				userDB.setEmailId(user.getEmailId());
			}
			if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
				userDB.setPhoneNumber(user.getPhoneNumber());
			}
			if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
				userDB.setPassword(user.getPassword());
			}
			if (Objects.nonNull(user.getDateOfBirth())) {
				userDB.setDateOfBirth(user.getDateOfBirth());
			}
			if (Objects.nonNull(user.getProfilePhoto()) && !"".equalsIgnoreCase(user.getProfilePhoto())) {
				userDB.setProfilePhoto(user.getProfilePhoto());
			}
			if (Objects.nonNull(user.getCreateDate())) {
				userDB.setCreateDate(user.getCreateDate());
			}
			if (Objects.nonNull(user.getUpdateDate())) {
				userDB.setUpdateDate(user.getUpdateDate());
			}
			userDB.setUpdateDate(LocalDate.now());
			return userRepository.save(userDB);
		}
		}
		
		

	}

	@Override
	public User fetchUserByIdOrEmailOrPhone(String userId, String emailId, String phoneNumber) throws UserException{
		User user = userRepository.findByUserIdOrEmailIdOrPhoneNumber(userId, emailId, phoneNumber);
		if (user != null) {
			return user;
		} else {
			throw new UserException("No User Found");
		}
	}
	
	@Override
	public List<User> ListUsers() throws UserException {
		List<User> list = userRepository.findAll();
		if (list.size() != 0) {
			return list;
		} else {
			throw new UserException("Users is Empty");
		}
	}

	@Override
	public List<User> fetchUsersCreateDateBetween(LocalDate date1, LocalDate date2) throws UserException {
		List<User> users = userRepository.findByCreateDateBetween(date1, date2);
		if (users.size() != 0) {
			return users;
		} else {
			throw new UserException("No User Found");
		}
	}

	@Override
	public void deleteUser(String userId) throws UserException {
		Optional<User> u = userRepository.findById(userId);
		if (!u.isPresent()) {
			throw new UserException("User not found with given Id: " + userId);
		} else {
			userRepository.deleteById(userId);
		}
	}
	
//=============================================================================================================================================	
	public String validate(User user) {
		StringBuilder sb = new StringBuilder();
		List<User> users = userRepository.findAll();

		// validating id
		if (Objects.nonNull(user.getUserId()) && !"".equalsIgnoreCase(user.getUserId())) {
			if (user.getUserId().length() <= 36) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUserId().equals(user.getUserId())) {
						sb.append("User Existed already with id: " + user.getUserId()+",");
					}
				}
			} else {
				sb.append("max-length of UserId should be 36,");
			}
		} else {
			sb.append("UserId should be mandatory,");
		}

		// validating firstname
		if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
			if (!(user.getFirstName().length() <= 50)) {
				sb.append(" max-length of firstname should be 50,");
			}
		} else {
			sb.append(" FirstName should be mandatory,");
		}
		// validating lastname
		if (!(user.getLastName().length() <= 50))
			sb.append(" max-length of lastname should be 50,");

		// validating gender
		if (user.getGender().length() == 1) {
			if (!(user.getGender().matches("(?:[M|F|O])"))) {
				sb.append(" Please provide proper gender [M|F|O],");
			} 
		}else {
				sb.append(" max-length of gender should be 1,");
		}
		
		// validating email
		if (Objects.nonNull(user.getEmailId()) && !"".equalsIgnoreCase(user.getEmailId())) {

			if ((user.getEmailId().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}"))) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getEmailId().equals(user.getEmailId())) {
						sb.append(" dupliacte email,");
					}
				}
			} else {
				sb.append(" Email should match with proper format,");
			}

		} else {
			sb.append(" EmailId should be mandatory,");
		}

		// validating phoneNumber
		if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())) {
			if ((user.getPhoneNumber().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}"))) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getPhoneNumber().equals(user.getPhoneNumber())) {
						sb.append(" duplicate phone number,");
					}
				}
			} else
				sb.append(" PhoneNumber should match with proper format,");
		} else {
			sb.append(" Phone number should be mandatory,");
		}

		// validating password
		if ((Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword()))) {
			if (((user.getPassword().length() >= 8 && user.getPassword().length() <= 20))) {
				if (!(user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$"))) {
					sb.append(" Password should match with proper format");
				}
			} else {
				sb.append(" Password should be in the range of 8-20 characters");
			}

		} else {
			sb.append(" Password should be mandatory");
		}

		return sb.toString();
	}

	

}
