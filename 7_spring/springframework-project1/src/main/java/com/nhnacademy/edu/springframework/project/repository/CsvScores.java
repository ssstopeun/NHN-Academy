package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CsvScores implements Scores {
    private static CsvScores csvScores;
    private List<Score> scores;

    public CsvScores() {
        scores = new ArrayList<>();
    }

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        if(csvScores == null){
            csvScores = new CsvScores();
        }
        return csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        String csvFile = "src/main/resources/data/score.csv";
        String line;

        try(BufferedReader br= new BufferedReader(new FileReader(csvFile))){
            while((line = br.readLine())!=null){
                String[] data = line.split(",");
                Score score = new Score(Integer.parseInt(data[0]),Integer.parseInt(data[1]));
                scores.add(score);
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Score> findAll() {
        return scores;
    }
}
