package hotel.model;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String type;
	@NotNull
	private Float price;

	private String status;
	
	@ManyToOne(targetEntity=Hotel.class)
	@JsonIgnore
	private Hotel hotel;
	
	@OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<BookedRoom> bookedRooms;
	
	@OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Image> images;
}
