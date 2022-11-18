package util;
import java.security.MessageDigest;

//[수정-1] https://moonsiri.tistory.com/39
import java.security.SecureRandom;
import java.util.Date;

//---단방향 암호화(SHA 256)로 암호화하면 복호화가 불가능: ★★★★★★★ https://truecode-95.tistory.com/153 에 암호화와 복호화(양방향 암호화(RSA)사용)하는 방식까지 소스 자세히 나옴 ★★★★★★★ 
public class SHA256 { 
	//'Salt+해싱'을 이용하여 비밀번호를 암호화(단, 비밀번호 찾기를 하면 신규로 8자리 랜덤비번을 보여줌)(단, 비밀번호 찾기는 안되고 비밀번호를 신규로 발급하여 메일로 전송함-나중에 기능 추가)
	
	/* 암호를 보호하는 가장 좋은 방법은 소금을 친 해싱을 사용하는 것이다.
	 * (소금을 치다 -> 패스워드를 보호하기 위해 특별한 값(소금)을 추가 하는 것)
	 * 소금이라고 불리는 무작위 문자열을 비밀번호를 해싱하기 전에 붙여서 해쉬 값을 무작위로 만들 수 있다.
	 */
	//원본 : 소금이 고정이 아니면 로그인할 때 비빌번호의 소금도 변경되어 로그인이 안됨(따라서, 소금값을 고정시킴)
	
	private static String salt ="맥카페";//임시 소금(멤버변수로 선언)	
	
	//[수정-1] : 8 글자의 임시비밀번호가 생성(소금으로 사용).같은 비밀번호인데도 결과로 생성된 해시값은 매번 다르게 나온다. 
	//private static String Salt = getRamdomPassword(8);//메서드는 아래 있음
		
	//[수정-2] 시작-------------------------------------------------
	// 1. SALT 값 생성  : https://st-lab.tistory.com/100
	/* getSALT() 메서드 설명 : 임의의 문자열을 생성해주는 솔트 생성 역할을 한다. 
	 * 일반적인 Random 클래스와 사용방법은 크게 다르지 않다. 
	 * 임의의 바이트 배열을 생성한 뒤, nextBytes() 에 해당 배열을 넣어, 바이트 배열이 임의의 값들로 채워지도록 하는 것이다. 
	 * 그렇게 채워진 배열이 바로 솔트가 되는 것이고, 해당 배열을 반환해준다.
	 */	
	/*== 포트폴리오에 필요없는 메서드(시작)====================================================*/
	private static String getSALT(int SALT_SIZE){//SALT_SIZE만큼의  SALT 생성
		byte[] temp = null;
		try {
			SecureRandom rnd = new SecureRandom();
			temp = new byte[SALT_SIZE];//임의의 바이트 배열을 생성한 뒤,
			rnd.nextBytes(temp);//nextBytes() 에 해당 배열을 넣어, 바이트 배열이 임의의 값들로 채워지도록 하는 것이다. 
		} catch (Exception e) {			
			//e.printStackTrace();
			System.out.println(e);
		}		
		return Byte_to_String(temp);
		
	}	
	// byte 배열을 String 으로 변환
	private static String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
	/*== 포트폴리오에 필요없는 메서드(끝)=====================================================*/


	//private static String Salt = getSALT(8);//[수정-2]
	//[수정-2] 끝------------------------------------------------------	
	
	/*==[암호화 전 비밀번호 찾기]를 할 때 리턴 값으로 돌려주기 위해 추가해 봄(시작)==========================*/
	private static String prePassword;	
	public static String getPrePassword() {
		return prePassword;
	}
	/*==[암호화 전 비밀번호 찾기]를 할 때 리턴 값으로 돌려주기 위해 추가해 봄(끝)===========================*/
	
