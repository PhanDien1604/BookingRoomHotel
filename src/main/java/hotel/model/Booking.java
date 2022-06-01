package hotel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(columnDefinition = "Date")
	private String beginDate;
	@NotNull
	@Column(columnDefinition = "Date")
	private String endDate;
	private String note;
	
	@ManyToOne(targetEntity=User.class)
	@JsonIgnore
	private User user;
	
	@ManyToOne(targetEntity=Client.class)
	@JsonIgnore
	private Client client;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "booking")
	@JsonIgnore
	private List<BookedRoom> bookedRooms;
}
