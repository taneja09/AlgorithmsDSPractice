package LC_Medium;

import java.util.*;

public class M0721_AccountsMerge {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> emailToName = new HashMap();  //<email node, neighbor nodes>
		Map<String, ArrayList<String>> graph = new HashMap();   //<email, username> required to insert name in results after sorting
		
		for (List<String> account: accounts) {
			String name = "";
			for (int i = 0; i < account.size(); i++) {
				String email = account.get(i);
				if (i == 0) {     //first component in each list is name so we need to add here when i = 0;
					name = email;
					continue;
				}if (i == 1) {    //for 2nd component in each list we create a new arraylist with key as email
					graph.computeIfAbsent(email, x -> new ArrayList<>());
					emailToName.put(email, name);
					continue;
				}
				graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));  //for rest of the emails of same account create a new arraylist and add
				//first email as its value
				graph.get(account.get(1)).add(email);  //and add this email to edge of first component. i.e. two way binding of graph edges are both ways
				emailToName.put(email, name);
			}
		}
		
		Set<String> seen = new HashSet();
		List<List<String>> ans = new ArrayList();
		for (String email: graph.keySet()) { // jsmith has three values john_newyork, john_newyork1, john00
			if (!seen.contains(email)) {
				seen.add(email);   //add jsmith to seen and stack
				Stack<String> stack = new Stack();
				stack.push(email);
				List<String> component = new ArrayList();
				while (!stack.empty()) {
					String node = stack.pop();  //pop jsmith
					component.add(node);    //add to result list
					for (String nei: graph.get(node)) {  //check neighbor osf jsmith 1 by 1 and do dfs
						if (!seen.contains(nei)) {
							seen.add(nei);   //add neighbor to seen and push in stack to check its neighbor
							stack.push(nei);
						}
					}
				}
				Collections.sort(component);
				component.add(0, emailToName.get(email));
				ans.add(component);
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList();
		List<String> a = new ArrayList<>();
		a.add("John");
		a.add("johnsmith@mail.com");
		a.add("john_newyork@mail.com");
		a.add("john_newyork1@mail.com");
		
		List<String> b = new ArrayList<>();
		b.add("John");
		b.add("john00@mail.com");
		b.add("johnsmith@mail.com");
		
		List<String> c = new ArrayList<>();
		c.add("John");
		c.add("johnnybravo@mail.com");
		
		List<String> d = new ArrayList<>();
		d.add("Mary");
		d.add("mary@mail.com");
		
		accounts.add(a);
		accounts.add(b);
		accounts.add(c);
		accounts.add(d);
		
		M0721_AccountsMerge cl = new M0721_AccountsMerge();
		List<List<String>> result = cl.accountsMerge(accounts);
		for(List<String> l : result)
			System.out.println(l);
	}
}

/*
[John, a.mail, b.mail, c.mail, d.mail]
[Jonn, a.mail, f.mail]  we need to merge these 2 becuase they have common address

step 1 : graph =>
key = a.mail , val = 0
key = b.mail, val = a.mail || key = a.mail , val = b.mail
key = c.mail, val = a.mail || key = a.mail, val = b.mail and c.mail


 */