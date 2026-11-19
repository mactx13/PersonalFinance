package OOP_HW;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {

    public void saveUsers(List<User> users) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File file = new File("data/users.json");

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, users);
    }

    public List<User> loadUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File file = new File("data/users.json");

        if (!file.exists()) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                file,
                mapper.getTypeFactory().constructCollectionType(
                        List.class,
                        User.class
                )
        );
    }
}
