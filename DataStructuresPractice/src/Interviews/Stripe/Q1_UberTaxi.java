package Interviews.Stripe;

import java.util.*;

public class Q1_UberTaxi {

	public  List<Integer>  getPaidAmount(List<String[]> api, List<String[]> bal){
		HashMap<String,Double> BalData = new HashMap<>();
		processAPIData(api,BalData);
		List<Integer> result = new ArrayList<>();
		
		for(String[] x: bal){
			for (String s : x) {
				String[] params = s.split("=");
				if(BalData.containsKey(params[1])){
					result.add((int)Math.round(BalData.get(params[1])));
				}
			}
		}
		return result;
	}
	
	private void processAPIData(List<String[]> api,HashMap<String,Double> BalData){
		for(String[] s: api) {
			APIData apiObj = new APIData();
			for (String x : s) {
				String[] params = x.split("=");
				if(params[0].equals("amount")){
					apiObj.setAmount(Double.parseDouble(params[1]));
				}else if(params[0].equals("merchant")){
					apiObj.setMerchantId(params[1]);
				}else if(params[0].equals("destination[account]")){
					apiObj.setDestinationAccId(params[1]);
				}else if(params[0].equals("destination[amount]")){
					apiObj.setDestinationAmount(Double.parseDouble(params[1]));
				}
			}
			calculateAmountValues(apiObj,BalData);
		}
	}
	
	private void calculateAmountValues(APIData obj, HashMap<String,Double> BalData){
		String DriverId = obj.getDestinationAccId();
		double driverAmount = obj.getDestinationAmount();
		double stripeFee = (obj.getAmount() * 2.9)/100 + 30;
		String merchantiD = obj.getMerchantId();
		double merchantFee = obj.getAmount() - stripeFee - driverAmount;
		
		BalData.put(merchantiD,BalData.getOrDefault(merchantiD,0.0)+merchantFee);
		BalData.put(DriverId,BalData.getOrDefault(DriverId,0.0)+driverAmount);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String[]> APIlist = new ArrayList();
		List<String[]> BALlist = new ArrayList();
		int paramLength = sc.nextInt();
		sc.nextLine();
		
		for(int i = 0; i < paramLength; i++){
			String s[] = sc.nextLine().split(" ");
			if(s[0].equals("API:"))
				APIlist.add(s[1].split("&"));
			else
				BALlist.add(s[1].split("&"));
		}
		Q1_UberTaxi cl = new Q1_UberTaxi();
		List<Integer> x = cl.getPaidAmount(APIlist,BALlist);
		System.out.println(x);
	}
}

class APIData{
	double amount;
	String merchantId;
	String destinationAccId;
	double destinationAmount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getDestinationAccId() {
		return destinationAccId;
	}
	public void setDestinationAccId(String destinationAccId) {
		this.destinationAccId = destinationAccId;
	}
	public double getDestinationAmount() { return destinationAmount; }
	public void setDestinationAmount(double destinationAmount) {
		this.destinationAmount = destinationAmount;
	}
}
