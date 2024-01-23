package com.algo.hackerank;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.algo.hackerank.RestAPI;

public class FootballMatch {

	public static void main(String[] args) throws Exception {
		int year = 0; // param
		String team = "ABC"; // param
		
		String team1Url = String.format("https://jsonmock.hackerrank.com/api/football_matches?year=%d&team1=%s", year, URLEncoder.encode(team,"UTF-8"));
		String team2Url = String.format("https://jsonmock.hackerrank.com/api/football_matches?year=%d&team2=%s", year, URLEncoder.encode(team,"UTF-8"));

		System.out.println(
				getTeamGoals(team1Url,"team1", 1,0) + getTeamGoals(team2Url,"team2", 1,0)); ;
	}
	
	
   /**
    * Pageable  分页调用收集答案, dfs 收集答案给 totalGoals
    */
   private static int getTeamGoals(String teamUrl, String teamtype, int page, int totalGoals) throws Exception {
		
		String response = RestAPI.call(teamUrl, page);
		
		JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
		int totalPages = jsonResponse.get("total_pages").getAsInt();
		JsonArray data = jsonResponse.getAsJsonArray("data");
		for (JsonElement e : data) {
			totalGoals += e.getAsJsonObject().get(teamtype+"goals").getAsInt();		
		}
		
		return totalPages==page? totalGoals : getTeamGoals(teamUrl, teamtype, page + 1, totalGoals);
	}
}
