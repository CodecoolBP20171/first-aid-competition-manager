package com.codecool.firstaidcompetition.util;

import com.codecool.firstaidcompetition.model.*;
import com.codecool.firstaidcompetition.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DBHandler implements ApplicationRunner {

    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final ProtestRepository protestRepository;
    private final StationRepository stationRepository;
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final TeamResultRepository teamResultRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DBHandler(UserRepository userRepository, CompetitionRepository competitionRepository, ExerciseRepository exerciseRepository, ProtestRepository protestRepository, StationRepository stationRepository, TaskRepository taskRepository, TeamRepository teamRepository, TeamResultRepository teamResultRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.competitionRepository = competitionRepository;
        this.exerciseRepository = exerciseRepository;
        this.protestRepository = protestRepository;
        this.stationRepository = stationRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.teamResultRepository = teamResultRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role admin = new Role("ROLE_ADMIN", null);
        Role refereeRole = new Role("ROLE_REFEREE", null);
        HashSet<Role> adminSet = new HashSet<>();
        HashSet<Role> userSet = new HashSet<>();
        adminSet.add(admin);
        userSet.add(refereeRole);

        User user = new User("Admin Béla", "admin", "kiss@gmail.com",
                bCryptPasswordEncoder.encode("admin"), null, adminSet);
        User user2 = new User("User Géza", "user", "kiss_geza@gmail.com",
                bCryptPasswordEncoder.encode("user"), null, userSet);

        Competition competition = new Competition("Első verseny", "Budapest", "2012-07-08", user);
        Competition competition2 = new Competition("Második verseny", "Babosdöbréte", "2013-07-08", user);
        Competition competition3 = new Competition("Harmadik verseny", "Cserneházadamonya", "2014-07-08", user2);
        List<Competition> comps = Arrays.asList(competition, competition2);
        List<Competition> comp3 = Arrays.asList(competition3);
        user.setCompetitions(comps);
        user2.setCompetitions(comp3);
        userRepository.save(user);
        userRepository.save(user2);

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
