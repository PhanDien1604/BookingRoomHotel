package hotel.model;

import java.util.List;

import javax.persistence.Entity;
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
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank(message = "Tên tài khoản bắt buộc được nhập")
	private String username;
	@NotNull
	@NotBlank(message = "Mật khẩu bắt buộc phải nhập")
	private String password;
	@NotNull
	private String position;
	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	private String tel;
	private String email;
	private String note;
	
	@ManyToOne(targetEntity=Hotel.class)
	@JsonIgnore
	private Hotel hotel;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Booking> bookings;
}
