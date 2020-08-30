package Interviews.Stripe;

import java.util.*;

class Loan {
	double loanAmount;
	String merchantId;
	double fixedFee;
	double leftFees;

	public Loan(String id) {
		this.merchantId = id;
	}

	public double getLeftFees() {
		return leftFees;
	}

	public void setLeftFees(double leftFees) {
		this.leftFees = leftFees;
	}

	double repaymentPercentage;

	public String getMerchantId() {
		return this.merchantId;
	}

	public double getFixedFee() {
		return this.fixedFee;
	}

	public void setFixedFee(double fixedFee) {
		this.fixedFee = fixedFee;
		this.leftFees = fixedFee;
	}

	public double getRepaymentPercentage() {
		return this.repaymentPercentage;
	}

	public void setRepaymentPercentage(double repaymentPercentage) {
		this.repaymentPercentage = repaymentPercentage;
	}

	public double getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(double amount) {
		this.loanAmount = amount;
	}

}

class Merchant {
	String merchantId;
	Queue<Loan> loan;
	ArrayList<Loan> processedLoans;
	double totalFeesPaid;

	public Merchant(String id) {
		this.merchantId = id;
		this.loan = new LinkedList<Loan>();
		this.processedLoans = new ArrayList<Loan>();
	}

	public double getTotalFeesPaid() {
		return totalFeesPaid;
	}

	public ArrayList<Loan> getProcessedLoans() {
		return this.processedLoans;
	}

	public void updateProcessedLoans(Loan processedLoan) {
		this.processedLoans.add(processedLoan);
	}

	public void setTotalFeesPaid(double totalFeesPaid) {
		this.totalFeesPaid += totalFeesPaid;
	}

	double saleAmount;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Queue<Loan> getLoan() {
		return loan;
	}

	public void setLoan(Queue<Loan> loan) {
		this.loan = loan;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}

}

public class Q1 {

	private static final String createLoanPrefix = "CREATE_LOAN:";
	private static final String payLoanPrefix = "PAY_LOAN:";
	private static final String transactionPrefix = "TRANSACTION_PROCESSED:";

	private static final String merchantIdPrefix = "merchant_id";
	private static final String amountprefix = "amount";
	private static final String fixedFeePrefix = "fee";
	private static final String repaymentPercentage = "repayment_percentage";

	private static final HashMap<String, Merchant> merchantsMap = new HashMap<String, Merchant>();

	public static void processLoanAPI(String loanAPI) {
		loanAPI = loanAPI.substring(loanAPI.indexOf(createLoanPrefix) + createLoanPrefix.length() + 1);
		String[] data = loanAPI.split("&");
		Merchant merchant = null;
		Loan loan = null;

		for (String str : data) {
			String[] arr = str.split("=");

			if (arr[0].equals(merchantIdPrefix)) {
				String merchantId = arr[1];
				merchant = merchantsMap.get(merchantId);
				if (merchant == null) {
					merchant = new Merchant(merchantId);
					merchantsMap.put(merchantId, merchant);
				}
			} else if (arr[0].equals(amountprefix)) {
				double loanAmt = Double.valueOf(arr[1]);
				loan = new Loan(merchant.getMerchantId());
				loan.setLoanAmount(loanAmt);
				Queue<Loan> q = merchant.getLoan();
				q.add(loan);
			} else if (arr[0].equals(fixedFeePrefix)) {
				double fixedFee = Double.valueOf(arr[1]);
				loan.setFixedFee(fixedFee);
			} else if (arr[0].equals(repaymentPercentage)) {
				double repaymentPercentage = Double.valueOf(arr[1]);
				loan.setRepaymentPercentage(repaymentPercentage);
			}
		}
	}

	private static void processTransactions(String transactionAPI) {
		transactionAPI = transactionAPI
				.substring(transactionAPI.indexOf(transactionPrefix) + transactionPrefix.length() + 1);
		String[] data = transactionAPI.split("&");

		Merchant merchant = null;
		double sale = 0.0;

		for (String str : data) {
			String[] arr = str.split("=");

			if (arr[0].equals(merchantIdPrefix)) {
				merchant = merchantsMap.get(arr[1]);

			} else if (arr[0].equals(amountprefix)) {
				sale = Double.valueOf(arr[1]);
				processSingleRepayment(sale, merchant);

			}
		}
	}

	private static void processSingleRepayment(double sale, Merchant merchant) {
		Queue<Loan> q = merchant.getLoan();
		Loan loan = q.peek();
		double loanAmt = loan.getLoanAmount();
		double leftFees = loan.getLeftFees();
		double repayment = sale * loan.getRepaymentPercentage() / 100;

		// first pay fees
		if (leftFees > 0) {
			if (repayment >= leftFees) {
				loan.setLeftFees(0);
			} else {
				loan.setLeftFees(leftFees - repayment);
			}
			repayment -= leftFees;
		}

		// pay loan now
		if (repayment > 0) {
			double leftLoan = loanAmt - repayment <= 0 ? 0 : loanAmt - repayment;
			loan.setLoanAmount(leftLoan);
			if (leftLoan == 0) {
				merchant.updateProcessedLoans(q.poll());
			}
		}
	}

