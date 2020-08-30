package Interviews.Stripe;

import java.util.*;

public class Q2_RadarRules {
	
	private boolean isTransactionAllowed(String s) {
//		int start = s.indexOf("[");
//		int end = s.indexOf("]");
//		String str = s.substring(start + 1, end);
		String[] arr = s.split(",");
		//System.out.println(Arrays.toString(arr));
		
		Charge charges;
		Map<String,Map<String,String>> allowMap = new HashMap<>();
		Map<String,Map<String,String>> blockMap = new HashMap<>();
		
		for (String apis : arr) {
			String[] separator = apis.split(":");
			if (separator[0].equals("CHARGE")){
				charges = resolveChargeAPI(separator[1]);
			}else if(separator[0].equals("ALLOW")) {
				resolveAllowAPI(separator[1], allowMap);
			}else if(separator[0].equals("BLOCK")) {
				resolveBlockAPI(separator[1],blockMap);
			}
		}
		
		return true;
	}
	
	private Charge resolveChargeAPI(String chargeAPI){
		String[] charges = chargeAPI.split("&");
		Charge newCharge = new Charge();
		for(String s : charges){
			s.trim();
			String[] separator = s.split("=");
			if(separator[0].equals("card_country")) {
				newCharge.setCard_country(separator[1]);
			}else if(separator[0].equals("currency")){
				newCharge.setCurrency(separator[1]);
			}else if(separator[0].equals("amount")){
				newCharge.setAmount(Double.parseDouble(separator[1]));
			}else if(separator[0].equals("ip_country")){
				newCharge.setIp_country(separator[1]);
			}
		}
		return newCharge;
	}
	String[] operators = {">","<","<=",">=","==","!="};
	private void resolveAllowAPI(String allowAPI, Map<String,Map<String,String>> allowMap ){
		String[] allowed = allowAPI.split("AND");
		if(allowed.length == 0) allowed = allowAPI.split("OR");
		if(allowed.length > 0){
			for(String condition : allowed) {
				for (String operator : operators)
					if (condition.contains(operator)) {
						String[] array = condition.split(operator);
						allowMap.put(array[0], new HashMap<>());
						Map<String, String> map = allowMap.get(array[0]);
						map.put(operator, array[1]);
						allowMap.put(array[0],map);
					}
			}
		}
	}
	
	private void resolveBlockAPI(String blockAPI,Map<String,Map<String,String>> blockMap ){
		String[] blocked = blockAPI.split("AND");
		if(blocked.length == 0) blocked = blockAPI.split("OR");
		if(blocked.length > 0){
			for(String condition : blocked) {
				for (String operator : operators){
					if (condition.contains(operator)) {
						String[] array = condition.split(operator);
						blockMap.put(array[0], new HashMap<>());
						Map<String, String> map = blockMap.get(array[0]);
						map.put(operator, array[1]);
						blockMap.put(array[0],map);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		Q2_RadarRules cl = new Q2_RadarRules();
		System.out.println(cl.isTransactionAllowed(s));
	}
}

class Charge{
	String card_country;
	String currency;
	double amount;
	String ip_country;
	
	public String getCard_country() {
		return card_country;
	}
	
	public void setCard_country(String card_country) {
		this.card_country = card_country;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getIp_country() {
		return ip_country;
	}
	
	public void setIp_country(String ip_country) {
		this.ip_country = ip_country;
	}
}
