package hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class BookedRoom {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(columnDefinition = "Date")
	private String date;
	
	private String note;
	
	@ManyToOne(targetEntity=Room.class)
	@JsonIgnore
	private Room room;
	
	@ManyToOne(targetEntity=Booking.class)
	@JsonIgnore
	private Booking booking;

}
