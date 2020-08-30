package Interviews.Stripe;

import java.util.*;

public class Q3_CapitalInvestment {
	public Double getBalance(List<String> list){
		
		for( String s : list){
			String[] api = s.split(":");
			if(api[0].equals("CHARGE")){
				resolveChargeAPI(api[1].trim());
			}
		}
		
		return 0.0;
	}
	
	private void resolveChargeAPI(String s){
		String[] charges = s.split("&");
		for(String charge : charges){
			String[] params = charge.split("=");
			
			
		}
	}
	
	public static void main(String[] args) {
		String charge1 = "CHARGE: merchantId=1000&LoanAmount=25000&Fee=15";
		String charge2 = "CHARGE: merchantId=2000&LoanAmount=20000&Fee=10";
		String t1 = "TRANSACTION: merchantId=1000&Sale=1200&Fee=10";
		String t2 = "TRANSACTION: merchantId=1000&Sale=1000&Month=15";
		String t3 = "TRANSACTION: merchantId=1000&Sale=1100&Month=Mar";
		String t4 = "TRANSACTION: merchantId=1000&Sale=1200&Month=Jan";
		String t5 = "TRANSACTION: merchantId=1000&Sale=1000&Month=Feb";
		String t6 = "TRANSACTION: merchantId=1000&Sale=1100&Month=Mar";
		
		List<String> list = new ArrayList();
		list.add(charge1);
		list.add(charge2);
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		
		Q3_CapitalInvestment ci = new Q3_CapitalInvestment();
		System.out.println(ci.getBalance(list));
	}
}

class ChargeAPI{
	private String merchantId;
	private Double LoanAmount;
	private Double Fee;
	
	public String getMerchantId() {
		return merchantId;
	}
	
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	public Double getLoanAmount() {
		return LoanAmount;
	}
	
	public void setLoanAmount(Double loanAmount) {
		LoanAmount = loanAmount;
	}
	
	public Double getFee() {
		return Fee;
	}
	
	public void setFee(Double fee) {
		Fee = fee;
	}
}
