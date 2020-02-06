import java.util.Scanner;
import java.util.Stack;

/**
 * @author Daniel Iliev
 * @version Prototype 1 
 * For this program I will use the stack and scanner classes
 * With scanner class I will scrape the user's in.
 */

public class ExpressionTree {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the expression for evaluation :");
		String infix_in = scan.nextLine();
		//infix example = " 3 - 4 / 5 + 6 ";
		System.out.println("Infix Expression: " + infix_in);
		System.out.println("Postfix Expression: " + infixToPostFix(infix_in));
		System.out.println("Prefix Expression: " + infixToPreFix(infix_in));
	}
	
	// using switch I will determine which is the case or operator being used
	// the switch will return an integer assigned to different operators an -1 if no match was found
	static int operator(char ch) {
		switch (ch) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '^':
				return 3;
			}
		return -1;
	}

	// the method infixToPreFix() will convert the infix expression to Prefix.
	static StringBuilder infixToPreFix(String expression) {
		// uses StringBuilder to map the expression to an object and reverse it
		StringBuilder strResult = new StringBuilder(); 
		StringBuilder in = new StringBuilder(expression);
		in.reverse();
		Stack<Character> charStack = new Stack<Character>();  // initializing a stack which will be fed by the operator()
		char[] charsExpression = new String(in).toCharArray();
		
		for (int i = 0; i < charsExpression.length; i++) { // this of loop will iterate the array charsExpression to reverse only the brackets

			if (charsExpression[i] == '(') {
				charsExpression[i] = ')';
				i++;
			} else if (charsExpression[i] == ')') {
				charsExpression[i] = '(';
				i++;
			}
		}
		for (int i = 0; i < charsExpression.length; i++) { // this for loop will iterate contents of the expression
			char c = charsExpression[i];

			// this if will check if the character is operator and push it to the stack
			if (operator(c) > 0) {
				while (charStack.isEmpty() == false && operator(charStack.peek()) >= operator(c)) {
					strResult.append(charStack.pop());
				}
				charStack.push(c);
				
			// this else if will pop an operator if the char == ) and will include it to the expression
			} else if (c == ')') {
				char x = charStack.pop();
				while (x != '(') {
					strResult.append(x);
					x = charStack.pop();
				}
				// this else if will will push the operator if char == (
			} else if (c == '(') {
				charStack.push(c);
			} else {
				// if the character is neither operator nor ( it will be a number
				strResult.append(c);
			}
		}
		// this if statement will iterate the contents of the stack to the final string
		for (int i = 0; i <= charStack.size(); i++) {
			strResult.append(charStack.pop());
		}
		//This statement will reverse the StringBulder Object
		return strResult.reverse();
	}

	static String infixToPostFix(String expression) {

		String strResult = ""; // initializing a blank string strResult. 
		Stack<Character> charStack = new Stack<>(); // initializing a stack which will be fed by the operator()
		for (int i = 0; i < expression.length(); i++) { // this for loop will iterate contents of the expression
			char ch = expression.charAt(i);

			// this if will check if the character is operator and push it to the stack
			if (operator(ch) > 0) {
				while (charStack.isEmpty() == false && operator(charStack.peek()) >= operator(ch)) {
					strResult += charStack.pop();
				}
				charStack.push(ch);
			// this else if will pop an operator if the char == ) and will include it to the expression
			} else if (ch == ')') {
				char x = charStack.pop();
				while (x != '(') {
					strResult += x;
					x = charStack.pop();
				}
			// this else if will will push the operator if char == (
			} else if (ch == '(') {
				charStack.push(ch);
			} else {
				// if the character is neither operator nor ( it will be a number
				
				strResult += ch;
			}
		}
		// this if statement will iterate the contents of the stack to the final string
		for (int i = 0; i <= charStack.size(); i++) {
			strResult += charStack.pop();
		}
		return strResult;
	}

}