	/*== 포트폴리오에 필요한 메서드(시작) ======================================================*/
	public static String encodeSHA256(String password){//password:암호화되기 전 비번
	    prePassword = password;/*--[암호화 전 비밀번호 찾기]를 위해 미리 멤버변수에 저장해둠---------*/
		
		String result = "";
		
		//원본
		/* 
		byte[] passwordByte = password.getBytes("UTF-8");// 소스를 바이트로 변경 
		byte[] salt = Salt.getBytes();//소금도 바이트로 변경
		//소금값을 암호 앞, 뒤 어드쪽에 붙여도 상관 없다. 비밀번호 앞에 사용하는게 좀 더 일반적이긴 하다.
		byte[] bytes = new byte[passwordByte.length+salt.length];
		*/
		
		try {
			byte[] saltByte = salt.getBytes(); //salt도 바이트로 변경
			byte[] passwordByte = password.getBytes("utf-8");//암호화되기 전 비번(password)를 바이트로 변경			
			
			/*
			 * 기본값으로 채워진 byte객체에 바이트로 변경된 소금값과 비번으로 채움
			 */
			byte[] saltPassword = new byte[saltByte.length + passwordByte.length]; 
			
			//소금값을 암호 앞, 뒤 어느쪽에 붙여도 상관 없다. 비밀번호 앞에 사용하는게 좀 더 일반적이긴 하다.
			//void arraycopy(원본, 원본의 시작 index, 도착지, 도착지의 시작index, 복사할 개수)
			System.arraycopy(saltByte, 0, saltPassword, 0, saltByte.length);//saltPassword [앞] : 바이트로 변경된 "salt값"			
			System.arraycopy(passwordByte, 0, saltPassword, saltByte.length, passwordByte.length);//saltPassword [뒤] : 바이트로 변경된 암호화되기 전 "비번"
			//즉, saltPassword [앞] : 바이트로 변경된 "salt값" + [뒤] : 바이트로 변경된 암호화되기 전 "비번"
			
			/* 'SHA-256알고리즘'을 사용하기 위해 먼저,import java.security.MessageDigest;
			 * new 생성자를 따로 쓸 필요 없이 아래와 같이 하면 자동으로 객체 생성됨
			 */
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//md.update()가 바로 입력한 문자열을 해싱하는 함수다. 이 때 문자열은 바이트배열이어야 한다.
			md.update(saltPassword);
			
			byte[] saltPasswordDigest =  md.digest();//md객체의 다이제스트를 얻어 password를 갱신한다.
		
			/* String과 'StingBuffer/StringBuilder' 클래스의 가장 큰 차이점은
			 * String은 불변의 속성을 갖는다. 
			 * 그래서 (예)"안녕"+"하세요"=>새로운문자열객체 "안녕하세요" heap메모리에 생성하므로
			 * 문자열을 수정, 추가, 삭제 등의 연산이 빈번히 발생하는 알고리즘에서는 heap메모리에 가비지(=쓰레기)객체가
			 * 계속 누적되어 힙메모리 부족으로 애플리케이션 성능에 치명적인 영향을 미친다.
			 * 
			 * 이를 해결하기 위해서 가변성을 가진 'StingBuffer/StringBuilder'클래스를 도입했다.
			 * 따라서 문자열을 수정, 추가, 삭제 등의 연산이 빈번하게 발생하는 경우라면 String가 아니라
			 * 'StingBuffer/StringBuilder' 클래스를 사용해야 한다.
			 * 
			 * 
			 * ['StingBuffer/StringBuilder' 클래스의 차이점]
			 * 1.문자열 연산이 많고 '멀티쓰레드' 환경일 경우 : StringBuffer
			 * 2.문자열 연산이 많고 '싱글쓰레드' 환경일 경우 : StringBuilder
			 * 
			 * 3.문자열 연산이 적고 '멀티쓰레드' 환경일 경우 : String
			 */
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<saltPasswordDigest.length ;i++) {
				/* 1.saltPasswordDigest의 하나하나의 byte를 16진수로 변경 ->2."16진수" -> 3.StringBuffer에 추가
				 * byte를 16진수로 변경하는 이유?
				 * 기본적으로 데이터는 binary data로 전달된다.
				 * 16진수 배열로 만들 경우 데이터의 손실없이 유지할 수 있는 장점이 있다.
				 * 꼭 16진수로 변환하지 않고 Base64 같은 64진수로도 표현되기도 한다.
				 */
				//1.saltPasswordDigest의 하나하나의 byte를 16진수로 변경 ->2."16진수" -> 3.StringBuffer에 추가
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+256, 16).substring(1));
			}
			result = sb.toString();
		
		} catch (Exception e) {			
			//e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("encodeSHA256 : "+result);
		return result;
	}
	
	
	/* [수정-1]
	 * https://moonsiri.tistory.com/39
	 * 임시 비밀번호 생성
	 * getRamdomPassword(8)를 호출하면 8 글자의 임시비밀번호가 생성됩니다.
	 * (1.'영어대소문자+숫자+특수문자'가 결합된 8 글자의 임시비밀번호 생성됨)
	 * (2.'영어대소문자+숫자'가 결합된 8 글자의 임시비밀번호 생성됨)
	 */
	public static String getRamdomPassword(int size) { //size가 8이면  8 글자의 임시비밀번호가 생성
		//1.'영어대소문자+숫자+특수문자'가 결합된 8 글자의 임시비밀번호
		/*
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'!', '@', '#', '$', '%', '^', '&' }; 
		*/
		
		//2.'영어대소문자+숫자'가 결합된 8 글자의 임시비밀번호
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
		         };//특수문자 제외
		
		StringBuffer sb = new StringBuffer(); 
		SecureRandom sr = new SecureRandom(); 
		sr.setSeed(new Date().getTime()); 
		
		int idx = 0; 
		int len = charSet.length; 
		for (int i=0; i<size; i++) { 
			// idx = (int) (len * Math.random()); 
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
			sb.append(charSet[idx]); 
			} 
		return sb.toString(); 
	}

	/*== 포트폴리오에 필요한 메서드(끝) ==========================================================*/
}
/* <Random과 SecureRandom 클래스의 차이점>
java.util.Random 클래스는 난수를 생성할 때 seed값으로 시간을 이용합니다.
그래서 동일한 시간에 동일한 seed값으로 Random 클래스를 사용하여 난수를 생성하면 동일한 값이 리턴됩니다.
예측 가능한 난수를 사용하는 경우 공격자가 SW에서 생성되는 다음 숫자를 예상하여 시스템을 공격할 수 있습니다.

시큐어코딩 가이드 - SW 보안약점 47개 항목 중 적절하지 않은 난수 값 사용 (Use of Insufficiently Random Values)에 해당됩니다.

반면에 java.security.SecureRandom 클래스는 예측할 수 없는 seed를 이용하여 강력한 난수를 생성합니다.

[Reference]
https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html
------------------------------------------------------------------------
★★정리 잘된 사이트
https://st-lab.tistory.com/100 

http://egloos.zum.com/tiger5net/v/6630689
https://cofs.tistory.com/297

[비밀번호 해시에 소금치기 - 바르게 쓰기] :포트폴리오-헬스 게임화 플랫폼.ppt와 함께 보기
https://starplatina.tistory.com/m/entry/%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%ED%95%B4%EC%8B%9C%EC%97%90-%EC%86%8C%EA%B8%88%EC%B9%98%EA%B8%B0-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0
 
 */