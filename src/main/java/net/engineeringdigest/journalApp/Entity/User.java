package net.engineeringdigest.journalApp.Entity;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document( collection ="users")
@Data
@Builder
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NotNull
    private String userName;
    @NotNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;


}
