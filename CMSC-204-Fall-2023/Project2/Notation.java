/**
 * @author Rose LeFlore 
 * Project 2, 9/25/23 
 * CMSC 240 
 * Professor Thai
 */
public class Notation {

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyStack<Character> operatorStack = new MyStack<>();
		MyQueue<Character> solution = new MyQueue<>(infix.length());

		// Going through each character of the string
		for (char c : infix.toCharArray()) {
			// ignore spaces
			if (Character.isWhitespace(c)) {
				continue;
			}
			// if its a digit, add it to the solution queue
			else if (Character.isDigit(c)) {
				solution.enqueue(c);
			}

			// if left parenthesis push it to stack
			else if (c == '(') {
				operatorStack.push(c);

			}
			// if it's an operator, pop operators with higher or equal precedence
			// from the stack and add them to the postfix solution queue
			else if (isOperator(c)) {

				// insert to solution queue if operator higher than current operator
				while (!operatorStack.isEmpty() && higherOperator(operatorStack.top(), c)) {
					solution.enqueue(operatorStack.pop());
				}
				// push the current character onto the stack
				operatorStack.push(c);

			}
			// if it's a right parenthesis, pop operators and add them to the postfix
			// solution queue
			// until a left parenthesis is encountered
			else if (c == ')') {
				// pop to solution until parenthesis is found
				while (!operatorStack.isEmpty() && operatorStack.top() != '(') {
					solution.enqueue(operatorStack.pop());
				}
				// checks if there is a matching left parenthesis
				if (operatorStack.isEmpty() || operatorStack.pop() != '(') {
					throw new InvalidNotationFormatException();
				}
			}
			// if the infix expression is invalid
			else {
				throw new InvalidNotationFormatException();
			}
		}

		// Pop any remaining operators from the stack and add them to the postfix
		// solution queue
		while (!operatorStack.isEmpty()) {

			if (operatorStack.top() == '(') {
				throw new InvalidNotationFormatException();
			}
			solution.enqueue(operatorStack.pop());

		}

		return solution.toString();

	}

	public static String convertPostfixToInfix(String str) throws InvalidNotationFormatException {

		MyStack<String> solution = new MyStack<>(str.length());
		for (char c : str.toCharArray()) {

			// ignore if white space
			if (Character.isWhitespace(c)) {
				continue;
			}

			// if its an operator
			else if (isOperator(c)) {
				// throw error if fewer than 2 values in the stack
				if (solution.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				// pop top 2 values from the stack
				String s1 = solution.pop();
				String s2 = solution.pop();
				// create string with the 2 values
				String result = "(" + s2 + c + s1 + ")";
				// push result to the stack
				solution.push(result);

			}
			// if its an operand
			else {
				solution.push(Character.toString(c));
			}

		}
		// if more than one value in the stack, throw an error
		if (solution.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return solution.toString();

	}

	public static double evaluatePostfixExpression(String str) throws InvalidNotationFormatException {
		MyStack<Double> stack = new MyStack<>();

		for (char ch : str.toCharArray()) {
			//ignore spaces
			if (Character.isWhitespace(ch)) {
				continue;
			} 
			//push the operand to the stack
			else if (Character.isDigit(ch)) {
				stack.push((double) Character.getNumericValue(ch));
			} 
			// if character is an operator
			else {
				//if not enough operands for the operator, throw and error
				if (stack.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				double op1 = stack.pop();
				double op2 = stack.pop();
				
				//calculate on the operands depending on the operator
				switch (ch) {
				case '+':
					stack.push(op2 + op1);
					break;
				case '-':
					stack.push(op2 - op1);
					break;
				case '*':
					stack.push(op2 * op1);
					break;
				case '/':
					if (op1 == 0) {
						throw new InvalidNotationFormatException();
					}
					stack.push(op2 / op1);
					break;
				default:
					throw new InvalidNotationFormatException();
				}
			}
		}

		if (stack.size() != 1) {
			throw new InvalidNotationFormatException();
		}

		return stack.pop();
	}

	private static boolean higherOperator(char op1, char op2) {
		if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
			return true;
		}
		return false;
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

}
