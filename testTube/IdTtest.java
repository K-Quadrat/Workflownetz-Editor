
public class IdTtest {

	public static void main(String[] args) {
		String transition = "Transition1";
		int transitionInt;
		
		transition = transition.replaceAll("[A-Z,a-z]", "");
		
		System.out.println("transition Sting: " + transition);

		transitionInt = (Integer.parseInt(transition.replaceAll("[A-Z,a-z]", "")));

		System.out.println("transition Int: " + transitionInt);

		

	}

}
