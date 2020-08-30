package LC_Medium;

import java.util.List;
import java.util.Stack;

interface NestedInteger{
	public Integer next();
	public Integer getInteger();
	public boolean hasNext();
	public boolean isInteger();
	public List getList();
}
public class M0341_FlattenNestedListIterator implements NestedInteger {
	Stack<NestedInteger> stack = new Stack<>();

	public M0341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
		resolveNested(nestedList);
	}

	private void resolveNested(List<NestedInteger> nestedList) {
		for (int i = nestedList.size() - 1; i >= 0; i--) {
			stack.push(nestedList.get(i));
		}
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			return null;
		}

		return stack.pop().getInteger();
	}
	

	
	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			if (stack.peek().isInteger()) return true;
			resolveNested(stack.pop().getList());
		}

		return false;
	}
	
	/*==============useless functions here and provided by interface at LC===============*/
	@Override
	public boolean isInteger() {
		return false;
	}
	
	@Override
	public List getList() {
		return null;
	}
	
	@Override
	public Integer getInteger() {
		return null;
	}
	
	
}



/**
 *
 * A question before this is the Nested List Weight Sum, and it requires recursion to solve.
 * As it carries to this problem that we will need recursion to solve it.
 * But since we need to access each NestedInteger at a time, we will use a stack to help.
 *
 * In the constructor, we push all the nestedList into the stack from back to front, so when we pop the stack,
 * it returns the very first element. Second, in the hasNext() function, we peek the first element in stack currently,
 * and if it is an Integer, we will return true and pop the element. If it is a list, we will further flatten it.
 * This is iterative version of flatting the nested list. Again, we need to iterate from the back to front of the list.
 */