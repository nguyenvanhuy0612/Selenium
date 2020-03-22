package pk.demo;

public class Simple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String agentStatus = "not ready 11";
		if (agentStatus.startsWith("not")) {
			System.out.println("match with not ready");
		}else {
			System.out.println("Not match with not ready");
		}

	}

}
