package com.codecool.firstaidcompetition.repository;

import com.codecool.firstaidcompetition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Repository
public class DBHandler {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ProtestRepository protestRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TeamResultRepository teamResultRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void populateDB() throws ParseException {
        Role admin = new Role("ROLE_ADMIN", null);
        Role refereeRole = new Role("ROLE_REFEREE", null);
        HashSet<Role> adminSet = new HashSet<>();
        HashSet<Role> userSet = new HashSet<>();
        adminSet.add(admin);
        userSet.add(refereeRole);
        roleRepository.save(admin);
        roleRepository.save(refereeRole);

        User user = new User("Admin Béla", "admin", "kiss@gmail.com",
                bCryptPasswordEncoder.encode("admin"), null, adminSet);
        User user2 = new User("User Géza", "user", "kiss_geza@gmail.com",
                bCryptPasswordEncoder.encode("user"), null, userSet);
        userRepository.save(user);
        userRepository.save(user2);

        // Date formatting example
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("2012-07-08");
        Date date2 = simpleDateFormat.parse("2013-09-08");
        Date date3 = simpleDateFormat.parse("2014-10-08");

        Competition competition = new Competition("Első verseny", "Budapest", "2012-07-08", user);
        Competition competition2 = new Competition("Második verseny", "Babosdöbréte", "2013-07-08", user);
        Competition competition3 = new Competition("Harmadik verseny", "Cserneházadamonya", "2014-07-08", user2);
        competitionRepository.save(competition);
        competitionRepository.save(competition2);
        competitionRepository.save(competition3);

        Station station = new Station("12. állomás", 12, "harmadik leírás", competition);

        Exercise exercise = new Exercise("exercise name", "loooooooooooooooooong description", station);
        stationRepository.save(station);
        exerciseRepository.save(exercise);

        List<Exercise> exerciseList = Arrays.asList(exercise);
        station.setExercise(exerciseList);

        Station station2 = new Station("5. állomás", 5, "másik leírás", competition2);
        Station station3 = new Station("7. állomás", 7, "leírás", competition3);
        stationRepository.save(station2);
        stationRepository.save(station3);

        Task task = new Task("subtaskname", 12L);
        exercise.addTask(task);
        taskRepository.save(task);

        Team team = new Team("Csapatnév", 2, 0456, TeamCategory.CHILD, competition);
        teamRepository.save(team);

        TeamResult teamResult = new TeamResult(12, team);
        teamResultRepository.save(teamResult);

        Protest protest = new Protest(team, exercise, "óvási leírás", "döntés megszületett");
        protestRepository.save(protest);
    }
}
