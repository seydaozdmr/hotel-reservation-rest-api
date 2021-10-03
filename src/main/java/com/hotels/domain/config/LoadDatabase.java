package com.hotels.domain.config;

import com.hotels.domain.model.hotel.Description;
import com.hotels.domain.model.hotel.Hotel;
import com.hotels.domain.model.hotel.Location;
import com.hotels.domain.model.room.Bed;
import com.hotels.domain.model.room.BedType;
import com.hotels.domain.model.room.Feature;
import com.hotels.domain.model.room.Room;
import com.hotels.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class LoadDatabase {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;
    private final FeatureRepository featureRepository;

    public LoadDatabase(HotelRepository hotelRepository,  DescriptionRepository descriptionRepository, RoomRepository roomRepository, BedRepository bedRepository, FeatureRepository featureRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.bedRepository = bedRepository;
        this.featureRepository = featureRepository;
    }


    @Bean
    CommandLineRunner initDb(){
        return args -> {
            Room r1=new Room("İki Kişilik Oda",new Bed(BedType.DOUBLE, BigDecimal.valueOf(250)));
            Room r2=new Room("Tek Kişilik Oda",new Bed(BedType.SINGLE, BigDecimal.valueOf(150)));
            r1.makeReservation(LocalDate.of(2021,10,4),LocalDate.of(2021,10,15));
            r1.makeReservation(LocalDate.of(2021,10,2),LocalDate.of(2021,10,5));
            r1.makeReservation(LocalDate.of(2021,10,6),LocalDate.of(2021,10,25));
            r2.makeReservation(LocalDate.of(2021,10,9),LocalDate.of(2021,10,12));
            r1.makeReservation(LocalDate.of(2021,10,16),LocalDate.of(2021,10,20));
            Room r3=new Room("İki Kişilik Daire",new Bed(BedType.KING, BigDecimal.valueOf(500)));
            Room r4=new Room("Üç Kişilik Oda",new Bed(BedType.SINGLE, BigDecimal.valueOf(250)));
            r4.addBed(new Bed(BedType.DOUBLE,BigDecimal.valueOf(300)));

            Room r5=new Room("Dört Kişilik Oda",new Bed(BedType.DOUBLE, BigDecimal.valueOf(250)));
            r5.addBed(new Bed(BedType.DOUBLE,BigDecimal.valueOf(400)));

            Room r6=new Room("Aile Odası",new Bed(BedType.DOUBLE, BigDecimal.valueOf(400)));
            r6.addBed(new Bed(BedType.KING,BigDecimal.valueOf(600)));

            Room r7=new Room("İki Kişilik Tek Yataklı Oda",new Bed(BedType.SINGLE, BigDecimal.valueOf(150)));
            r7.addBed(new Bed(BedType.SINGLE,BigDecimal.valueOf(150)));

            Room r8=new Room("Dört Kişilik Suit Oda",new Bed(BedType.SINGLE, BigDecimal.valueOf(250)));
            r8.addBed(new Bed(BedType.SINGLE,BigDecimal.valueOf(150)));
            r8.addBed(new Bed(BedType.DOUBLE,BigDecimal.valueOf(350)));

            Room r9=new Room("Deluxe Oda",new Bed(BedType.KING, BigDecimal.valueOf(1000)));

            Room r10=new Room("Superior Çift Kişilik Oda",new Bed(BedType.KING, BigDecimal.valueOf(1500)));

            Hotel h1=hotelRepository.save(new Hotel.HotelBuild("Merit").buildLocation(new Location("Antalya")).buildDescription(new Description("La Villa Antalya","Baie de Somme Doğa Rezervi'nde yer alan La Villa Antalya, Ault'a sadece 1 km ve denize 300 metre mesafede hizmet veren bir oda ve kahvaltı tesisidir. Tesis bahçe manzaralı odalar, teras ve bahçe sunmaktadır.")).buildRooms(List.of(r1,r2)).build());
            Hotel h2=hotelRepository.save(new Hotel.HotelBuild("At Home").buildLocation(new Location("İzmir")).buildDescription(new Description("At Home","Barbekü ve teras sunan At'Home, Ault'ta, Saint-Valéry-sur-Somme'a 17 km uzaklıkta yer alan bir konukevidir.")).buildRooms(List.of(r3,r4,r5)).build());
            Hotel h3=hotelRepository.save(new Hotel.HotelBuild("Appartement").buildLocation(new Location("İstanbul")).buildDescription(new Description("Appartement centre village","Ault'ta, Bois de Cise'ye sadece 1,9 km mesafede yer alan Appartement centre village vue mer - Relais Fleuri, sahilde ücretsiz WiFi erişimine sahip konaklama birimleriyle hizmet vermektedir. Tesis deniz ve şehir manzaralıdır." )).buildRooms(List.of(r6,r7,r8)).build());
            Hotel h4=hotelRepository.save(new Hotel.HotelBuild("Blanc").buildLocation(new Location("Muğla")).buildDescription(new Description("Blanc d'ecume","Ault'ta, Bois de Cise'ye sadece 1,9 km mesafede yer alan Blanc d'ecume, restoran ve ücretsiz Wi-Fi erişimi ile sahil kenarında konaklama olanağı sunmaktadır. Geçmişi 1945 yılına uzanan bir binada yer alan daire, konukların yürüyüş ve balıkçılık gibi aktiviteler gerçekleştirebileceği bir bölgede hizmet vermektedir.")).buildRooms(List.of(r9,r10)).build());

            System.out.println(h1);


            System.out.println(r1);
        };
    }

}
