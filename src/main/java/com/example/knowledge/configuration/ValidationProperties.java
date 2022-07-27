package com.example.knowledge.configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

// Bắt đầu bằng ^, kết thúc bằng $
// [...] khớp với 1 trong các kí tự trong ngoặc
// [...] khớp với 1 trong các kí tự trong ngoặc(có thể nhiểu lần)

@Data
@Component
@ConfigurationProperties(prefix = "validation")
public class ValidationProperties {

	private static Pattern _phoneNumberPattern;

	private String phoneNumberRegex;

	@PostConstruct
	public void init() {
		_phoneNumberPattern = Pattern.compile(this.phoneNumberRegex);
	}

	public boolean isPhoneNumberValid(String phoneNumber) {

		Matcher matcher = _phoneNumberPattern.matcher(phoneNumber);

		return matcher.matches();
	}

	// public static void main(String[] args) {
	// Scanner sc = new Scanner(System.in);
	//
	// // Nhận nhiều chữ số
	// String txtRegex = "^[0-9]+$";
	//
	// // Nhận 1 chữ số
	// String txtRegex1 = "^[0-9]$";
	//
	// // Nhận 1 số >= 6 kí tự
	// String txtRegex2 = "^[0-9]{6,}$";
	//
	// // Nhận 1 số chính xác 6 kí tự
	// String txtRegex3 = "^[0-9]{6}$";
	//
	// // Nhận 1 số trong khoảng 6-10 kí tự
	// String txtRegex4 = "^[0-9]{6,10}$";
	//
	// // Nhận 1 kí tự chữ hoa hoặc thường
	// String txtRegex5 = "^[a-zA-Z]$";
	//
	// // Nhận 1 hoặc nhiều kí tự hoa hoặc thường
	// String txtRegex6 = "^[a-zA-Z]+$";
	//
	// // Nhận 1 chuỗi cả số cả chữ thường + hoa
	// String txtRegex7 = "^[a-zA-Z0-9]+$";
	//
	// // Nhận 1 chuỗi cả số cả chữ thường + hoa + dấu cách
	// String txtRegex8 = "^[a-zA-Z0-9 ]+$";
	//
	// String txtRegex9 = "^\\s";
	//
	// // SĐT đuôi 03|08|09, 10 kí tự
	// String txtRegex10 = "^(\\s){0,}(0)(3|8|9)\\d{8}(\\s){0,}$";
	//
	// while (true) {
	// System.out.println("Input txt:");
	// String txt = sc.nextLine();
	//
	// Pattern pattern = Pattern.compile(txtRegex10);
	//
	// Matcher matcher = pattern.matcher(txt);
	//
	// if (matcher.matches()) {
	// System.out.println("txt ok");
	// break;
	// }
	//
	// else {
	// System.err.println("txt not ok");
	// }
	// }
	//
	//
	// sc.close();
	// }

}
