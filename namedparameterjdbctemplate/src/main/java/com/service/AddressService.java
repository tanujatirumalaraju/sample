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

import com.model.AddressModel;

@Service
public class AddressService {
	@Autowired
	NamedParameterJdbcTemplate namedparameterjdbctemplate;
	@Autowired
	JdbcTemplate jdbctemplate;  

	public Map<String, Object> insertAddressDetails(AddressModel addressmodel) {

		Map<String, Object> response = new HashMap();

		int address_id = addressmodel.getAddressId();
		int user_id = addressmodel.getUserId();
		String user_address = addressmodel.getUserAddress();
		String user_state = addressmodel.getUserState();
		int user_pincode = addressmodel.getUserPincode();

		try {

			String insertQuery = "INSERT INTO address(id,userid,address,state,pincode)VALUES(:addressId,:userId,:userAddress,:userState,:userPincode)";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("addressId", address_id)
					.addValue("userId", user_id).addValue("userAddress", user_address).addValue("userState", user_state)
					.addValue("userPincode", user_pincode);
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

	public Map<String, Object> updateAddressDetails(AddressModel addressmodel) {
		Map<String, Object> response = new HashMap();
		int userId = addressmodel.getUserId();
		String userAddress = addressmodel.getUserAddress();

		try {
			String updateQuery = "update address set address=:tempeaddress where userid=:tempid";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource()
					.addValue("tempeaddress", userAddress).addValue("tempid", userId);
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

	public Map<String, Object> deleteAddressDetails(AddressModel addressmodel) {
		Map<String, Object> response = new HashMap<>();
		int address_id = addressmodel.getAddressId();
		try {
			String deleteQuery = "delete from address where id=:tempid";
			MapSqlParameterSource mysqlparametersource = new MapSqlParameterSource().addValue("tempid", address_id);
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

	public Map<String, Object> getAddressDetails() {
		Map<String, Object> response = new HashMap<>();

		try {
			List<Map<String, Object>> list = new ArrayList();

			String query = "SELECT id,userid,address,state,pincode,date FROM address ";

			List<Map<String, Object>> addressList = jdbctemplate.queryForList(query);

			for (Map<String, Object> userMap : addressList) {
				int id = (Integer) userMap.get("id");
				int userid = (Integer) userMap.get("userid");
				String address = (String) userMap.get("address");
				String state = (String) userMap.get("state");
				int pin = (Integer) userMap.get("pincode");
				LocalDateTime date = (LocalDateTime) userMap.get("date");

				Map<String, Object> tempMap = new HashMap<>();
				tempMap.put("id", id);
				tempMap.put("userid", userid);
				tempMap.put("address", address);
				tempMap.put("state", state);
				tempMap.put("pin", pin);
				tempMap.put("createatdate", date);

				list.add(tempMap);
				System.out.println(id + " " + userid + " " + address + " " + state + " " + pin + " " + date);

			}

			response.put("userAddressList", list);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

}
