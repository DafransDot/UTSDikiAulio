package com.example.utsdikiaulio.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResponse{

	@SerializedName("items")
	private List<UserResponse> users;


	public List<UserResponse> getUsers(){
		return users;
	}

}