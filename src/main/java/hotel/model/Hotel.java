package hotel.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank(message = "Tên cơ sở bắt buộc phải nhập")
	private String name;
	@NotNull
	@NotBlank(message = "Tên cơ sở bắt buộc phải nhập")
	private String address;
	private String rate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Room> rooms;
	
	@OneToMany(mappedBy = "hotel", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<User> users;
}
