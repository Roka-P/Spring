package java0708;

public class _508SwitchStrExample {

	public static void main(String[] args) {
		String[] hello = {"????Έ?", "?¬λ‘?", "???€", "κ³€λμ°μ?"};
		
		int i = (int)(Math.random()*hello.length);
		System.out.println("? ?? ?¨?΄ : " + hello[i]);
		
		switch(hello[i]) {
			case "????Έ?" :
				System.out.println("?κ΅??΄???€.");
				break;
			case "?¬λ‘?" :
				System.out.println("??΄???€.");
				break;
			case "???€" :
				System.out.println("μ€κ΅­?΄???€.");
				break;
			case "κ³€λμ°μ?" :
				System.out.println("?Όλ³Έμ΄???€.");
				break;
			default :
				System.out.println("!?!?!?!?!?!?");
				break;
				
		}
	}

}
