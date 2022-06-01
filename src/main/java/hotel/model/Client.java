package hotel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
//	@NotBlank(message = "Tên tài khoản bắt buộc phải nhập")
	private String username;
//	@NotBlank(message = "Mật khẩu bắt buộc phải nhập")
	private String password;

	@NotBlank(message = "Họ và tên bắt buộc phải nhập")
	private String name;

	@NotBlank(message = "Địa chỉ bắt buộc phải nhập")
	private String address;

	@NotBlank(message = "Số điện thoại bắt buộc phải nhập")
	private String tel;
	private String email;
	private String note;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Booking> bookings;
}
