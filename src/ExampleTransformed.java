public class ExampleTransformed {
	public static void main(String[] args) {   
		m (3, 2);  
	}  
	public static int m(int a, int b) {   
		if (a < 2)
			a++;  else { System.out.print("Else visited"); b++; }
		return a + b;  
	}
}
