package com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.model.UserModel;

@Service
public class UserService {
	@Autowired
	NamedParameterJdbcTemplate namedparameterjdbctemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<String, Object> insertUserDetails(UserModel usermodel) {
		Map<String, Object> response = new HashMap();

		int user_id = usermodel.getUserId();
		String user_name = usermodel.getUserName();
		String user_email = usermodel.getUserEmail();
		String user_password = usermodel.getUserPassword();
		int user_age = usermodel.getUserAge();

		try {

			String insertQuery = "INSERT INTO user (id,name,email,password,age)VALUES(:userId,:userName,:userEmail,:userPassword,:userAge)";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("userId", user_id)
					.addValue("userName", user_name).addValue("userEmail", user_email)
					.addValue("userPassword", user_password).addValue("userAge", user_age);
			int result = namedparameterjdbctemplate.update(insertQuery, mysqlparametersource);
			if (result > 0)
				response.put("row", "inserted succesfully");
			else
				response.put("error occured", "during insertion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Map<String, Object> updateUserDetails(int user_id, String user_email) {
		Map<String, Object> response = new HashMap();
		try {
			String updateQuery = "update user set email=:tempemail where id=:tempid";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("tempemail", user_email)
					.addValue("user_id", user_id);
			int result = namedparameterjdbctemplate.update(updateQuery, mysqlparametersource);
			if (result > 0) {
				response.put("row", "updated successfully");
			} else {
				response.put("note", "Enter valid id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public Map<String, Object> deleteUserDetails(UserModel usermodel) {
		Map<String, Object> response = new HashMap<>();
		String user_name = usermodel.getUserName();
		try {
			String deleteQuery = "delete from user where name=:tempname";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("tempname", user_name);
			int result = namedparameterjdbctemplate.update(deleteQuery, mysqlparametersource);
			if (result > 0) {
				response.put("row", "deleted successfully");
			} else {
				response.put("note", "no record available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public Map<String, Object> updateUserDetails(UserModel usermodel) {
		Map<String, Object> response = new HashMap();
		int user_id = usermodel.getUserId();
		String user_email = usermodel.getUserEmail();
		try {
			String updateQuery = "update user set email=:tempemail where id=:tempid";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("tempemail", user_email)
					.addValue("tempid", user_id);
			int result = namedparameterjdbctemplate.update(updateQuery, mysqlparametersource);
			if (result > 0) {
				response.put("row", "updated successfully");
			} else {
				response.put("note", "Enter valid id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public Map<String, Object> getUserDetails() {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			String query = "SELECT id,name,email,password,age,date FROM user ";

			List<Map<String, Object>> userList = jdbcTemplate.queryForList(query);

			for (Map<String, Object> userMap : userList) {

				int userid = (Integer) userMap.get("id");
				String name = (String) userMap.get("name");
				String email = (String) userMap.get("email");
				String pin = (String) userMap.get("password");
				int age = (Integer) userMap.get("age");
				LocalDateTime date = (LocalDateTime) userMap.get("date");

				Map<String, Object> tempMap = new HashMap<>();

				tempMap.put("userid", userid);
				tempMap.put("address", name);
				tempMap.put("state", email);
				tempMap.put("pin", pin);
				tempMap.put("age", age);
				tempMap.put("createatdate", date);

				list.add(tempMap);
				System.out.println(userid + " " + name + " " + email + " " + pin + " " + age + " " + date);

			}

			response.put("userList", list);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public List<Map<String, Object>> getUserDetails(int id) {
		List<Map<String, Object>> response = new ArrayList();

		try {
			
			String seleteQuery = "SELECT id,name,email,password,age,date FROM user where id=?";

			response = jdbcTemplate.queryForList(seleteQuery, id);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

}
