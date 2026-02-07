package main;

import java.util.Stack;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
	
	public int postfixCalc(String postfixEq) {
		Stack<Integer> stack = new Stack<>();
		String[] elements = postfixEq.trim().split("\\s+");
			
		for (String element : elements) {
			if(isNumeric(element)) {
				stack.push(Integer.parseInt(element));
			} else if (isOperator(element)) {
				if (stack.size() < 2) {
					System.out.println("Error: Invalid postfix expression");
					return Integer.MIN_VALUE;
				}
				int b = stack.pop();
				int a = stack.pop();
				int result = 0;
				
				switch (element) {
				case "+":
					result = a + b;
					break;
				case "-":
					result = a - b;
					break;
				case "*":
					result = a * b;
					break;
				case "/":
					if (b == 0) {
						System.out.println("Error: Cannot divide by zero.");
						return Integer.MIN_VALUE;
					}
					result = a / b;
					break;
				case "%":
					if (b == 0) {
						System.out.println("Error: Cannot divide by zero.");
						return Integer.MIN_VALUE;
					}
					result = a % b;
					break;
				default:
					System.out.println("Error: Invalid operator " + element);
					return Integer.MIN_VALUE;						
				}
				stack.push(result);
			} else {
				System.out.println("Error: Invalid element: " + element);
				return Integer.MIN_VALUE;
			}
		}
		
		if (stack.size() != 1) {
			System.out.println("Error: Invalid postfix expression.");
			return Integer.MIN_VALUE;
		}
		
		return stack.pop();
	}
	
	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isOperator(String str) {
		return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%");
	}
	
	public void fileEquations(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			int lineNumber = 1;
			while ((line = br.readLine()) != null) {
				System.out.println("Expression " + lineNumber + ": " + line);
				int result = postfixCalc(line);
				if (result != Integer.MIN_VALUE) {
					System.out.println("Result: " + result);
				}
				System.out.println();
				lineNumber ++;
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
