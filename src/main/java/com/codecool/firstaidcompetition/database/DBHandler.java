package com.codecool.firstaidcompetition.database;

import com.codecool.firstaidcompetition.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class DBHandler {

    private CompetitionRepository competitionRepository;
    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private ProtestRepository protestRepository;
    private StationRepository stationRepository;
    private TaskRepository taskRepository;
    private TeamRepository teamRepository;
    private TeamResultRepository teamResultRepository;

    @Autowired
    public DBHandler(CompetitionRepository competitionRepository,
                     UserRepository userRepository, ExerciseRepository exerciseRepository,
                     ProtestRepository protestRepository, StationRepository stationRepository,
                     TaskRepository taskRepository, TeamRepository teamRepository,
                     TeamResultRepository teamResultRepository){
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.protestRepository = protestRepository;
        this.stationRepository = stationRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.teamResultRepository = teamResultRepository;
    }

    public Iterable<Competition> getAllCompetition(){
        return competitionRepository.findAll();
    }

    public Iterable<Station> getAllStation(){ return stationRepository.findAll(); }

    public CompetitionRepository getCompetitionRepository() {
        return competitionRepository;
    }

    public StationRepository getStationRepositoryRepository() {
        return stationRepository;
    }

    public UserRepository getUserRepository() { return userRepository; }

    public void populateDB() throws ParseException {
        User user = new User("Kiss Gyula", "kiss_gyula", "kiss@gmail.com", "pass");
        User user2 = new User("Kiss Géza", "kiss_geza", "kiss_geza@gmail.com", "pass");
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
        Station station2 = new Station("5. állomás", 5, "másik leírás", competition2);
        Station station3 = new Station("7. állomás", 7, "leírás", competition3);
        stationRepository.save(station);
        stationRepository.save(station2);
        stationRepository.save(station3);

        Exercise exercise = new Exercise("exercise name", "loooooooooooooooooong description");
        exerciseRepository.save(exercise);

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
