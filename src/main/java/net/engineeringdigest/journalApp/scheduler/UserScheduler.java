package net.engineeringdigest.journalApp.scheduler;



import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpli;
import net.engineeringdigest.journalApp.service.SentimentAnalysisService;
import net.engineeringdigest.journalApp.service.emailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserRepositoryImpli userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private emailService emailService;

    @Scheduled(cron = "0 * * * * *")
    public void fetchUserAndSendSaMail(){

        List<User> users = userRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(JournalEntry::getContent).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
//            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);

        }

    }
}
