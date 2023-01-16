package com.example.softwareengineering.statistics;

/**
 * DB 에 저장되어 있는 감정들에 점수를 기록하기 위하여 만든 class
 * 점수가 높은 순으로 정렬하는 기능도 사용하기 위해 정의
 */
public class Score implements Comparable<Score> {

    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public void addScore() { score += 10; }

    @Override
    public int compareTo(Score o) {
        if (this.score > o.score) {
            return -1;
        } else {
            return 1;
        }
    }
}
