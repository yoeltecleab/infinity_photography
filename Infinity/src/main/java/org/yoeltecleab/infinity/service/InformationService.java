package org.yoeltecleab.infinity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.yoeltecleab.infinity.model.Information;
import org.yoeltecleab.infinity.repository.InformationRepository;
import org.yoeltecleab.infinity.transfer.InformationDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <pre>
 *     This class "CustomerService" Autowired the CustomerRepository class
 *     contains the method "save" to save the information to the database
 * </pre>
 */
@Service
public class InformationService {

    final double BasicPrintedCount = 10;
    final double BasicDigitalCount = 10;
    final double PricePerPrint = 12;
    final double PricePerDigital = 7;
    final double BasicDuration = 1;
    final double PricePerHour = 90;
    final double BasicPeopleCount = 1;
    final double PricePerExtraPerson = 50;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ServicesService service;

    public void save(InformationDto infoDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Information info = new Information();
        calculations(infoDto, auth, info);
        info.setConfirmation("Confirmed");
        informationRepository.save(info);
    }

    public void deleteInformation(int infoId) {
        informationRepository.deleteByBookingId(infoId);
    }

    public List<Information> allBookingByEmail(String userEmail) {
        return informationRepository.findAllByUser_Email(userEmail);
    }

    public double sumTotal(String userEmail) {
        return informationRepository.allTotalPrice(userEmail) == null ? 0 : informationRepository.allTotalPrice(userEmail);
    }

    public Information infoById(int infoId) {
        return informationRepository.findByBookingId(infoId);
    }

    public void modify(InformationDto infoDto, int infoId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Before adding  " + informationRepository.findByBookingId(infoId));
        Information info = informationRepository.findByBookingId(infoId);
        calculations(infoDto, auth, info);
        info.setConfirmation("Changed");
        informationRepository.save(info);
    }

    public void calculations(InformationDto infoDto, Authentication auth, Information info) {
        info.setDigital(infoDto.getDigital());
        info.setDuration(infoDto.getDuration());
        info.setOccasion(infoDto.getOccasion());
        info.setLocation(infoDto.getLocation());
        info.setPeople(infoDto.getPeople());
        info.setServiceDate(infoDto.getServiceDate()); // Todo the infoDto is reading null. Find a way for that
        info.setServiceTime(infoDto.getServiceTime());
        info.setPrinted(infoDto.getPrinted());
        info.setBookingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        info.setUser(userService.findByEmail(auth.getName()));
        {
            double flatRate;
            if (infoDto.getLocation().equalsIgnoreCase("New Born"))
                flatRate = 150;
            else if (infoDto.getLocation().equalsIgnoreCase("Portrait"))
                flatRate = 175;
            else if (infoDto.getLocation().equalsIgnoreCase("Birth Day"))
                flatRate = 200;
            else if (infoDto.getLocation().equalsIgnoreCase("Celebration"))
                flatRate = 250;
            else if (infoDto.getLocation().equalsIgnoreCase("Engagement"))
                flatRate = 275;
            else if (infoDto.getLocation().equalsIgnoreCase("Graduation"))
                flatRate = 200;
            else if (infoDto.getLocation().equalsIgnoreCase("Maternity"))
                flatRate = 225;
            else flatRate = 0;

            if (infoDto.getLocation().equalsIgnoreCase("Indoor"))
                flatRate += 0;

            else if (infoDto.getLocation().equalsIgnoreCase("Outdoor"))
                flatRate += 50;
            else flatRate = 999;

            info.setTotalPrice(flatRate +
                    ((infoDto.getPrinted() - BasicPrintedCount) * PricePerPrint) +
                    ((infoDto.getDigital() - BasicDigitalCount) * PricePerDigital) +
                    ((infoDto.getDuration() - BasicDuration) * PricePerHour) +
                    ((infoDto.getPeople() - BasicPeopleCount) * PricePerExtraPerson)
            );
        }

        info.setServices(
                service.findServiceByOccasion(infoDto.getOccasion())
        );
    }

    public void deleteAll(String userEmail) {
        informationRepository.deleteInformationByUser(userService.findByEmail(userEmail));
    }
}
