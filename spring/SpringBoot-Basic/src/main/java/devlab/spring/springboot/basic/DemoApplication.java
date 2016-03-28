package devlab.spring.springboot.basic;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
/**
 * 
 * @author asus
 *
 */
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Component
class BookingCommandLineRunner implements CommandLineRunner {

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		
		System.out.println("CommandLineRunner");

		for (Booking b : this.bookingRepository.findAll()) {
			System.out.println(b.toString());
		}
		
	}
	
	@Autowired BookingRepository bookingRepository;
	
}


interface BookingRepository extends JpaRepository<Booking, Long> {
	Collection<Booking> findByBookingName(String bookingName);
}

@RestController
class BookingRestController {
	
	@RequestMapping("/bookings")
	Collection<Booking> bookings() {
		return this.bookingRepository.findAll();
	}
	
	@Autowired BookingRepository bookingRepository;
}

@Entity
class Booking {
	@Id @GeneratedValue
	private Long id;
	private String bookingName;
	
	/**
	 * @param bookingName
	 */
	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}

	/**
	 * 
	 */
	public Booking() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the bookingName
	 */
	public String getBookingName() {
		return bookingName;
	}
	
	
	
}