	private static void processLoanRepayment(double amount, Merchant merchant) {
		double repayment = amount;
		Queue<Loan> q = merchant.getLoan();

		while (repayment > 0 && !q.isEmpty()) {

			Loan loan = q.peek();
			double loanAmt = loan.getLoanAmount();
			double leftFees = loan.getLeftFees();

			// first pay fees
			if (leftFees > 0) {
				if (repayment >= leftFees) {
					loan.setLeftFees(0);
				} else {
					loan.setLeftFees(leftFees - repayment);
				}
				repayment -= leftFees;
			}

			// pay loan now
			if (repayment > 0) {
				double leftLoan = loanAmt - repayment <= 0 ? 0 : loanAmt - repayment;
				loan.setLoanAmount(leftLoan);
				if (leftLoan == 0) {
					merchant.updateProcessedLoans(q.poll());
				}
				repayment -= loanAmt;
			}
		}
	}

	private static void processManualLoanPayment(String payLoanAPI) {
		payLoanAPI = payLoanAPI.substring(payLoanAPI.indexOf(payLoanPrefix) + payLoanPrefix.length() + 1);
		String[] data = payLoanAPI.split("&");
		Merchant merchant = null;
		Loan loan = null;

		for (String str : data) {
			String[] arr = str.split("=");

			if (arr[0].equals(merchantIdPrefix)) {
				String merchantId = arr[1];
				merchant = merchantsMap.get(arr[1]);
			} else if (arr[0].equals(amountprefix)) {
				double loanAmt = Double.valueOf(arr[1]);

				// LOOP over all left loans
				processLoanRepayment(loanAmt, merchant);
			}
		}
	}

	private static void processBalQuery() {

		for (Merchant m : merchantsMap.values()) {
			double loanAmt = 0.0;
			Queue<Loan> queue = m.getLoan();

			Iterator<Loan> itr = queue.iterator();

			// hasNext() returns true if the queue has more elements
			while (itr.hasNext()) {
				// next() returns the next element in the iteration
				Loan loan = itr.next();
				loanAmt += loan.getLoanAmount()+loan.getLeftFees();
			}
			System.out.println(m.merchantId + "," + Math.round(loanAmt));
		}
	}

	private static void getTotalFeePaidToStripeQuery() {
		double totalFee = 0.0;

		for (Merchant m : merchantsMap.values()) {

			Queue<Loan> queue = m.getLoan();

			Iterator<Loan> itr = queue.iterator();

			// hasNext() returns true if the queue has more elements
			while (itr.hasNext()) {
				// next() returns the next element in the iteration
				Loan loan = itr.next();
				totalFee += loan.getFixedFee() - loan.getLeftFees();
			}
			
			ArrayList<Loan> list = m.getProcessedLoans();
			for (Loan l : list) {
				totalFee += l.getFixedFee();
			}

			System.out.println((int)totalFee);
		}
	}

	public static void process_api_lines(List<String> api_lines) {
		for (String str : api_lines) {
			if (str.contains(transactionPrefix)) {
				processTransactions(str);
			} else if (str.contains(createLoanPrefix)) {
				processLoanAPI(str);
			} else if (str.contains(payLoanPrefix)) {
				processManualLoanPayment(str);
			}
		}
	}

	private static void testCase1() {
		String charge1 = "CREATE_LOAN: merchant_id=acct_foobar&amount=1000&repayment_percentage=10&fee=100";
		String charge2 = "CREATE_LOAN: merchant_id=acct_foobar&amount=2000&repayment_percentage=10&fee=200";

		String t = "PAY_LOAN: merchant_id=acct_foobar&amount=7000";
		List<String> input = new ArrayList(Arrays.asList(charge1, charge2, t));
		Q1.process_api_lines(input);

		processBalQuery();
		getTotalFeePaidToStripeQuery();
	}

	private static void testCase2() {
		String charge1 = "CREATE_LOAN: merchant_id=acct_foobar&amount=1000&repayment_percentage=10&fee=100";
		String charge2 = "CREATE_LOAN: merchant_id=acct_foobar&amount=1000&repayment_percentage=15&fee=100";

		String t1 = "TRANSACTION_PROCESSED: merchant_id=acct_foobar&amount=14000";
		String t2 = "TRANSACTION_PROCESSED: merchant_id=acct_foobar&amount=7000";

		List<String> input = new ArrayList(Arrays.asList(charge1, charge2, t1, t2));
		Q1.process_api_lines(input);
		processBalQuery();
		getTotalFeePaidToStripeQuery();
	}

	private static void testCase3() {
		String charge1 = "CREATE_LOAN: merchant_id=acct_other&amount=1500&repayment_percentage=15&fee=150";
		String charge2 = "CREATE_LOAN: merchant_id=acct_foobar&amount=1500&repayment_percentage=15&fee=150";
		String charge3 = "CREATE_LOAN: merchant_id=acct_foobar&amount=2000&repayment_percentage=20&fee=200";

		String t1 = "TRANSACTION_PROCESSED: merchant_id=acct_foobar&amount=1750";
		String t2 = "PAY_LOAN: merchant_id=acct_foobar&amount=1539";

		List<String> input = new ArrayList(Arrays.asList(charge1, charge2, charge3, t1, t2));
		Q1.process_api_lines(input);
		processBalQuery();
		getTotalFeePaidToStripeQuery();
	}

	public static void main(String[] args) {
		Q1 obj = new Q1();
		//Q1.testCase1();
		//Q1.testCase2();
		Q1.testCase3();
	}
}
