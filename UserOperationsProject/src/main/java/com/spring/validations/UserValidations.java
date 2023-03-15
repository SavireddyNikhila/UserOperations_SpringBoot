//package com.spring.validations;
//
//import java.util.List;
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.spring.entity.User;
//import com.spring.repository.UserRepository;
//
//public class UserValidations {
//
//	public String validate(User user) {
//		StringBuilder sb = new StringBuilder();
//		List<User> users = userRepository.findAll();
//
//		// validating id
//		if (!Objects.nonNull(user.getUserId()) && "".equalsIgnoreCase(user.getUserId())) {
//			sb.append("UserId should be mandatory");
//		}
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getUserId().equals(user.getUserId())) {
//				sb.append("User Existed already with id: " + user.getUserId());
//			}
//		}
//		if (user.getUserId().length() > 36)
//			sb.append("max-length of UserId should be 36");
//
//		// validating firstname
//		if (!Objects.nonNull(user.getFirstName()) && "".equalsIgnoreCase(user.getFirstName())) {
//			sb.append("FirstName should be mandatory");
//		}
//		if (user.getFirstName().length() > 50)
//			sb.append("max-length of firstname should be 50");
//
//		// validating lastname
//		if (user.getLastName().length() > 50)
//			sb.append("max-length of lastname should be 50");
//
//		// validating gender
//		if (user.getGender().length() > 1)
//			sb.append("max-length of gender should be 1");
//
//		// validating email
//		if (!Objects.nonNull(user.getEmailId()) && "".equalsIgnoreCase(user.getEmailId())) {
//			sb.append("EmailId should be mandatory");
//		}
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getEmailId().equals(user.getEmailId())) {
//				sb.append("dupliacte email");
//			}
//
//		}
//		if (!(user.getEmailId().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}")))
//			sb.append("Email should not match with proper format");
//
//		// validating phoneNumber
//		if (!Objects.nonNull(user.getPhoneNumber()) && "".equalsIgnoreCase(user.getPhoneNumber())) {
//			sb.append("Phone should be mandatory");
//		}
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getPhoneNumber().equals(user.getPhoneNumber())) {
//				sb.append("duplicate phone number");
//			}
//		}
//		if (!(user.getPhoneNumber().matches("[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}")))
//			sb.append("PhoneNumber should not match with proper format");
//
//		// validating password
//		if (!Objects.nonNull(user.getPassword()) && "".equalsIgnoreCase(user.getPassword())) {
//			sb.append("Password should be mandatory");
//		}
//		if (!(user.getPassword().length() >= 8 && user.getPassword().length() <= 20))
//			sb.append("Password should be in the range of 8-20 characters");
//
//		if (!(user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$")))
//			sb.append("Password should not match with proper format");
//
//		return sb.toString();
//	}
//	/*
//	 * public String mandatoryFields(User user) { StringBuilder sb=new
//	 * StringBuilder(); if(!Objects.nonNull(user.getUserId()) &&
//	 * "".equalsIgnoreCase(user.getUserId())) {
//	 * sb.append("UserId should be mandatory"); }
//	 * if(!Objects.nonNull(user.getFirstName()) &&
//	 * "".equalsIgnoreCase(user.getFirstName())) {
//	 * sb.append("FirstName should be mandatory"); }
//	 * if(!Objects.nonNull(user.getEmailId()) &&
//	 * "".equalsIgnoreCase(user.getEmailId())) {
//	 * sb.append("EmailId should be mandatory"); }
//	 * if(!Objects.nonNull(user.getPhoneNumber()) &&
//	 * "".equalsIgnoreCase(user.getPhoneNumber())) {
//	 * sb.append("Phone should be mandatory"); }
//	 * if(!Objects.nonNull(user.getPassword()) &&
//	 * "".equalsIgnoreCase(user.getPassword())) {
//	 * sb.append("Password should be mandatory"); } return sb.toString(); }
//	 * 
//	 * public String maxLengthCheck(User user) { StringBuilder sb=new
//	 * StringBuilder(); if(user.getUserId().length()>36)
//	 * sb.append("max-length of UserId should be 36");
//	 * if(user.getFirstName().length()>50)
//	 * sb.append("max-length of firstname should be 50");
//	 * if(user.getLastName().length()>50)
//	 * sb.append("max-length of lastname should be 50");
//	 * if(user.getGender().length()>1)
//	 * sb.append("max-length of gender should be 1");
//	 * if(!(user.getPassword().length()>=8 && user.getPassword().length()<=20))
//	 * sb.append("Password should be in the range of 8-20 characters"); return
//	 * sb.toString(); }
//	 * 
//	 * public String validateFieldsWithRegex(User user) { StringBuilder sb=new
//	 * StringBuilder(); if(!(user.getEmailId().matches(
//	 * "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}")))
//	 * sb.append("Email should not match with proper format");
//	 * if(!(user.getPhoneNumber().matches(
//	 * "[\\\\\\\\\\\\\\\\+]+[0-9]{2}+[-]+[0-9]{10}")))
//	 * sb.append("PhoneNumber should not match with proper format");
//	 * if(!(user.getPassword().matches(
//	 * "^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[A-Za-z0-9]).{8,16}$")))
//	 * sb.append("Password should not match with proper format"); return
//	 * sb.toString();
//	 * 
//	 * }
//	 */
//}
